package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/String message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class String extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/String";

	private final java.lang.String data;

	/**
	 * Create a new String with an empty String.
	 */
	public String() {
		this("");
	}

	/**
	 * Create a new String with the given data value.
	 * 
	 * @param data
	 *            The data value of the String.
	 */
	public String(java.lang.String data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(String.FIELD_DATA, data).build(),
				String.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this String.
	 * 
	 * @return The data value of this String.
	 */
	public java.lang.String getData() {
		return this.data;
	}

	/**
	 * Create a clone of this String.
	 */
	@Override
	public String clone() {
		return new String(this.data);
	}

	/**
	 * Create a new String based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A String message based on the given JSON string.
	 */
	public static String fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return String.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new String based on the given Message. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A String message based on the given Message.
	 */
	public static String fromMessage(Message m) {
		// get it from the JSON object
		return String.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new String based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A String message based on the given JSON object.
	 */
	public static String fromJsonObject(JsonObject jsonObject) {
		// check the fields
		java.lang.String data = jsonObject.containsKey(String.FIELD_DATA) ? jsonObject
				.getString(String.FIELD_DATA) : "";
		return new String(data);
	}
}
