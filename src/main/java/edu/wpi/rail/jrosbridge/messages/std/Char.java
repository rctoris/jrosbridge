package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Char message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 4, 2014
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

	private char data;

	/**
	 * Create a new Char with a default of 0.
	 */
	public Char() {
		this((char) 0);
	}

	/**
	 * Create a new Char with the given data value.
	 * 
	 * @param data
	 *            The data value of the char.
	 */
	public Char(char data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Char.FIELD_DATA, data).build(),
				Char.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this char.
	 * 
	 * @return The data value of this char.
	 */
	public char getData() {
		return this.data;
	}

	/**
	 * Create a deep clone of this Char.
	 */
	@Override
	public Char clone() {
		return new Char(this.data);
	}
}
