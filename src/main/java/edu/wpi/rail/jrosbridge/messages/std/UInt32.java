package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

/**
 * The std_msgs/UInt32 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class UInt32 extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/UInt32";

	private final int data;

	/**
	 * Create a new UInt32 with a default of 0.
	 */
	public UInt32() {
		this(0);
	}

	/**
	 * Create a new UInt32 with the given data value treated as a 32-bit
	 * unsigned integer.
	 * 
	 * @param data
	 *            The data value of the int.
	 */
	public UInt32(int data) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(UInt32.FIELD_DATA, Primitive.fromUInt32(data)).build(),
				UInt32.TYPE);
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
	 * Create a clone of this UInt32.
	 */
	@Override
	public UInt32 clone() {
		return new UInt32(this.data);
	}

	/**
	 * Create a new UInt32 based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A UInt32 message based on the given JSON string.
	 */
	public static UInt32 fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return UInt32.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new UInt32 based on the given Message. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A UInt32 message based on the given Message.
	 */
	public static UInt32 fromMessage(Message m) {
		// get it from the JSON object
		return UInt32.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new UInt32 based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A UInt32 message based on the given JSON object.
	 */
	public static UInt32 fromJsonObject(JsonObject jsonObject) {
		// check the fields
		int data = jsonObject.containsKey(UInt32.FIELD_DATA) ? Primitive
				.toUInt32(jsonObject.getJsonNumber(UInt32.FIELD_DATA)
						.longValue()) : 0;
		return new UInt32(data);
	}
}
