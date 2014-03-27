package edu.wpi.rail.jrosbridge.primitives;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

public class Duration extends TimeBase<Duration> {

	/**
	 * The primitive type.
	 */
	public static final String TYPE = "duration";

	/**
	 * Create a new Duration with a default of 0.
	 */
	public Duration() {
		super(Duration.TYPE);
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
		super(secs, nsecs, Duration.TYPE);
	}

	public Duration(double sec) {
		super(sec, Duration.TYPE);
	}

	public Duration(long nano) {
		super(nano, Duration.TYPE);
	}

	public Duration add(Duration d) {
		return new Duration(this.toSec() + d.toSec());
	}

	public Duration subtract(Duration d) {
		return new Duration(this.toSec() - d.toSec());
	}

	public boolean sleep() {
		try {
			Thread.sleep(this.secs * 1000, this.nsecs);
			return true;
		} catch (InterruptedException e) {
			return false;
		}
	}

	/**
	 * Create a clone of this Duration.
	 */
	@Override
	public Duration clone() {
		return new Duration(this.secs, this.nsecs);
	}

	/**
	 * Create a new Duration message based on the given seconds.
	 * 
	 * @param sec
	 *            The duration in seconds.
	 * 
	 * @return The new Duration primitive.
	 */
	public static Duration fromSec(double sec) {
		return new Duration(sec);
	}

	/**
	 * Create a new Duration message based on the given nanoseconds.
	 * 
	 * @param nano
	 *            The duration in nanoseconds.
	 * 
	 * @return The new Duration primitive.
	 */
	public static Duration fromNano(long nano) {
		return new Duration(nano);
	}

	/**
	 * Create a new Duration based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Duration message based on the given JSON string.
	 */
	public static Duration fromJsonString(String jsonString) {
		// convert to a JSON object
		return Duration.fromJsonObject(Json.createReader(
				new StringReader(jsonString)).readObject());
	}

	/**
	 * Create a new Duration based on the given JSON object. Any missing values
	 * will be set to their defaults.
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
