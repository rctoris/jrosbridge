package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

/**
 * The std_msgs/Char message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Char extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Char";

	private final byte data;

	/**
	 * Create a new Char with a default of 0.
	 */
	public Char() {
		this((byte) 0);
	}

	/**
	 * Create a new Char with the given data value treated as an unsigned 8-bit
	 * integer.
	 * 
	 * @param data
	 *            The data value of the char.
	 */
	public Char(byte data) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(Char.FIELD_DATA, Primitive.fromUInt8(data)).build(),
				Char.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this char.
	 * 
	 * @return The data value of this char.
	 */
	public byte getData() {
		return this.data;
	}

	/**
	 * Create a clone of this Char.
	 */
	@Override
	public Char clone() {
		return new Char(this.data);
	}

	/**
	 * Create a new Char based on the given JSON string. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Char message based on the given JSON string.
	 */
	public static Char fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return Char.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Char based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Char message based on the given Message.
	 */
	public static Char fromMessage(Message m) {
		// get it from the JSON object
		return Char.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Char based on the given JSON object. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Char message based on the given JSON object.
	 */
	public static Char fromJsonObject(JsonObject jsonObject) {
		// check the fields
		short data16 = jsonObject.containsKey(Char.FIELD_DATA) ? (short) jsonObject
				.getInt(Char.FIELD_DATA) : 0;

		// convert to a 8-bit number
		byte data8 = Primitive.toUInt8(data16);
		return new Char(data8);
	}
}
