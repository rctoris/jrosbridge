package edu.wpi.rail.jrosbridge.primitives;

import javax.json.Json;

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

	public final int secs, nsecs;

	public TimeBase(String type) {
		this(0, 0, type);
	}

	public TimeBase(double sec, String type) {
		this((long) (sec * 1000000000), type);
	}

	public TimeBase(long nano, String type) {
		// extract seconds and nanoseconds
		this((int) (nano / 1000000000), (int) (nano % 1000000000), type);
	}

	public TimeBase(int secs, int nsecs, String type) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Duration.FIELD_SECS, secs)
				.add(Duration.FIELD_NSECS, nsecs).build(), type);
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

	public boolean isZero() {
		return (this.secs + this.nsecs) == 0;
	}

	public double toSec() {
		return this.secs + 1e-9 * (double) this.nsecs;
	}

	public long toNSec() {
		return (this.secs * 1000000000) + this.nsecs;
	}

	@Override
	public int compareTo(TimeBase<T> t) {
		return Double.compare(this.toSec(), t.toSec());
	}

	public abstract T add(T t);

	public abstract T subtract(T t);

	/**
	 * Create a clone of this TimeBase.
	 * 
	 * @return A clone of this TimeBase.
	 */
	@Override
	public abstract T clone();
}
