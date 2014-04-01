package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Int8 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Int8 extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Int8";

	private final byte data;

	/**
	 * Create a new Int8 with a default of 0.
	 */
	public Int8() {
		this((byte) 0);
	}

	/**
	 * Create a new Int8 with the given data value.
	 * 
	 * @param data
	 *            The data value of the byte.
	 */
	public Int8(byte data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Int8.FIELD_DATA, data).build(),
				Int8.TYPE);
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
	 * Create a clone of this Int8.
	 */
	@Override
	public Int8 clone() {
		return new Int8(this.data);
	}

	/**
	 * Create a new Int8 based on the given JSON string. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Int8 message based on the given JSON string.
	 */
	public static Int8 fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return Int8.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Int8 based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Int8 message based on the given Message.
	 */
	public static Int8 fromMessage(Message m) {
		// get it from the JSON object
		return Int8.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Int8 based on the given JSON object. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Int8 message based on the given JSON object.
	 */
	public static Int8 fromJsonObject(JsonObject jsonObject) {
		// check the fields
		byte data = jsonObject.containsKey(Int8.FIELD_DATA) ? (byte) jsonObject
				.getInt(Int8.FIELD_DATA) : 0;
		return new Int8(data);
	}
}
