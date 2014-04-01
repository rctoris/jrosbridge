package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

/**
 * The std_msgs/UInt8 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class UInt8 extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/UInt8";

	private final byte data;

	/**
	 * Create a new UInt8 with a default of 0.
	 */
	public UInt8() {
		this((byte) 0);
	}

	/**
	 * Create a new UInt8 with the given data value treated as a 8-bit unsigned
	 * integer.
	 * 
	 * @param data
	 *            The data value of the byte.
	 */
	public UInt8(byte data) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(UInt8.FIELD_DATA, Primitive.fromUInt8(data)).build(),
				UInt8.TYPE);
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
	 * Create a clone of this UInt8.
	 */
	@Override
	public UInt8 clone() {
		return new UInt8(this.data);
	}

	/**
	 * Create a new UInt8 based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A UInt8 message based on the given JSON string.
	 */
	public static UInt8 fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return UInt8.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new UInt8 based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A UInt8 message based on the given Message.
	 */
	public static UInt8 fromMessage(Message m) {
		// get it from the JSON object
		return UInt8.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new UInt8 based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A UInt8 message based on the given JSON object.
	 */
	public static UInt8 fromJsonObject(JsonObject jsonObject) {
		// check the fields
		byte data = jsonObject.containsKey(UInt8.FIELD_DATA) ? Primitive
				.toUInt8((short) jsonObject.getInt(UInt8.FIELD_DATA)) : 0;
		return new UInt8(data);
	}
}
