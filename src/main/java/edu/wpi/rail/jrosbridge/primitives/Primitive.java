package edu.wpi.rail.jrosbridge.primitives;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.core.JsonWrapper;

/**
 * Primitive objects are used as a wrapper for non-native ROS primitives. These
 * primitives act as wrappers around JSON objects.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version March 8, 2014
 */
public abstract class Primitive extends JsonWrapper {

	/**
	 * The String representation of an empty primitive in JSON.
	 */
	public static final String EMPTY_MESSAGE = JsonWrapper.EMPTY_JSON;

	private String primitiveType;

	/**
	 * Create a Primitive based on the given String representation of a JSON
	 * object.
	 * 
	 * @param jsonString
	 *            The JSON String to parse.
	 * @param primitiveType
	 *            The type of the primitive (e.g., "geometry_msgs/Twist").
	 */
	public Primitive(String jsonString, String primitiveType) {
		// parse and pass it to the JSON constructor
		this(Json.createReader(new StringReader(jsonString)).readObject(),
				primitiveType);
	}

	/**
	 * Create a Primitive based on the given JSON object.
	 * 
	 * @param jsonObject
	 *            The JSON object containing the primitive data.
	 * @param primitiveType
	 *            The type of the primitive (e.g., "time").
	 */
	public Primitive(JsonObject jsonObject, String primitiveType) {
		// setup the JSON information
		super(jsonObject);
		// set the type
		this.primitiveType = primitiveType;
	}

	/**
	 * Get the type of the primitive.
	 * 
	 * @return The type of the primitive.
	 */
	public String getPrimitiveType() {
		return this.primitiveType;
	}

	/**
	 * Set the type of the primitive.
	 * 
	 * @param primitiveType
	 *            The type of the primitive (e.g., "time").
	 */
	public void setPrimitiveType(String primitiveType) {
		this.primitiveType = primitiveType;
	}

	public static byte toUInt8(short value) {
		// zero out the high 8-bits
		short tmp = (short) ((value >> 8) << 8);
		return (byte) (value - tmp);
	}

	public static int toUInt32(long value) {
		// zero out the high 32-bits
		long tmp = (long) ((value >> 32) << 32);
		return (int) (value - tmp);
	}
}
