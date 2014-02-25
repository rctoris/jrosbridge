package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Quaternion message. This represents an orientation in free
 * space in quaternion form.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 25, 2014
 */
public class Quaternion extends Message {

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
	 * The name of the z field for the message.
	 */
	public static final String FIELD_W = "w";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/Quaternion";

	private double x, y, z, w;

	/**
	 * Create a new Quaternion with all 0s.
	 */
	public Quaternion() {
		this(0, 0, 0, 0);
	}

	/**
	 * Create a new Quaternion with the given x value (y, z, and w values will
	 * 0).
	 * 
	 * @param x
	 *            The x value of the quaternion.
	 */
	public Quaternion(double x) {
		this(x, 0, 0, 0);
	}

	/**
	 * Create a new quaternion with the given x and y values (z and w values
	 * will be set to 0).
	 * 
	 * @param x
	 *            The x value of the quaternion.
	 * @param y
	 *            The y value of the quaternion.
	 */
	public Quaternion(double x, double y) {
		this(x, y, 0, 0);
	}

	/**
	 * Create a new quaternion with the given x, y, and z values (the w value
	 * will be set to 0).
	 * 
	 * @param x
	 *            The x value of the quaternion.
	 * @param y
	 *            The y value of the quaternion.
	 * @param z
	 *            The z value of the quaternion.
	 */
	public Quaternion(double x, double y, double z) {
		this(x, y, z, 0);
	}

	/**
	 * Create a new quaternion with the given values.
	 * 
	 * @param x
	 *            The x value of the quaternion.
	 * @param y
	 *            The y value of the quaternion.
	 * @param z
	 *            The z value of the quaternion.
	 * @param w
	 *            The w value of the quaternion.
	 */
	public Quaternion(double x, double y, double z, double w) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Quaternion.FIELD_X, x)
				.add(Quaternion.FIELD_Y, y).add(Quaternion.FIELD_Z, z)
				.add(Quaternion.FIELD_W, w).build(), Quaternion.TYPE);
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	/**
	 * Get the x value of this quaternion.
	 * 
	 * @return The x value of this quaternion.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Get the y value of this quaternion.
	 * 
	 * @return The y value of this quaternion.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Get the z value of this quaternion.
	 * 
	 * @return The z value of this quaternion.
	 */
	public double getZ() {
		return this.z;
	}

	/**
	 * Get the w value of this quaternion.
	 * 
	 * @return The w value of this quaternion.
	 */
	public double getW() {
		return this.w;
	}

	/**
	 * Create a deep clone of this Quaternion.
	 */
	@Override
	public Quaternion clone() {
		return new Quaternion(this.x, this.y, this.z, this.w);
	}
}
