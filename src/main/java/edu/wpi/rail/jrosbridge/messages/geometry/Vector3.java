package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Vector3 message. This represents a vector in free space.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 25, 2014
 */
public class Vector3 extends Message {

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
	public static final String TYPE = "geometry_msgs/Vector3";

	private double x, y, z;

	/**
	 * Create a new Vector3 with all 0s.
	 */
	public Vector3() {
		this(0, 0, 0);
	}

	/**
	 * Create a new Vector3 with the given x value (y and z values will 0).
	 * 
	 * @param x
	 *            The x value of the vector.
	 */
	public Vector3(double x) {
		this(x, 0, 0);
	}

	/**
	 * Create a new Vector3 with the given x and y values (the z value will be
	 * set to 0).
	 * 
	 * @param x
	 *            The x value of the vector.
	 * @param y
	 *            The y value of the vector.
	 */
	public Vector3(double x, double y) {
		this(x, y, 0);
	}

	/**
	 * Create a new Vector3 with the given values.
	 * 
	 * @param x
	 *            The x value of the vector.
	 * @param y
	 *            The y value of the vector.
	 * @param z
	 *            The z value of the vector.
	 */
	public Vector3(double x, double y, double z) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Vector3.FIELD_X, x)
				.add(Vector3.FIELD_Y, y).add(Vector3.FIELD_Z, z).build(),
				Vector3.TYPE);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Get the x value of this vector.
	 * 
	 * @return The x value of this vector.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Get the y value of this vector.
	 * 
	 * @return The y value of this vector.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Get the z value of this vector.
	 * 
	 * @return The z value of this vector.
	 */
	public double getZ() {
		return this.z;
	}

	/**
	 * Create a deep clone of this Vector3.
	 */
	@Override
	public Vector3 clone() {
		return new Vector3(this.x, this.y, this.z);
	}
}
