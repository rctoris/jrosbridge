package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Bool message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 6, 2014
 */
public class Bool extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Bool";

	private final boolean data;

	/**
	 * Create a new Bool with a default of false.
	 */
	public Bool() {
		this(false);
	}

	/**
	 * Create a new Bool with the given data value.
	 * 
	 * @param data
	 *            The data value of the boolean.
	 */
	public Bool(boolean data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Bool.FIELD_DATA, data).build(),
				Bool.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this boolean.
	 * 
	 * @return The data value of this boolean.
	 */
	public boolean getData() {
		return this.data;
	}

	/**
	 * Create a deep clone of this Bool.
	 */
	@Override
	public Bool clone() {
		return new Bool(this.data);
	}
}
