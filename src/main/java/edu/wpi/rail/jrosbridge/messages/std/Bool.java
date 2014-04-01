package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Bool message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Bool extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Bool";

	private final boolean data;

	/**
	 * Create a new Bool with a default of false.
	 */
	public Bool() {
		this(false);
	}

	/**
	 * Create a new Bool with the given data value.
	 * 
	 * @param data
	 *            The data value of the boolean.
	 */
	public Bool(boolean data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Bool.FIELD_DATA, data).build(),
				Bool.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this boolean.
	 * 
	 * @return The data value of this boolean.
	 */
	public boolean getData() {
		return this.data;
	}

	/**
	 * Create a clone of this Bool.
	 */
	@Override
	public Bool clone() {
		return new Bool(this.data);
	}

	/**
	 * Create a new Bool based on the given JSON string. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Bool message based on the given JSON string.
	 */
	public static Bool fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return Bool.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Bool based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Bool message based on the given Message.
	 */
	public static Bool fromMessage(Message m) {
		// get it from the JSON object
		return Bool.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Bool based on the given JSON object. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Bool message based on the given JSON object.
	 */
	public static Bool fromJsonObject(JsonObject jsonObject) {
		// check the fields
		boolean data = jsonObject.containsKey(Bool.FIELD_DATA) ? jsonObject
				.getBoolean(Bool.FIELD_DATA) : false;
		return new Bool(data);
	}
}
