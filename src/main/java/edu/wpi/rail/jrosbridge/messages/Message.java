package edu.wpi.rail.jrosbridge.messages;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.JsonWrapper;

/**
 * Message objects are used for publishing and subscribing to and from topics.
 * These messages act as wrappers around JSON objects. Message data is
 * immutable.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Message extends JsonWrapper {

	/**
	 * The String representation of an empty message in JSON.
	 */
	public static final String EMPTY_MESSAGE = JsonWrapper.EMPTY_JSON;

	private String messageType;

	/**
	 * Create a new, empty message. The type will be set to the empty string.
	 */
	public Message() {
		this(Message.EMPTY_MESSAGE, "");
	}

	/**
	 * Create a Message based on the given String representation of a JSON
	 * object. The type will be set to the empty string.
	 * 
	 * @param jsonString
	 *            The JSON String to parse.
	 */
	public Message(String jsonString) {
		this(jsonString, "");
	}

	/**
	 * Create a Message based on the given String representation of a JSON
	 * object.
	 * 
	 * @param jsonString
	 *            The JSON String to parse.
	 * @param messageType
	 *            The type of the message (e.g., "geometry_msgs/Twist").
	 */
	public Message(String jsonString, String messageType) {
		// parse and pass it to the JSON constructor
		this(Json.createReader(new StringReader(jsonString)).readObject(),
				messageType);
	}

	/**
	 * Create a Message based on the given JSON object. The type will be set to
	 * the empty string.
	 * 
	 * @param jsonObject
	 *            The JSON object containing the message data.
	 */
	public Message(JsonObject jsonObject) {
		// setup the JSON information
		this(jsonObject, "");
	}

	/**
	 * Create a Message based on the given JSON object.
	 * 
	 * @param jsonObject
	 *            The JSON object containing the message data.
	 * @param messageType
	 *            The type of the message (e.g., "geometry_msgs/Twist").
	 */
	public Message(JsonObject jsonObject, String messageType) {
		// setup the JSON information
		super(jsonObject);
		// set the type
		this.messageType = messageType;
	}

	/**
	 * Get the type of the message if one was set.
	 * 
	 * @return The type of the message.
	 */
	public String getMessageType() {
		return this.messageType;
	}

	/**
	 * Set the type of the message.
	 * 
	 * @param messageType
	 *            The type of the message (e.g., "geometry_msgs/Twist").
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * Create a clone of this Message.
	 */
	@Override
	public Message clone() {
		return new Message(this.toJsonObject(), this.messageType);
	}
}
