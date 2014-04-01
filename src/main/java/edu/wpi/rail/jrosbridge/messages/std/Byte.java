package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Byte message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Byte extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Byte";

	private final byte data;

	/**
	 * Create a new Byte with a default of 0.
	 */
	public Byte() {
		this((byte) 0);
	}

	/**
	 * Create a new Byte with the given data value.
	 * 
	 * @param data
	 *            The data value of the byte.
	 */
	public Byte(byte data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Byte.FIELD_DATA, data).build(),
				Byte.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this byte.
	 * 
	 * @return The data value of this byte.
	 */
	public byte getData() {
		return this.data;
	}

	/**
	 * Create a clone of this Byte.
	 */
	@Override
	public Byte clone() {
		return new Byte(this.data);
	}

	/**
	 * Create a new Byte based on the given JSON string. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Byte message based on the given JSON string.
	 */
	public static Byte fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return Byte.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Byte based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Byte message based on the given Message.
	 */
	public static Byte fromMessage(Message m) {
		// get it from the JSON object
		return Byte.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Byte based on the given JSON object. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Byte message based on the given JSON object.
	 */
	public static Byte fromJsonObject(JsonObject jsonObject) {
		// check the fields
		byte data = jsonObject.containsKey(Byte.FIELD_DATA) ? (byte) jsonObject
				.getInt(Byte.FIELD_DATA) : 0;
		return new Byte(data);
	}
}
