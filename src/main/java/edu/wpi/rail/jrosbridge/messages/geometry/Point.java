package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Point message. This contains the position of a point in
 * free space.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 25, 2014
 */
public class Point extends Message {

	/**
	 * The name of the x field for the message.
	 */
	public static final String FIELD_X = "x";

	/**
	 * The name of the y field for the message.
	 */
	public static final String FIELD_Y = "y";

	/**
	 * The name of the z field for the message.
	 */
	public static final String FIELD_Z = "z";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/Point";

	private double x, y, z;

	/**
	 * Create a new Point with all 0s.
	 */
	public Point() {
		this(0, 0, 0);
	}

	/**
	 * Create a new Point with the given x value (y and z values will 0).
	 * 
	 * @param x
	 *            The x value of the point.
	 */
	public Point(double x) {
		this(x, 0, 0);
	}

	/**
	 * Create a new Point with the given x and y values (the z value will be set
	 * to 0).
	 * 
	 * @param x
	 *            The x value of the point.
	 * @param y
	 *            The y value of the point.
	 */
	public Point(double x, double y) {
		this(x, y, 0);
	}

	/**
	 * Create a new Point with the given values.
	 * 
	 * @param x
	 *            The x value of the point.
	 * @param y
	 *            The y value of the point.
	 * @param z
	 *            The z value of the point.
	 */
	public Point(double x, double y, double z) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Point.FIELD_X, x)
				.add(Point.FIELD_Y, y).add(Point.FIELD_Z, z).build(),
				Point.TYPE);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Get the x value of this point.
	 * 
	 * @return The x value of this point.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Get the y value of this point.
	 * 
	 * @return The y value of this point.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Get the z value of this point.
	 * 
	 * @return The z value of this point.
	 */
	public double getZ() {
		return this.z;
	}

	/**
	 * Create a deep clone of this Point.
	 */
	@Override
	public Point clone() {
		return new Point(this.x, this.y, this.z);
	}
}
