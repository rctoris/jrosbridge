package edu.wpi.rail.jrosbridge.messages.geometry;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Vector3 message. A Vector3 represents a 3-dimensional
 * vector with x, y, and z components.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 20, 2014
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
	 * Create a new vector with all 0s.
	 */
	public Vector3() {
		this(0);
	}

	/**
	 * Create a new vector with the given x value (y and z values will 0).
	 * 
	 * @param x
	 *            The x value of the vector.
	 */
	public Vector3(double x) {
		this(x, 0);
	}

	/**
	 * Create a new vector with the given x and y values (the z value will be
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
	 * Create a new vector with the given values.
	 * 
	 * @param x
	 *            The x value of the vector.
	 * @param y
	 *            The y value of the vector.
	 * @param z
	 *            The z value of the vector.
	 */
	public Vector3(double x, double y, double z) {
		this.setValues(x, y, z);
	}

	/**
	 * Create a new vector based on the given JSON string. Any missing values
	 * are initialized to 0
	 * 
	 * @param json
	 *            The JSON String to parse.
	 */
	public Vector3(String json) {
		// parse and pass it to the JSON constructor
		this(Json.createReader(new StringReader(json)).readObject());
	}

	/**
	 * Create a new vector based on the given JSON object. Any missing values
	 * are initialized to 0
	 * 
	 * @param jsonObject
	 *            The JSON object containing the message data.
	 */
	public Vector3(JsonObject jsonObject) {
		double x = 0;
		double y = 0;
		double z = 0;

		// try to get the information
		JsonNumber xJson = jsonObject.getJsonNumber(Vector3.FIELD_X);
		if (xJson != null) {
			x = xJson.doubleValue();
		}
		JsonNumber yJson = jsonObject.getJsonNumber(Vector3.FIELD_Y);
		if (yJson != null) {
			y = yJson.doubleValue();
		}
		JsonNumber zJson = jsonObject.getJsonNumber(Vector3.FIELD_Z);
		if (zJson != null) {
			z = zJson.doubleValue();
		}

		this.setValues(x, y, z);
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
	 */
	private void setValues(double x, double y, double z) {
		this.jsonObject = Json.createObjectBuilder().add(Vector3.FIELD_X, x)
				.add(Vector3.FIELD_Y, y).add(Vector3.FIELD_Z, z).build();
	}

	/**
	 * Get the x value of this vector.
	 * 
	 * @return The x value of this vector.
	 */
	public double getX() {
		return jsonObject.getJsonNumber(Vector3.FIELD_X).doubleValue();
	}

	/**
	 * Set the x value of this vector.
	 * 
	 * @param x
	 *            The x value of this vector.
	 */
	public void setX(double x) {
		this.setValues(x, this.getY(), this.getZ());
	}

	/**
	 * Get the y value of this vector.
	 * 
	 * @return The y value of this vector.
	 */
	public double getY() {
		return jsonObject.getJsonNumber(Vector3.FIELD_Y).doubleValue();
	}

	/**
	 * Set the x value of this vector.
	 * 
	 * @param y
	 *            The y value of this vector.
	 */
	public void setY(double y) {
		this.setValues(this.getX(), y, this.getZ());
	}

	/**
	 * Get the z value of this vector.
	 * 
	 * @return The z value of this vector.
	 */
	public double getZ() {
		return jsonObject.getJsonNumber(Vector3.FIELD_Z).doubleValue();
	}

	/**
	 * Set the z value of this vector.
	 * 
	 * @param z
	 *            The z value of this vector.
	 */
	public void setZ(double z) {
		this.setValues(this.getX(), this.getY(), z);
	}

	/**
	 * Set the values of this vector to the sum of itself and the given vector.
	 * 
	 * @param v
	 *            The vector to add with.
	 */
	public void add(Vector3 v) {
		double x = this.getX() + v.getX();
		double y = this.getY() + v.getY();
		double z = this.getZ() + v.getZ();
		this.setValues(x, y, z);
	}

	/**
	 * Set the values of this vector to the difference of itself and the given
	 * vector.
	 * 
	 * @param v
	 *            The vector to subtract with.
	 */
	public void subtract(Vector3 v) {
		double x = this.getX() - v.getX();
		double y = this.getY() - v.getY();
		double z = this.getZ() - v.getZ();
		this.setValues(x, y, z);
	};

	/**
	 * Multiply the given Quaternion with this vector.
	 * 
	 * @param q
	 *            The quaternion to multiply with.
	 */
	public void multiplyQuaternion(Quaternion q) {
		double curX = this.getX();
		double curY = this.getY();
		double curZ = this.getZ();

		double ix = q.getW() * curX + q.getY() * curZ - q.getZ() * curY;
		double iy = q.getW() * curY + q.getZ() * curX - q.getX() * curZ;
		double iz = q.getW() * curZ + q.getX() * curY - q.getY() * curX;
		double iw = -q.getW() * curX - q.getY() * curY - q.getZ() * curZ;

		double x = ix * q.getW() + iw * -q.getX() + iy * -q.getZ() - iz
				* -q.getY();
		double y = iy * q.getW() + iw * -q.getY() + iz * -q.getX() - ix
				* -q.getZ();
		double z = iz * q.getW() + iw * -q.getZ() + ix * -q.getY() - iy
				* -q.getX();

		this.setValues(x, y, z);
	}

	/**
	 * Check if the given Object is the same as the vector. Two vectors are the
	 * same if their x, y, and z values are equal.
	 * 
	 * @param o
	 *            The Object to check.
	 * @return If the Object is equal to this Vector3.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof Vector3) {
			Vector3 p = (Vector3) o;
			return p.getX() == this.getX() && p.getY() == this.getY()
					&& p.getZ() == this.getZ();
		} else {
			return false;
		}
	}

	/**
	 * Create a clone of this Vector3.
	 * 
	 * @return A clone of this Vector3.
	 */
	public Vector3 clone() {
		return new Vector3(this.jsonObject);
	}
}
