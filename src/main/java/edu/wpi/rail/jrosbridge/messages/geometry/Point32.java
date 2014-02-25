package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Point32 message. This contains the position of a point in
 * free space(with 32 bits of precision). It is recommended to use Point32
 * wherever possible instead of Point32.
 * 
 * This recommendation is to promote interoperability.
 * 
 * This message is designed to take up less space when sending lots of points at
 * once, as in the case of a PointCloud.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 25, 2014
 */
public class Point32 extends Message {

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
	public static final String TYPE = "geometry_msgs/Point32";

	private float x, y, z;

	/**
	 * Create a new Point32 with all 0s.
	 */
	public Point32() {
		this(0, 0, 0);
	}

	/**
	 * Create a new Point32 with the given x value (y and z values will 0).
	 * 
	 * @param x
	 *            The x value of the point.
	 */
	public Point32(float x) {
		this(x, 0, 0);
	}

	/**
	 * Create a new Point32 with the given x and y values (the z value will be
	 * set to 0).
	 * 
	 * @param x
	 *            The x value of the point.
	 * @param y
	 *            The y value of the point.
	 */
	public Point32(float x, float y) {
		this(x, y, 0);
	}

	/**
	 * Create a new Point32 with the given values.
	 * 
	 * @param x
	 *            The x value of the point.
	 * @param y
	 *            The y value of the point.
	 * @param z
	 *            The z value of the point.
	 */
	public Point32(float x, float y, float z) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Point32.FIELD_X, x)
				.add(Point32.FIELD_Y, y).add(Point32.FIELD_Z, z).build(),
				Point32.TYPE);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Get the x value of this point.
	 * 
	 * @return The x value of this point.
	 */
	public float getX() {
		return this.x;
	}

	/**
	 * Get the y value of this point.
	 * 
	 * @return The y value of this point.
	 */
	public float getY() {
		return this.y;
	}

	/**
	 * Get the z value of this point.
	 * 
	 * @return The z value of this point.
	 */
	public float getZ() {
		return this.z;
	}

	/**
	 * Create a deep clone of this Point32.
	 */
	@Override
	public Point32 clone() {
		return new Point32(this.x, this.y, this.z);
	}
}
