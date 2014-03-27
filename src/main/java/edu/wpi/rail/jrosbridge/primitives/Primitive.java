package edu.wpi.rail.jrosbridge.primitives;

import java.io.StringReader;
import java.math.BigInteger;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.core.JsonWrapper;

/**
 * Primitive objects are used as a wrapper for non-native ROS primitives. These
 * primitives act as wrappers around JSON objects.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version March 27, 2014
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

	public static short fromUInt8(byte value) {
		return (short) (value & (short) 0xFF);
	}

	public static short[] fromUInt8(byte[] values) {
		short[] tmp = new short[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Primitive.fromUInt8(values[i]);
		}
		return tmp;
	}

	public static short toUInt16(int value) {
		// zero out the high 16-bits
		int tmp = (int) ((value >> 16) << 16);
		return (short) (value - tmp);
	}

	public static int fromUInt16(short value) {
		return (int) (value & 0xFFFF);
	}

	public static int[] fromUInt16(short[] values) {
		int[] tmp = new int[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Primitive.fromUInt16(values[i]);
		}
		return tmp;
	}

	public static int toUInt32(long value) {
		// zero out the high 32-bits
		long tmp = (long) ((value >> 32) << 32);
		return (int) (value - tmp);
	}

	public static long fromUInt32(int value) {
		return (long) (value & 0xFFFFFFFFL);
	}

	public static long[] fromUInt32(int[] values) {
		long[] tmp = new long[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Primitive.fromUInt32(values[i]);
		}
		return tmp;
	}

	public static long toUInt64(BigInteger value) {
		return value.longValue();
	}

	public static BigInteger fromUInt64(long value) {
		return BigInteger.valueOf(value).and(
				BigInteger.ONE.shiftLeft(64).subtract(BigInteger.ONE));
	}

	public static BigInteger[] fromUInt64(long[] values) {
		BigInteger[] tmp = new BigInteger[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Primitive.fromUInt64(values[i]);
		}
		return tmp;
	}
}
