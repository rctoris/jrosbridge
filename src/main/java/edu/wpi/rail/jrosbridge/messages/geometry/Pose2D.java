package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Pose2D message. This expresses a position and orientation
 * on a 2D manifold.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
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

	private final double x, y, theta;

	/**
	 * Create a new Pose2D with all 0s.
	 */
	public Pose2D() {
		this(0, 0, 0);
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
	 * Create a clone of this Pose2D.
	 */
	@Override
	public Pose2D clone() {
		return new Pose2D(this.x, this.y, this.theta);
	}

	/**
	 * Create a new Pose2D based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Pose2D message based on the given JSON string.
	 */
	public static Pose2D fromJsonString(String jsonString) {
		// convert to a message
		return Pose2D.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Pose2D based on the given Message. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Pose2D message based on the given Message.
	 */
	public static Pose2D fromMessage(Message m) {
		// get it from the JSON object
		return Pose2D.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Pose2D based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Pose2D message based on the given JSON object.
	 */
	public static Pose2D fromJsonObject(JsonObject jsonObject) {
		// check the fields
		double x = jsonObject.containsKey(Pose2D.FIELD_X) ? jsonObject
				.getJsonNumber(Pose2D.FIELD_X).doubleValue() : 0.0;
		double y = jsonObject.containsKey(Pose2D.FIELD_Y) ? jsonObject
				.getJsonNumber(Pose2D.FIELD_Y).doubleValue() : 0.0;
		double theta = jsonObject.containsKey(Pose2D.FIELD_THETA) ? jsonObject
				.getJsonNumber(Pose2D.FIELD_THETA).doubleValue() : 0.0;
		return new Pose2D(x, y, theta);
	}
}
