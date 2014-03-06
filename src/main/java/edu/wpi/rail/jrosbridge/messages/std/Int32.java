package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Int32 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 6, 2014
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
	 * Create a deep clone of this Int32.
	 */
	@Override
	public Int32 clone() {
		return new Int32(this.data);
	}
}
