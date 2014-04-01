package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Vector3 message. This represents a vector in free space.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
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

	private final double x, y, z;

	/**
	 * Create a new Vector3 with all 0s.
	 */
	public Vector3() {
		this(0, 0, 0);
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
	 * Create a clone of this Vector3.
	 */
	@Override
	public Vector3 clone() {
		return new Vector3(this.x, this.y, this.z);
	}

	/**
	 * Create a new Vector3 based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Vector3 message based on the given JSON string.
	 */
	public static Vector3 fromJsonString(String jsonString) {
		// convert to a message
		return Vector3.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Vector3 based on the given Message. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Vector3 message based on the given Message.
	 */
	public static Vector3 fromMessage(Message m) {
		// get it from the JSON object
		return Vector3.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Vector3 based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Vector3 message based on the given JSON object.
	 */
	public static Vector3 fromJsonObject(JsonObject jsonObject) {
		// check the fields
		double x = jsonObject.containsKey(Vector3.FIELD_X) ? jsonObject
				.getJsonNumber(Vector3.FIELD_X).doubleValue() : 0.0;
		double y = jsonObject.containsKey(Vector3.FIELD_Y) ? jsonObject
				.getJsonNumber(Vector3.FIELD_Y).doubleValue() : 0.0;
		double z = jsonObject.containsKey(Vector3.FIELD_Z) ? jsonObject
				.getJsonNumber(Vector3.FIELD_Z).doubleValue() : 0.0;
		return new Vector3(x, y, z);
	}
}
