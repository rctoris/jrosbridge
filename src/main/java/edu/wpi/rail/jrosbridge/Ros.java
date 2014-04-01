package edu.wpi.rail.jrosbridge;

import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParsingException;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.glassfish.grizzly.http.util.Base64Utils;

import edu.wpi.rail.jrosbridge.callback.ServiceCallback;
import edu.wpi.rail.jrosbridge.callback.TopicCallback;
import edu.wpi.rail.jrosbridge.handler.RosHandler;
import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.services.ServiceResponse;

/**
 * The Ros object is the main connection point to the rosbridge server. This
 * object manages all communication to-and-from ROS. Typically, this object is
 * not used on its own. Instead, helper classes, such as
 * {@link edu.wpi.rail.jrosbridge.JRosbridge.Topic Topic}, are used.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version April 1, 2014
 */
@ClientEndpoint
public class Ros {

	/**
	 * The default hostname used if none is provided.
	 */
	public static final String DEFAULT_HOSTNAME = "localhost";

	/**
	 * The default port used if none is provided.
	 */
	public static final int DEFAULT_PORT = 9090;

	private final String hostname;
	private final int port;
	private final JRosbridge.WebSocketType protocol;

	// active session (stored upon connection)
	private Session session;

	// used throughout the library to create unique IDs for requests.
	private long idCounter;

	// keeps track of callback functions for a given topic
	private final HashMap<String, ArrayList<TopicCallback>> topicCallbacks;

	// keeps track of callback functions for a given service request
	private final HashMap<String, ServiceCallback> serviceCallbacks;

	// keeps track of handlers for this connection
	private final ArrayList<RosHandler> handlers;

	/**
	 * Create a connection to ROS with the default hostname and port. A call to
	 * connect must be made to establish a connection.
	 */
	public Ros() {
		this(Ros.DEFAULT_HOSTNAME);
	}

	/**
	 * Create a connection to ROS with the given hostname and default port. A
	 * call to connect must be made to establish a connection. By default,
	 * WebSockets is used (as opposed to WSS).
	 * 
	 * @param hostname
	 *            The hostname to connect to.
	 */
	public Ros(String hostname) {
		this(hostname, Ros.DEFAULT_PORT);
	}

	/**
	 * Create a connection to ROS with the given hostname and port. A call to
	 * connect must be made to establish a connection. By default, WebSockets is
	 * used (as opposed to WSS).
	 * 
	 * @param hostname
	 *            The hostname to connect to.
	 * @param port
	 *            The port to connect to.
	 */
	public Ros(String hostname, int port) {
		this(hostname, port, JRosbridge.WebSocketType.ws);
	}

	/**
	 * Create a connection to ROS with the given hostname and port. A call to
	 * connect must be made to establish a connection.
	 * 
	 * @param hostname
	 *            The hostname to connect to.
	 * @param port
	 *            The port to connect to.
	 * @param protocol
	 *            The WebSocket protocol to use.
	 */
	public Ros(String hostname, int port, JRosbridge.WebSocketType protocol) {
		this.hostname = hostname;
		this.port = port;
		this.protocol = protocol;
		this.session = null;
		this.idCounter = 0;
		this.topicCallbacks = new HashMap<String, ArrayList<TopicCallback>>();
		this.serviceCallbacks = new HashMap<String, ServiceCallback>();
		this.handlers = new ArrayList<RosHandler>();
	}

	/**
	 * Get the hostname associated with this connection.
	 * 
	 * @return The hostname associated with this connection.
	 */
	public String getHostname() {
		return this.hostname;
	}

	/**
	 * Get the port associated with this connection.
	 * 
	 * @return The port associated with this connection.
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * Get the type of WebSocket protocol being used.
	 * 
	 * @return The type of WebSocket protocol being used.
	 */
	public JRosbridge.WebSocketType getProtocol() {
		return this.protocol;
	}

	/**
	 * Get the full URL this client is connecting to.
	 * 
	 * @return
	 */
	public String getURL() {
		return this.protocol.toString() + "://" + this.hostname + ":"
				+ this.port;
	}

	/**
	 * Get the next unique ID number for this connection.
	 * 
	 * @return The next unique ID number for this connection.
	 */
	public long nextId() {
		return this.idCounter++;
	}

	/**
	 * Add a handler to this connection. This handler is called when the
	 * associated events occur.
	 * 
	 * @param handler
	 *            The handler to add.
	 */
	public void addRosHandler(RosHandler handler) {
		this.handlers.add(handler);
	}

