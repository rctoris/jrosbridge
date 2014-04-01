package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

/**
 * The std_msgs/UInt16 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class UInt16 extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/UInt16";

	private final short data;

	/**
	 * Create a new UInt16 with a default of 0.
	 */
	public UInt16() {
		this((short) 0);
	}

	/**
	 * Create a new UInt16 with the given data value treated as a 16-bit
	 * unsigned integer.
	 * 
	 * @param data
	 *            The data value of the short.
	 */
	public UInt16(short data) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(UInt16.FIELD_DATA, Primitive.fromUInt16(data)).build(),
				UInt16.TYPE);
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
	 * Create a clone of this UInt16.
	 */
	@Override
	public UInt16 clone() {
		return new UInt16(this.data);
	}

	/**
	 * Create a new UInt16 based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A UInt16 message based on the given JSON string.
	 */
	public static UInt16 fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return UInt16.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new UInt16 based on the given Message. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A UInt16 message based on the given Message.
	 */
	public static UInt16 fromMessage(Message m) {
		// get it from the JSON object
		return UInt16.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new UInt16 based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A UInt16 message based on the given JSON object.
	 */
	public static UInt16 fromJsonObject(JsonObject jsonObject) {
		// check the fields
		short data = jsonObject.containsKey(UInt16.FIELD_DATA) ? Primitive
				.toUInt16(jsonObject.getInt(UInt16.FIELD_DATA)) : 0;
		return new UInt16(data);
	}
}
