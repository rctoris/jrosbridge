package edu.wpi.rail.jrosbridge.primitives;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

public class Duration extends Primitive {

	/**
	 * The name of the seconds field for the message.
	 */
	public static final String FIELD_SECS = "secs";
	/**
	 * The name of the nanoseconds field for the message.
	 */
	public static final String FIELD_NSECS = "nsecs";

	/**
	 * The primitive type.
	 */
	public static final String TYPE = "duration";

	private int secs, nsecs;

	/**
	 * Create a new Duration with a default of 0.
	 */
	public Duration() {
		this(0, 0);
	}

	/**
	 * Create a new Duration with the given seconds value (nsecs will be set to 0).
	 * 
	 * @param secs
	 *            The seconds value of this duration.
	 */
	public Duration(int secs) {
		this(secs, 0);
	}

	/**
	 * Create a new Duration with the given seconds and nanoseconds values.
	 * 
	 * @param secs
	 *            The seconds value of this duration.
	 * @param nsecs
	 *            The nanoseconds value of this duration.
	 */
	public Duration(int secs, int nsecs) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Duration.FIELD_SECS, secs)
				.add(Duration.FIELD_NSECS, nsecs).build(), Duration.TYPE);
		this.secs = secs;
		this.nsecs = nsecs;
	}

	/**
	 * Get the seconds value of this duration.
	 * 
	 * @return The seconds value of this duration.
	 */
	public int getSecs() {
		return this.secs;
	}

	/**
	 * Get the nanoseconds value of this duration.
	 * 
	 * @return The nanoseconds value of this duration.
	 */
	public int getNsecs() {
		return this.secs;
	}

	/**
	 * Create a clone of this Duration.
	 */
	@Override
	public Duration clone() {
		return new Duration(this.secs, this.nsecs);
	}

	/**
	 * Create a new Duration message based on the given nanoseconds duration.
	 * 
	 * @param nanoDuration
	 *            The duration to create a message from in nanoseconds.
	 * 
	 * @return The new Duration message.
	 */
	public static Duration fromNanoDuration(long nanoDuration) {
		double conversion = (double) nanoDuration / 1000000000.0;

		// extract seconds and nanoseconds
		int secs = (int) conversion;
		int nsecs = (int) ((conversion - secs) * 1000000000.0);
		return new Duration(secs, nsecs);
	}

	/**
	 * Create a new Duration based on the given JSON string. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Duration message based on the given JSON string.
	 */
	public static Duration fromJsonString(String jsonString) {
		// convert to a message
		return Duration.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Duration based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Duration message based on the given Message.
	 */
	public static Duration fromMessage(Message m) {
		// get it from the JSON object
		return Duration.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Duration based on the given JSON object. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Duration message based on the given JSON object.
	 */
	public static Duration fromJsonObject(JsonObject jsonObject) {
		// check the fields
		int secs = jsonObject.containsKey(Duration.FIELD_SECS) ? jsonObject
				.getInt(Duration.FIELD_SECS) : 0;
		int nsecs = jsonObject.containsKey(Duration.FIELD_NSECS) ? jsonObject
				.getInt(Duration.FIELD_NSECS) : 0;
		return new Duration(secs, nsecs);
	}

}