	/**
	 * Attempt to establish a connection to rosbridge. Errors are printed to the
	 * error output stream.
	 * 
	 * @return Returns true if the connection was established successfully and
	 *         false otherwise.
	 */
	public boolean connect() {
		try {
			// create a WebSocket connection here
			URI uri = new URI(this.getURL());
			ContainerProvider.getWebSocketContainer()
					.connectToServer(this, uri);
			return true;
		} catch (DeploymentException | URISyntaxException | IOException e) {
			// failed connection, return false
			System.err.println("[ERROR]: Could not create WebSocket: "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * Disconnect the connection to rosbridge. Errors are printed to the error
	 * output stream.
	 * 
	 * @return Returns true if the disconnection was successful and false
	 *         otherwise.
	 */
	public boolean disconnect() {
		if (this.isConnected()) {
			try {
				this.session.close();
				return true;
			} catch (IOException e) {
				System.err.println("[ERROR]: Could not disconnect: "
						+ e.getMessage());
			}
		}
		// could not disconnect cleanly
		return false;
	}

	/**
	 * Check if there is a connection to rosbridge.
	 * 
	 * @return If there is a connection to rosbridge.
	 */
	public boolean isConnected() {
		return this.session != null && this.session.isOpen();
	}

	/**
	 * This function is called once a successful connection is made.
	 * 
	 * @param session
	 *            The session associated with the connection.
	 */
	@OnOpen
	public void onOpen(Session session) {
		// store the session
		this.session = session;

		// call the handlers
		for (RosHandler handler : this.handlers) {
			handler.handleConnection(session);
		}
	}

	/**
	 * This function is called once a successful disconnection is made.
	 * 
	 * @param session
	 *            The session associated with the disconnection.
	 */
	@OnClose
	public void onClose(Session session) {
		// remove the session
		this.session = null;

		// call the handlers
		for (RosHandler handler : this.handlers) {
			handler.handleDisconnection(session);
		}
	}

	/**
	 * This function is called if an error occurs.
	 * 
	 * @param session
	 *            The session for the error.
	 * @param session
	 *            The session for the error.
	 */
	@OnError
	public void onError(Session session, Throwable t) {
		// call the handlers
		for (RosHandler handler : this.handlers) {
			handler.handleError(session, t);
		}
	}

	/**
	 * This method is called once an entire message has been read in by the
	 * connection from rosbridge. It will parse the incoming JSON and attempt to
	 * handle the request appropriately.
	 * 
	 * @param message
	 *            The incoming JSON message from rosbridge.
	 */
	@OnMessage
	public void onMessage(String message) {
		try {
			// parse the JSON
			JsonObject jsonObject = Json
					.createReader(new StringReader(message)).readObject();

			// check for compression
			String op = jsonObject.getString(JRosbridge.FIELD_OP);
			if (op.equals(JRosbridge.OP_CODE_PNG)) {
				String data = jsonObject.getString(JRosbridge.FIELD_DATA);
				// decompress the PNG data
				byte[] bytes = Base64Utils.decode(data.getBytes());
				Raster imageData = ImageIO
						.read(new ByteArrayInputStream(bytes)).getRaster();

				// read the RGB data
				int[] rawData = null;
				rawData = imageData.getPixels(0, 0, imageData.getWidth(),
						imageData.getHeight(), rawData);
				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i < rawData.length; i++) {
					buffer.append(Character.toString((char) rawData[i]));
				}

				// reparse the JSON
				JsonObject newJsonObject = Json.createReader(
						new StringReader(buffer.toString())).readObject();
				handleMessage(newJsonObject);
			} else {
				handleMessage(jsonObject);
			}
		} catch (NullPointerException | IOException | JsonParsingException e) {
			// only occurs if there was an error with the JSON
			System.err.println("[WARN]: Invalid incoming rosbridge protocol: "
					+ message);
		}
	}

	/**
	 * Handle the incoming rosbridge message by calling the appropriate
	 * callbacks.
	 * 
	 * @param jsonObject
	 *            The JSON object from the incoming rosbridge message.
	 */
	private void handleMessage(JsonObject jsonObject) {
		// check for the correct fields
		String op = jsonObject.getString(JRosbridge.FIELD_OP);
		if (op.equals(JRosbridge.OP_CODE_PUBLISH)) {
			// check for the topic name
			String topic = jsonObject.getString(JRosbridge.FIELD_TOPIC);

			// call each callback with the message
			ArrayList<TopicCallback> callbacks = topicCallbacks.get(topic);
			if (callbacks != null) {
				Message msg = new Message(
						jsonObject.getJsonObject(JRosbridge.FIELD_MESSAGE));
				for (TopicCallback cb : callbacks) {
					cb.handleMessage(msg);
				}
			}
		} else if (op.equals(JRosbridge.OP_CODE_SERVICE_RESPONSE)) {
			// check for the request ID
			String id = jsonObject.getString(JRosbridge.FIELD_ID);

			// call the callback for the request
			ServiceCallback cb = serviceCallbacks.get(id);
			if (cb != null) {
				// check if a success code was given
				boolean success = jsonObject
						.containsKey(JRosbridge.FIELD_RESULT) ? jsonObject
						.getBoolean(JRosbridge.FIELD_RESULT) : true;
				// get the response
				JsonObject values = jsonObject
						.getJsonObject(JRosbridge.FIELD_VALUES);
				ServiceResponse response = new ServiceResponse(values, success);
				cb.handleServiceResponse(response);
			}
		} else {
			System.err.println("[WARN]: Unrecognized op code: "
					+ jsonObject.toString());
		}

	}

	/**
	 * Send the given JSON object to rosbridge.
	 * 
	 * @param jsonObject
	 *            The JSON object to send to rosbridge.
	 * @return If the sending of the message was successful.
	 */
	public boolean send(JsonObject jsonObject) {
		// check the connection
		if (this.isConnected()) {
			try {
				// send it as text
				this.session.getBasicRemote().sendText(jsonObject.toString());
				return true;
			} catch (IOException e) {
				System.err.println("[ERROR]: Could not send message: "
						+ e.getMessage());
			}
		}
		// message send failed
		return false;
	}

	/**
	 * Sends an authorization request to the server.
	 * 
	 * @param mac
	 *            The MAC (hash) string given by the trusted source.
	 * @param client
	 *            The IP of the client.
	 * @param dest
	 *            The IP of the destination.
	 * @param rand
	 *            The random string given by the trusted source.
	 * @param t
	 *            The time of the authorization request.
	 * @param level
	 *            The user level as a string given by the client.
	 * @param end
	 *            The end time of the client's session.
	 */
	public void authenticate(String mac, String client, String dest,
			String rand, int t, String level, int end) {
		// build and send the rosbridge call
		JsonObject call = Json.createObjectBuilder()
				.add(JRosbridge.FIELD_OP, JRosbridge.OP_CODE_AUTH)
				.add(JRosbridge.FIELD_MAC, mac)
				.add(JRosbridge.FIELD_CLIENT, client)
				.add(JRosbridge.FIELD_DESTINATION, dest)
				.add(JRosbridge.FIELD_RAND, rand).add(JRosbridge.FIELD_TIME, t)
				.add(JRosbridge.FIELD_LEVEL, level)
				.add(JRosbridge.FIELD_END_TIME, end).build();
		this.send(call);
	}

	/**
	 * Register a callback for a given topic.
	 * 
	 * @param topic
	 *            The topic to register this callback with.
	 * @param cb
	 *            The callback that will be called when messages come in for the
	 *            associated topic.
	 */
	public void registerTopicCallback(String topic, TopicCallback cb) {
		// check if any callbacks exist yet
		if (!this.topicCallbacks.containsKey(topic)) {
			this.topicCallbacks.put(topic, new ArrayList<TopicCallback>());
		}

		// add the callback
		this.topicCallbacks.get(topic).add(cb);
	}

	/**
	 * Deregister a callback for a given topic.
	 * 
	 * @param topic
	 *            The topic associated with the callback.
	 * @param cb
	 *            The callback to remove.
	 */
	public void deregisterTopicCallback(String topic, TopicCallback cb) {
		// check if any exist for this topic
		if (this.topicCallbacks.containsKey(topic)) {
			// remove the callback if it exists
			ArrayList<TopicCallback> callbacks = this.topicCallbacks.get(topic);
			if (callbacks.contains(cb)) {
				callbacks.remove(cb);
			}

			// remove the list if it is empty
			if (callbacks.size() == 0) {
				this.topicCallbacks.remove(topic);
			}
		}
	}

	/**
	 * Register a callback for a given service request.
	 * 
	 * @param serviceCallId
	 *            The unique ID of the service call.
	 * @param cb
	 *            The callback that will be called when a service request comes
	 *            back for the associated request.
	 */
	public void registerServiceCallback(String serviceCallId, ServiceCallback cb) {
		// add the callback
		this.serviceCallbacks.put(serviceCallId, cb);
	}
}
