package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Int8 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 6, 2014
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
	 * Create a deep clone of this Int8.
	 */
	@Override
	public Int8 clone() {
		return new Int8(this.data);
	}
}
