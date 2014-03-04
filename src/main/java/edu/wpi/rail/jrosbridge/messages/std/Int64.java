package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Int64 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 4, 2014
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

	private long data;

	/**
	 * Create a new Int64 with a default of 0.
	 */
	public Int64() {
		this((long) 0);
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
	 * Create a deep clone of this Int64.
	 */
	@Override
	public Int64 clone() {
		return new Int64(this.data);
	}
}
