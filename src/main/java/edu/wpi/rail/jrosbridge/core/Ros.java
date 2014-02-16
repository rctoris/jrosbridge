package edu.wpi.rail.jrosbridge.core;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import edu.wpi.rail.jrosbridge.JRosbridge;

/**
 * The Ros object is the main connection point to the rosbridge server. This
 * object manages all communication to-and-from ROS. Typically, this object is
 * not used on its own. Instead, helper classes, such as
 * edu.wpi.rail.jrosbridge.JRosbridge.Topic, are used.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version Feb. 16, 2014
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

	private String hostname;
	private int port;

	// active session (stored upon connection)
	private Session session;

	// used throughout the library to create unique IDs for requests.
	private long idCounter;

	// keeps track of callback functions for a given topic
	private HashMap<String, ArrayList<TopicCallback>> topicCallbacks;

	// keeps track of callback functions for a given service request
	private HashMap<String, ServiceCallback> serviceCallbacks;

	// keeps track of handlers for this connection
	private ArrayList<RosHandler> handlers;

	/**
	 * Create a connection to ROS with the default hostname and port. A call to
	 * connect must be made to establish a connection.
	 */
	public Ros() {
		this(Ros.DEFAULT_HOSTNAME);
	}

	/**
	 * Create a connection to ROS with the given hostname and default port. A
	 * call to connect must be made to establish a connection.
	 * 
	 * @param hostname
	 *            The hostname to connect to.
	 */
	public Ros(String hostname) {
		this(hostname, Ros.DEFAULT_PORT);
	}

	/**
	 * Create a connection to ROS with the given hostname and port. A call to
	 * connect must be made to establish a connection.
	 * 
	 * @param hostname
	 *            The hostname to connect to.
	 * @param port
	 *            The port to connect to.
	 */
	public Ros(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
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
			URI uri = new URI("ws://" + hostname + ":" + port);
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
		return session != null && session.isOpen();
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
							.getBoolean(JRosbridge.FIELD_RESULT);
					// get the response
					JsonObject values = jsonObject
							.getJsonObject(JRosbridge.FIELD_VALUES);
					ServiceResponse response = new ServiceResponse(values);
					cb.handleServiceResponse(response, success);
				}
			} else {
				System.err.println("[WARN]: Unrecognized op code: " + message);
			}
		} catch (NullPointerException e) {
			// only occurs if there was an error with the JSON
			System.err.println("[WARN]: Invalid incoming rosbridge protocol: "
					+ message);
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
