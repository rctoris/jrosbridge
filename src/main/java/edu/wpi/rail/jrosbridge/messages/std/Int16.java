package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Int16 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Int16 extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Int16";

	private final short data;

	/**
	 * Create a new Int16 with a default of 0.
	 */
	public Int16() {
		this((short) 0);
	}

	/**
	 * Create a new Int16 with the given data value.
	 * 
	 * @param data
	 *            The data value of the short.
	 */
	public Int16(short data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Int16.FIELD_DATA, data).build(),
				Int16.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this short.
	 * 
	 * @return The data value of this short.
	 */
	public short getData() {
		return this.data;
	}

	/**
	 * Create a clone of this Int16.
	 */
	@Override
	public Int16 clone() {
		return new Int16(this.data);
	}

	/**
	 * Create a new Int16 based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Int16 message based on the given JSON string.
	 */
	public static Int16 fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return Int16.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Int16 based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Int16 message based on the given Message.
	 */
	public static Int16 fromMessage(Message m) {
		// get it from the JSON object
		return Int16.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Int16 based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Int16 message based on the given JSON object.
	 */
	public static Int16 fromJsonObject(JsonObject jsonObject) {
		// check the fields
		short data = jsonObject.containsKey(Int16.FIELD_DATA) ? (short) jsonObject
				.getInt(Int16.FIELD_DATA) : 0;
		return new Int16(data);
	}
}
