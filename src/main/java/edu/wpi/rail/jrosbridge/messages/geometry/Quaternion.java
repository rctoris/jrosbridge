package edu.wpi.rail.jrosbridge.messages.geometry;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Quaternion message. A Quaternion represents a 4-dimensional
 * vector with x, y, z, and w components.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 20, 2014
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
	 * Create a new quaternion with all 0s.
	 */
	public Quaternion() {
		this(0);
	}

	/**
	 * Create a new quaternion with the given x value (y, z, and w values will
	 * 0).
	 * 
	 * @param x
	 *            The x value of the quaternion.
	 */
	public Quaternion(double x) {
		this(x, 0);
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
		this(x, y, 0);
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
		this.setValues(x, y, z, w);
	}

	/**
	 * Create a new quaternion based on the given JSON string. Any missing
	 * values are initialized to 0
	 * 
	 * @param json
	 *            The JSON String to parse.
	 */
	public Quaternion(String json) {
		// parse and pass it to the JSON constructor
		this(Json.createReader(new StringReader(json)).readObject());
	}

	/**
	 * Create a new quaternion based on the given JSON object. Any missing
	 * values are initialized to 0
	 * 
	 * @param jsonObject
	 *            The JSON object containing the message data.
	 */
	public Quaternion(JsonObject jsonObject) {
		double x = 0;
		double y = 0;
		double z = 0;
		double w = 0;

		// try to get the information
		JsonNumber xJson = jsonObject.getJsonNumber(Quaternion.FIELD_X);
		if (xJson != null) {
			x = xJson.doubleValue();
		}
		JsonNumber yJson = jsonObject.getJsonNumber(Quaternion.FIELD_Y);
		if (yJson != null) {
			y = yJson.doubleValue();
		}
		JsonNumber zJson = jsonObject.getJsonNumber(Quaternion.FIELD_Z);
		if (zJson != null) {
			z = zJson.doubleValue();
		}
		JsonNumber wJson = jsonObject.getJsonNumber(Quaternion.FIELD_W);
		if (wJson != null) {
			w = wJson.doubleValue();
		}

		this.setValues(x, y, z, w);
	}

	/**
	 * Set the values of the internal JSON object to the given values.
	 * 
	 * @param x
	 *            The x value of the message.
	 * @param y
	 *            The y value of the message.
	 * @param z
	 *            The z value of the message.
	 * @param w
	 *            The w value of the message.
	 */
	private void setValues(double x, double y, double z, double w) {
		this.jsonObject = Json.createObjectBuilder().add(Quaternion.FIELD_X, x)
				.add(Quaternion.FIELD_Y, y).add(Quaternion.FIELD_Z, z)
				.add(Quaternion.FIELD_W, w).build();
	}

	/**
	 * Get the x value of this quaternion.
	 * 
	 * @return The x value of this quaternion.
	 */
	public double getX() {
		return jsonObject.getJsonNumber(Quaternion.FIELD_X).doubleValue();
	}

	/**
	 * Set the x value of this quaternion.
	 * 
	 * @param x
	 *            The x value of this quaternion.
	 */
	public void setX(double x) {
		this.setValues(x, this.getY(), this.getZ(), this.getW());
	}

	/**
	 * Get the y value of this quaternion.
	 * 
	 * @return The y value of this quaternion.
	 */
	public double getY() {
		return jsonObject.getJsonNumber(Quaternion.FIELD_Y).doubleValue();
	}

	/**
	 * Set the x value of this quaternion.
	 * 
	 * @param y
	 *            The y value of this quaternion.
	 */
	public void setY(double y) {
		this.setValues(this.getX(), y, this.getZ(), this.getW());
	}

	/**
	 * Get the z value of this quaternion.
	 * 
	 * @return The z value of this quaternion.
	 */
	public double getZ() {
		return jsonObject.getJsonNumber(Quaternion.FIELD_Z).doubleValue();
	}

	/**
	 * Set the z value of this quaternion.
	 * 
	 * @param z
	 *            The z value of this quaternion.
	 */
	public void setZ(double z) {
		this.setValues(this.getX(), this.getY(), z, this.getW());
	}

	/**
	 * Get the w value of this quaternion.
	 * 
	 * @return The w value of this quaternion.
	 */
	public double getW() {
		return jsonObject.getJsonNumber(Quaternion.FIELD_Z).doubleValue();
	}

	/**
	 * Set the w value of this quaternion.
	 * 
	 * @param w
	 *            The w value of this quaternion.
	 */
	public void setW(double w) {
		this.setValues(this.getX(), this.getY(), this.getZ(), w);
	}

	/**
	 * Perform a conjugation on this quaternion.
	 */
	public void conjugate() {
		this.setValues(-this.getX(), -this.getY(), -this.getZ(), this.getW());
	}

	/**
	 * Perform a normalization on this quaternion.
	 */
	public void normalize() {
		double curX = this.getX();
		double curY = this.getY();
		double curZ = this.getZ();
		double curW = this.getW();

		double l = Math.sqrt(curX * curX + curY * curY + curZ * curZ + curW
				* curW);
		if (l == 0) {
			this.setValues(0, 0, 0, 1);
		} else {
			l = 1 / l;
			this.setValues(curX * l, curY * l, curZ * l, curW * l);
		}
	}

	/**
	 * Convert this quaternion into its inverse.
	 */
	public void invert() {
		this.conjugate();
		this.normalize();
	}

	/**
	 * Set the values of this quaternion to the product of itself and the given
	 * quaternion.
	 * 
	 * @param q
	 *            The quaternion to multiply with.
	 */
	public void multiply(Quaternion q) {
		double newX = this.x * q.getW() + this.y * q.getZ() - this.z * q.getY()
				+ this.w * q.getX();
		double newY = -this.x * q.getZ() + this.y * q.getW() + this.z
				* q.getX() + this.w * q.getY();
		double newZ = this.x * q.getY() - this.y * q.getX() + this.z * q.getW()
				+ this.w * q.getZ();
		double newW = -this.x * q.getX() - this.y * q.getY() - this.z
				* q.getZ() + this.w * q.getW();

		this.setValues(newX, newY, newZ, newX);
	}

	/**
	 * Check if the given Object is the same as the quaternion. Two quaternions
	 * are the same if their x, y, z, and w values are equal.
	 * 
	 * @param o
	 *            The Object to check.
	 * @return If the Object is equal to this Quaternion.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof Quaternion) {
			Quaternion q = (Quaternion) o;
			return q.getX() == this.getX() && q.getY() == this.getY()
					&& q.getZ() == this.getZ() && q.getW() == this.getW();
		} else {
			return false;
		}
	}

	/**
	 * Create a clone of this Quaternion.
	 * 
	 * @return A clone of this Quaternion.
	 */
	public Quaternion clone() {
		return new Quaternion(this.jsonObject);
	}
}
