package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Pose2D message. This expresses a position and orientation
 * on a 2D manifold.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 25, 2014
 */
public class Pose2D extends Message {

	/**
	 * The name of the x field for the message.
	 */
	public static final String FIELD_X = "x";

	/**
	 * The name of the y field for the message.
	 */
	public static final String FIELD_Y = "y";

	/**
	 * The name of the theta field for the message.
	 */
	public static final String FIELD_THETA = "theta";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/Pose2D";

	private double x, y, theta;

	/**
	 * Create a new Pose2D with all 0s.
	 */
	public Pose2D() {
		this(0, 0, 0);
	}

	/**
	 * Create a new Pose2D with the given x value (y and theta values will 0).
	 * 
	 * @param x
	 *            The x value of the pose.
	 */
	public Pose2D(double x) {
		this(x, 0, 0);
	}

	/**
	 * Create a new Pose2D with the given x and y values (the theta value will
	 * be set to 0).
	 * 
	 * @param x
	 *            The x value of the pose.
	 * @param y
	 *            The y value of the pose.
	 */
	public Pose2D(double x, double y) {
		this(x, y, 0);
	}

	/**
	 * Create a new Pose2D with the given values.
	 * 
	 * @param x
	 *            The x value of the pose.
	 * @param y
	 *            The y value of the pose.
	 * @param theta
	 *            The theta value of the pose.
	 */
	public Pose2D(double x, double y, double theta) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Pose2D.FIELD_X, x)
				.add(Pose2D.FIELD_Y, y).add(Pose2D.FIELD_THETA, theta).build(),
				Pose2D.TYPE);
		this.x = x;
		this.y = y;
		this.theta = theta;
	}

	/**
	 * Get the x value of this pose.
	 * 
	 * @return The x value of this pose.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Get the y value of this pose.
	 * 
	 * @return The y value of this pose.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Get the theta value of this pose.
	 * 
	 * @return The theta value of this pose.
	 */
	public double getTheta() {
		return this.theta;
	}

	/**
	 * Create a deep clone of this Pose2D.
	 */
	@Override
	public Pose2D clone() {
		return new Pose2D(this.x, this.y, this.theta);
	}
}
