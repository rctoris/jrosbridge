package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Int32 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Int32 extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Int32";

	private final int data;

	/**
	 * Create a new Int32 with a default of 0.
	 */
	public Int32() {
		this(0);
	}

	/**
	 * Create a new Int32 with the given data value.
	 * 
	 * @param data
	 *            The data value of the int.
	 */
	public Int32(int data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Int32.FIELD_DATA, data).build(),
				Int32.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this int.
	 * 
	 * @return The data value of this int.
	 */
	public int getData() {
		return this.data;
	}

	/**
	 * Create a clone of this Int32.
	 */
	@Override
	public Int32 clone() {
		return new Int32(this.data);
	}

	/**
	 * Create a new Int32 based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Int32 message based on the given JSON string.
	 */
	public static Int32 fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return Int32.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Int32 based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Int32 message based on the given Message.
	 */
	public static Int32 fromMessage(Message m) {
		// get it from the JSON object
		return Int32.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Int32 based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Int32 message based on the given JSON object.
	 */
	public static Int32 fromJsonObject(JsonObject jsonObject) {
		// check the fields
		int data = jsonObject.containsKey(Int32.FIELD_DATA) ? jsonObject
				.getInt(Int32.FIELD_DATA) : 0;
		return new Int32(data);
	}
}
