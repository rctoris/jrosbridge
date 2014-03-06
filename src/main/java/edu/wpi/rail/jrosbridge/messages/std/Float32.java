package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Float32 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 6, 2014
 */
public class Float32 extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Float32";

	private final float data;

	/**
	 * Create a new Float32 with a default of 0.
	 */
	public Float32() {
		this(0f);
	}

	/**
	 * Create a new Float32 with the given data value.
	 * 
	 * @param data
	 *            The data value of the float.
	 */
	public Float32(float data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Float32.FIELD_DATA, data).build(),
				Float32.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this float.
	 * 
	 * @return The data value of this float.
	 */
	public float getData() {
		return this.data;
	}

	/**
	 * Create a deep clone of this Float32.
	 */
	@Override
	public Float32 clone() {
		return new Float32(this.data);
	}
}
