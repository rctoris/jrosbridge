package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Byte message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 6, 2014
 */
public class Byte extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Byte";

	private final byte data;

	/**
	 * Create a new Byte with a default of 0.
	 */
	public Byte() {
		this((byte) 0);
	}

	/**
	 * Create a new Byte with the given data value.
	 * 
	 * @param data
	 *            The data value of the byte.
	 */
	public Byte(byte data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Byte.FIELD_DATA, data).build(),
				Byte.TYPE);
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
	 * Create a deep clone of this Byte.
	 */
	@Override
	public Byte clone() {
		return new Byte(this.data);
	}
}
