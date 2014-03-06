package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Int16 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 6, 2014
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
	 * Create a deep clone of this Int16.
	 */
	@Override
	public Int16 clone() {
		return new Int16(this.data);
	}
}
