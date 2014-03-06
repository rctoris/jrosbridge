package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Time message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 6, 2014
 */
public class Time extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Time";

	private final edu.wpi.rail.jrosbridge.primitives.Time data;

	/**
	 * Create a new Time with a default of 0.
	 */
	public Time() {
		this(new edu.wpi.rail.jrosbridge.primitives.Time());
	}

	/**
	 * Create a new Time with the given time primitive.
	 * 
	 * @param data
	 *            The data value of this time.
	 */
	public Time(edu.wpi.rail.jrosbridge.primitives.Time data) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(Time.FIELD_DATA, data.toJsonObject()).build(), Time.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this Time.
	 * 
	 * @return The data value of this Time.
	 */
	public edu.wpi.rail.jrosbridge.primitives.Time getData() {
		return this.data;
	}

	/**
	 * Create a deep clone of this Time.
	 */
	@Override
	public Time clone() {
		return new Time(this.data.clone());
	}
}
