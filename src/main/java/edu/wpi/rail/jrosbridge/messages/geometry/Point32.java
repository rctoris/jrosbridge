package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

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
 * @version April 1, 2014
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

	private final float x, y, z;

	/**
	 * Create a new Point32 with all 0s.
	 */
	public Point32() {
		this(0, 0, 0);
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
	 * Create a clone of this Point32.
	 */
	@Override
	public Point32 clone() {
		return new Point32(this.x, this.y, this.z);
	}

	/**
	 * Create a new Point32 based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Point32 message based on the given JSON string.
	 */
	public static Point32 fromJsonString(String jsonString) {
		// convert to a message
		return Point32.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Point32 based on the given Message. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Point32 message based on the given Message.
	 */
	public static Point32 fromMessage(Message m) {
		// get it from the JSON object
		return Point32.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Point32 based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Point32 message based on the given JSON object.
	 */
	public static Point32 fromJsonObject(JsonObject jsonObject) {
		// check the fields
		float x = jsonObject.containsKey(Point32.FIELD_X) ? (float) jsonObject
				.getJsonNumber(Point32.FIELD_X).doubleValue() : 0.0f;
		float y = jsonObject.containsKey(Point32.FIELD_Y) ? (float) jsonObject
				.getJsonNumber(Point32.FIELD_Y).doubleValue() : 0.0f;
		float z = jsonObject.containsKey(Point32.FIELD_Z) ? (float) jsonObject
				.getJsonNumber(Point32.FIELD_Z).doubleValue() : 0.0f;
		return new Point32(x, y, z);
	}
}
