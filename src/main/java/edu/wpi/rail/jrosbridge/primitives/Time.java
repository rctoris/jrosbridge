package edu.wpi.rail.jrosbridge.primitives;

import java.io.StringReader;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;

public class Time extends TimeBase<Time> {

	/**
	 * The primitive type.
	 */
	public static final String TYPE = "time";

	/**
	 * Create a new Time with a default of 0.
	 */
	public Time() {
		super(Time.TYPE);
	}

	/**
	 * Create a new Time with the given seconds and nanoseconds values.
	 * 
	 * @param secs
	 *            The seconds value of this time.
	 * @param nsecs
	 *            The nanoseconds value of this time.
	 */
	public Time(int secs, int nsecs) {
		super(secs, nsecs, Time.TYPE);
	}

	public Time(double sec) {
		super(sec, Time.TYPE);
	}

	public Time(long nano) {
		super(nano, Time.TYPE);
	}

	public Time add(Time t) {
		return new Time(this.toSec() + t.toSec());
	}

	public Time subtract(Time t) {
		return new Time(this.toSec() - t.toSec());
	}

	public boolean isValid() {
		return !this.isZero();
	}

	/**
	 * Crate a new Java Date object based on this message.
	 * 
	 * @return A new Java Date object based on this message.
	 */
	public Date toDate() {
		// TODO
		return null;
	}

	public static boolean sleepUntil(Time t) {
		// use a duration to sleep with
		return Duration.fromSec(Time.now().subtract(t).toSec()).sleep();
	}

	/**
	 * Create a clone of this Time.
	 */
	@Override
	public Time clone() {
		return new Time(this.secs, this.nsecs);
	}

	/**
	 * Create a new Time message based on the current system time.
	 * 
	 * @return The new Time message.
	 */
	public static Time now() {
		return Time.fromNano(System.nanoTime());
	}

	/**
	 * Create a new Time message based on the given seconds.
	 * 
	 * @param sec
	 *            The time in seconds.
	 * 
	 * @return The new Time primitive.
	 */
	public static Time fromSec(double sec) {
		return new Time(sec);
	}

	/**
	 * Create a new Time message based on the given nanoseconds.
	 * 
	 * @param nano
	 *            The time in nanoseconds.
	 * 
	 * @return The new Time primitive.
	 */
	public static Time fromNano(long nano) {
		return new Time(nano);
	}

	public static Time fromDate(Date date) {
		// TODO
		return null;
	}

	/**
	 * Create a new Time based on the given JSON string. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Time message based on the given JSON string.
	 */
	public static Time fromJsonString(String jsonString) {
		// convert to a JSON object
		return Time.fromJsonObject(Json.createReader(
				new StringReader(jsonString)).readObject());
	}

	/**
	 * Create a new Time based on the given JSON object. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Time message based on the given JSON object.
	 */
	public static Time fromJsonObject(JsonObject jsonObject) {
		// check the fields
		int secs = jsonObject.containsKey(Time.FIELD_SECS) ? jsonObject
				.getInt(Time.FIELD_SECS) : 0;
		int nsecs = jsonObject.containsKey(Time.FIELD_NSECS) ? jsonObject
				.getInt(Time.FIELD_NSECS) : 0;
		return new Time(secs, nsecs);
	}
}
