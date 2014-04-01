package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Quaternion message. This represents an orientation in free
 * space in quaternion form.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
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

	private final double x, y, z, w;

	/**
	 * Create a new Quaternion with all 0s.
	 */
	public Quaternion() {
		this(0, 0, 0, 0);
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
	 * Create a clone of this Quaternion.
	 */
	@Override
	public Quaternion clone() {
		return new Quaternion(this.x, this.y, this.z, this.w);
	}

	/**
	 * Create a new Quaternion based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Quaternion message based on the given JSON string.
	 */
	public static Quaternion fromJsonString(String jsonString) {
		// convert to a message
		return Quaternion.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Quaternion based on the given Message. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Quaternion message based on the given Message.
	 */
	public static Quaternion fromMessage(Message m) {
		// get it from the JSON object
		return Quaternion.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Quaternion based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Quaternion message based on the given JSON object.
	 */
	public static Quaternion fromJsonObject(JsonObject jsonObject) {
		// check the fields
		double x = jsonObject.containsKey(Quaternion.FIELD_X) ? jsonObject
				.getJsonNumber(Quaternion.FIELD_X).doubleValue() : 0.0;
		double y = jsonObject.containsKey(Quaternion.FIELD_Y) ? jsonObject
				.getJsonNumber(Quaternion.FIELD_Y).doubleValue() : 0.0;
		double z = jsonObject.containsKey(Quaternion.FIELD_Z) ? jsonObject
				.getJsonNumber(Quaternion.FIELD_Z).doubleValue() : 0.0;
		double w = jsonObject.containsKey(Quaternion.FIELD_W) ? jsonObject
				.getJsonNumber(Quaternion.FIELD_W).doubleValue() : 0.0;
		return new Quaternion(x, y, z, w);
	}
}
