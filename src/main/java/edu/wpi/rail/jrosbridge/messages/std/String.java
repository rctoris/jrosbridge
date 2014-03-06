package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/String message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 6, 2014
 */
public class String extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/String";

	private final java.lang.String data;

	/**
	 * Create a new String with an empty String.
	 */
	public String() {
		this("");
	}

	/**
	 * Create a new String with the given data value.
	 * 
	 * @param data
	 *            The data value of the String.
	 */
	public String(java.lang.String data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(String.FIELD_DATA, data).build(),
				String.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this String.
	 * 
	 * @return The data value of this String.
	 */
	public java.lang.String getData() {
		return this.data;
	}

	/**
	 * Create a deep clone of this String.
	 */
	@Override
	public String clone() {
		return new String(new java.lang.String(this.data));
	}
}
