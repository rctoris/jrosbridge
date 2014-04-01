package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

/**
 * The std_msgs/UInt64 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class UInt64 extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/UInt64";

	private final long data;

	/**
	 * Create a new UInt64 with a default of 0.
	 */
	public UInt64() {
		this(0L);
	}

	/**
	 * Create a new UInt64 with the given data value treated as a 64-bit
	 * unsigned integer.
	 * 
	 * @param data
	 *            The data value of the long.
	 */
	public UInt64(long data) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(UInt64.FIELD_DATA, Primitive.fromUInt64(data)).build(),
				UInt64.TYPE);
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
	 * Create a clone of this UInt64.
	 */
	@Override
	public UInt64 clone() {
		return new UInt64(this.data);
	}

	/**
	 * Create a new UInt64 based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A UInt64 message based on the given JSON string.
	 */
	public static UInt64 fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return UInt64.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new UInt64 based on the given Message. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A UInt64 message based on the given Message.
	 */
	public static UInt64 fromMessage(Message m) {
		// get it from the JSON object
		return UInt64.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new UInt64 based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A UInt64 message based on the given JSON object.
	 */
	public static UInt64 fromJsonObject(JsonObject jsonObject) {
		// check the fields
		long data = jsonObject.containsKey(UInt64.FIELD_DATA) ? Primitive
				.toUInt64(jsonObject.getJsonNumber(UInt64.FIELD_DATA)
						.bigIntegerValue()) : 0L;
		return new UInt64(data);
	}
}
