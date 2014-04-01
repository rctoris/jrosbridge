package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Int64 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Int64 extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Int64";

	private final long data;

	/**
	 * Create a new Int64 with a default of 0.
	 */
	public Int64() {
		this(0L);
	}

	/**
	 * Create a new Int64 with the given data value.
	 * 
	 * @param data
	 *            The data value of the long.
	 */
	public Int64(long data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Int64.FIELD_DATA, data).build(),
				Int64.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this long.
	 * 
	 * @return The data value of this long.
	 */
	public long getData() {
		return this.data;
	}

	/**
	 * Create a clone of this Int64.
	 */
	@Override
	public Int64 clone() {
		return new Int64(this.data);
	}

	/**
	 * Create a new Int64 based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Int64 message based on the given JSON string.
	 */
	public static Int64 fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return Int64.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Int64 based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Int64 message based on the given Message.
	 */
	public static Int64 fromMessage(Message m) {
		// get it from the JSON object
		return Int64.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Int64 based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Int64 message based on the given JSON object.
	 */
	public static Int64 fromJsonObject(JsonObject jsonObject) {
		// check the fields
		long data = jsonObject.containsKey(Int64.FIELD_DATA) ? jsonObject
				.getJsonNumber(Int64.FIELD_DATA).longValue() : 0L;
		return new Int64(data);
	}
}
