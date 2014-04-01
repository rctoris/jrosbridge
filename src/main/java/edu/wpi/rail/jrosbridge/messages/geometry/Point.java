package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Point message. This contains the position of a point in
 * free space.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
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

	private final double x, y, z;

	/**
	 * Create a new Point with all 0s.
	 */
	public Point() {
		this(0, 0, 0);
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
	 * Create a clone of this Point.
	 */
	@Override
	public Point clone() {
		return new Point(this.x, this.y, this.z);
	}

	/**
	 * Create a new Point based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Point message based on the given JSON string.
	 */
	public static Point fromJsonString(String jsonString) {
		// convert to a message
		return Point.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Point based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Point message based on the given Message.
	 */
	public static Point fromMessage(Message m) {
		// get it from the JSON object
		return Point.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Point based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Point message based on the given JSON object.
	 */
	public static Point fromJsonObject(JsonObject jsonObject) {
		// check the fields
		double x = jsonObject.containsKey(Point.FIELD_X) ? jsonObject
				.getJsonNumber(Point.FIELD_X).doubleValue() : 0.0;
		double y = jsonObject.containsKey(Point.FIELD_Y) ? jsonObject
				.getJsonNumber(Point.FIELD_Y).doubleValue() : 0.0;
		double z = jsonObject.containsKey(Point.FIELD_Z) ? jsonObject
				.getJsonNumber(Point.FIELD_Z).doubleValue() : 0.0;
		return new Point(x, y, z);
	}
}
