package edu.wpi.rail.jrosbridge;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.callback.TopicCallback;
import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The Topic object is responsible for publishing and/or subscribing to a topic
 * in ROS.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Topic {

	private final Ros ros;
	private final String name;
	private final String type;
	private boolean isAdvertised;
	private boolean isSubscribed;
	private final JRosbridge.CompressionType compression;
	private final int throttleRate;

	// used to keep track of this object's callbacks
	private final ArrayList<TopicCallback> callbacks;

	// used to keep track of the subscription IDs
	private final ArrayList<String> ids;

	/**
	 * Create a ROS topic with the given information. No compression or
	 * throttling is used.
	 * 
	 * @param ros
	 *            A handle to the ROS connection.
	 * @param name
	 *            The name of the topic (e.g., "/cmd_vel").
	 * @param type
	 *            The message type (e.g., "std_msgs/String").
	 */
	public Topic(Ros ros, String name, String type) {
		this(ros, name, type, JRosbridge.CompressionType.none, 0);
	}

	/**
	 * Create a ROS topic with the given information. No throttling is used.
	 * 
	 * @param ros
	 *            A handle to the ROS connection.
	 * @param name
	 *            The name of the topic (e.g., "/cmd_vel").
	 * @param type
	 *            The message type (e.g., "std_msgs/String").
	 * @param compression
	 *            The type of compression used for this topic.
	 */
	public Topic(Ros ros, String name, String type,
			JRosbridge.CompressionType compression) {
		this(ros, name, type, compression, 0);
	}

	/**
	 * Create a ROS topic with the given information. No compression is used.
	 * 
	 * @param ros
	 *            A handle to the ROS connection.
	 * @param name
	 *            The name of the topic (e.g., "/cmd_vel").
	 * @param type
	 *            The message type (e.g., "std_msgs/String").
	 * @param throttleRate
	 *            The throttle rate to use for this topic.
	 */
	public Topic(Ros ros, String name, String type, int throttleRate) {
		this(ros, name, type, JRosbridge.CompressionType.none, throttleRate);
	}

	/**
	 * Create a ROS topic with the given information.
	 * 
	 * @param ros
	 *            A handle to the ROS connection.
	 * @param name
	 *            The name of the topic (e.g., "/cmd_vel").
	 * @param type
	 *            The message type (e.g., "std_msgs/String").
	 * @param compression
	 *            The type of compression used for this topic.
	 * @param throttleRate
	 *            The throttle rate to use for this topic.
	 */
	public Topic(Ros ros, String name, String type,
			JRosbridge.CompressionType compression, int throttleRate) {
		this.ros = ros;
		this.name = name;
		this.type = type;
		this.isAdvertised = false;
		this.isSubscribed = false;
		this.compression = compression;
		this.throttleRate = throttleRate;
		this.callbacks = new ArrayList<TopicCallback>();
		this.ids = new ArrayList<String>();
	}

	/**
	 * Get the ROS connection handle for this topic.
	 * 
	 * @return The ROS connection handle for this topic.
	 */
	public Ros getRos() {
		return this.ros;
	}

	/**
	 * Get the name of this topic.
	 * 
	 * @return The name of this topic.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the message type of this topic.
	 * 
	 * @return The message type of this topic.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Check if the current topic is advertising to ROS.
	 * 
	 * @return If the current topic is advertising to ROS.
	 */
	public boolean isAdvertised() {
		return this.isAdvertised;
	}

	/**
	 * Check if the current topic is subscribed to ROS.
	 * 
	 * @return If the current topic is subscribed to ROS.
	 */
	public boolean isSubscribed() {
		return this.isSubscribed;
	}

	/**
	 * Get the compression type for this topic.
	 * 
	 * @return The compression type for this topic.
	 */
	public JRosbridge.CompressionType getCompression() {
		return this.compression;
	}

	/**
	 * Get the throttle rate for this topic.
	 * 
	 * @return The throttle rate for this topic.
	 */
	public int getThrottleRate() {
		return this.throttleRate;
	}

	/**
	 * Subscribe to this topic. A callback function is required and will be
	 * called with any incoming message for this topic.
	 * 
	 * @param cb
	 *            The callback that will be called when incoming messages are
	 *            received.
	 */
	public void subscribe(TopicCallback cb) {
		// register the callback function
		this.ros.registerTopicCallback(this.name, cb);
		// internal reference used during unsubscribe
		this.callbacks.add(cb);

		String subscribeId = "subscribe:" + this.name + ":" + this.ros.nextId();
		this.ids.add(subscribeId);

		// build and send the rosbridge call
		JsonObject call = Json.createObjectBuilder()
				.add(JRosbridge.FIELD_OP, JRosbridge.OP_CODE_SUBSCRIBE)
				.add(JRosbridge.FIELD_ID, subscribeId)
				.add(JRosbridge.FIELD_TYPE, this.type)
				.add(JRosbridge.FIELD_TOPIC, this.name)
				.add(JRosbridge.FIELD_COMPRESSION, this.compression.toString())
				.add(JRosbridge.FIELD_THROTTLE_RATE, this.throttleRate).build();
		this.ros.send(call);

		// set the flag indicating we have subscribed
		this.isSubscribed = true;
	}

	/**
	 * Unregisters as a subscriber for the topic. Unsubscribing will remove all
	 * the associated subscribe callbacks.
	 */
	public void unsubscribe() {
		// remove this object's associated callbacks.
		for (TopicCallback cb : this.callbacks) {
			this.ros.deregisterTopicCallback(this.name, cb);
		}
		this.callbacks.clear();

		// build and send the rosbridge calls
		for (String id : this.ids) {
			JsonObject call = Json.createObjectBuilder()
					.add(JRosbridge.FIELD_OP, JRosbridge.OP_CODE_UNSUBSCRIBE)
					.add(JRosbridge.FIELD_ID, id)
					.add(JRosbridge.FIELD_TOPIC, this.name).build();
			this.ros.send(call);
		}

		// set the flag indicating we are not longer subscribed
		this.isSubscribed = false;
	}

	/**
	 * Registers as a publisher for the topic. This call will be automatically
	 * called by publish if you do not explicitly call it.
	 */
	public void advertise() {
		// build and send the rosbridge call
		String advertiseId = "advertise:" + this.name + ":" + this.ros.nextId();
		JsonObject call = Json.createObjectBuilder()
				.add(JRosbridge.FIELD_OP, JRosbridge.OP_CODE_ADVERTISE)
				.add(JRosbridge.FIELD_ID, advertiseId)
				.add(JRosbridge.FIELD_TYPE, this.type)
				.add(JRosbridge.FIELD_TOPIC, this.name).build();
		this.ros.send(call);

		// set the flag indicating we are registered
		this.isAdvertised = true;
	}

	/**
	 * Unregister as a publisher for the topic.
	 */
	public void unadvertise() {
		// build and send the rosbridge call
		String unadvertiseId = "unadvertise:" + this.name + ":"
				+ this.ros.nextId();
		JsonObject call = Json.createObjectBuilder()
				.add(JRosbridge.FIELD_OP, JRosbridge.OP_CODE_UNADVERTISE)
				.add(JRosbridge.FIELD_ID, unadvertiseId)
				.add(JRosbridge.FIELD_TOPIC, this.name).build();
		this.ros.send(call);

		// set the flag indicating we are no longer registered
		this.isAdvertised = false;
	}

	/**
	 * Publish the given message to ROS on this topic. If the topic is not
	 * advertised, it will be advertised first.
	 * 
	 * @param message
	 *            The message to publish.
	 */
	public void publish(Message message) {
		// check if we have advertised yet.
		if (!this.isAdvertised()) {
			this.advertise();
		}

		// build and send the rosbridge call
		String publishId = "publish:" + this.name + ":" + this.ros.nextId();
		JsonObject call = Json.createObjectBuilder()
				.add(JRosbridge.FIELD_OP, JRosbridge.OP_CODE_PUBLISH)
				.add(JRosbridge.FIELD_ID, publishId)
				.add(JRosbridge.FIELD_TOPIC, this.name)
				.add(JRosbridge.FIELD_MESSAGE, message.toJsonObject()).build();
		this.ros.send(call);
	}
}
