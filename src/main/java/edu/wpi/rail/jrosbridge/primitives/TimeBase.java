package edu.wpi.rail.jrosbridge.primitives;

import javax.json.Json;

/**
 * The TimeBase class is an abstract implementation of common time/duration
 * primitive functions for ROS time and duration primitives. Since these
 * primitives are serialized like messages, they are immutable.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version April 1, 2014
 * 
 * @param <T>
 *            The type of TimeBase used in add and subtract.
 */
public abstract class TimeBase<T extends Primitive> extends Primitive implements
		Comparable<TimeBase<T>> {

	/**
	 * The name of the seconds field for the Primitive.
	 */
	public static final String FIELD_SECS = "secs";

	/**
	 * The name of the nanoseconds field for the Primitive.
	 */
	public static final String FIELD_NSECS = "nsecs";

	/**
	 * The number of milliseconds in a second.
	 */
	protected static final long SECS_TO_MILLI = 1000;

	/**
	 * The fraction of a second in a millisecond.
	 */
	protected static final double MILLI_TO_SECS = 0.001;

	/**
	 * The number of nanoseconds in a second.
	 */
	protected static final long SECS_TO_NSECS = 1000000000l;

	/**
	 * The fraction of a second in a nanosecond.
	 */
	protected static final double NSECS_TO_SECS = 1e-9;

	/**
	 * The number of milliseconds in a second.
	 */
	protected static final long MILLI_TO_NSECS = 1000000;

	/**
	 * The number of milliseconds in a second.
	 */
	protected static final double NSECS_TO_MILLI = 1e-6;

	public final int secs, nsecs;

	/**
	 * Create an empty TimeBase with the given type field.
	 * 
	 * @param type
	 *            The type of primitive.
	 */
	public TimeBase(String type) {
		this(0, 0, type);
	}

	/**
	 * Create a new TimeBase with the given time in seconds (and partial
	 * seconds).
	 * 
	 * @param sec
	 *            The time in seconds.
	 * @param type
	 *            The type of TimeBase primitive.
	 */
	public TimeBase(double sec, String type) {
		this((long) (sec * TimeBase.SECS_TO_NSECS), type);
	}

	/**
	 * Create a new TimeBase with the given time in nanoseconds.
	 * 
	 * @param nano
	 *            The time in nanoseconds.
	 * @param type
	 *            The type of TimeBase primitive.
	 */
	public TimeBase(long nano, String type) {
		// extract seconds and nanoseconds
		this((int) (nano / TimeBase.SECS_TO_NSECS),
				(int) (nano % TimeBase.SECS_TO_NSECS), type);
	}

	/**
	 * Create a new TimeBase with the given time in seconds and nanoseconds.
	 * 
	 * @param secs
	 *            The amount of seconds.
	 * @param nsecs
	 *            The amount of additional nanoseconds.
	 * @param type
	 *            The type of TimeBase primitive.
	 */
	public TimeBase(int secs, int nsecs, String type) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Duration.FIELD_SECS, secs)
				.add(Duration.FIELD_NSECS, nsecs).build(), type);
		this.secs = secs;
		this.nsecs = nsecs;
	}

	/**
	 * Get the seconds value of this TimeBase.
	 * 
	 * @return The seconds value of this TimeBase.
	 */
	public int getSecs() {
		return this.secs;
	}

	/**
	 * Get the nanoseconds value of this TimeBase.
	 * 
	 * @return The nanoseconds value of this TimeBase.
	 */
	public int getNsecs() {
		return this.nsecs;
	}

	/**
	 * Check if the value of this TimeBase is zero.
	 * 
	 * @return If the value of this TimeBase is zero.
	 */
	public boolean isZero() {
		return (this.secs + this.nsecs) == 0;
	}

	/**
	 * Convert this TimeBase to seconds (and partial seconds).
	 * 
	 * @return This TimeBase to seconds (and partial seconds).
	 */
	public double toSec() {
		return this.secs + (TimeBase.NSECS_TO_SECS * (double) this.nsecs);
	}

	/**
	 * Convert this TimeBase to nanoseconds.
	 * 
	 * @return This TimeBase to nanoseconds.
	 */
	public long toNSec() {
		return ((long) (this.secs * TimeBase.SECS_TO_NSECS))
				+ ((long) this.nsecs);
	}

	/**
	 * Compare the given TimeBase object to this one.
	 * 
	 * @param t
	 *            The TimeBase to compare to.
	 * @return 0 if the values are equal, less than 0 if t is less that this
	 *         TimeBase, and greater than 0 otherwise.
	 */
	@Override
	public int compareTo(TimeBase<T> t) {
		return Double.compare(this.toSec(), t.toSec());
	}

	/**
	 * Add the given type to this TimeBase and return a new object with that
	 * value.
	 * 
	 * @param t
	 *            Add the given type to this TimeBase.
	 * @return A new object with the new value.
	 */
	public abstract T add(T t);

	/**
	 * Subtract the given type to this TimeBase and return a new object with
	 * that value.
	 * 
	 * @param t
	 *            Subtract the given type to this TimeBase.
	 * @return A new object with the new value.
	 */
	public abstract T subtract(T t);

	/**
	 * Create a clone of this TimeBase.
	 * 
	 * @return A clone of this TimeBase.
	 */
	@Override
	public abstract T clone();
}
