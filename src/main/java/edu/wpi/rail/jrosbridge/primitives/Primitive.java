package edu.wpi.rail.jrosbridge.primitives;

import java.io.StringReader;
import java.math.BigInteger;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.JsonWrapper;

/**
 * Primitive objects are used as a wrapper for non-native ROS primitives. These
 * primitives act as wrappers around JSON objects.
 * 
 * This class also contains static functions for dealing with ROS' unsigned
 * numbers.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version April 1, 2014
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

	/**
	 * Convert the given value to an unsigned 8-bit unsigned integer. This
	 * ignores the high 8-bits of the short.
	 * 
	 * @param value
	 *            The value to convert.
	 * @return The value encoded as an 8-bit unsigned integer.
	 */
	public static byte toUInt8(short value) {
		// zero out the high 8-bits
		short tmp = (short) ((value >> 8) << 8);
		return (byte) (value - tmp);
	}

	/**
	 * Convert the given values to unsigned 8-bit unsigned integers. This
	 * ignores the high 8-bits of the input.
	 * 
	 * @param values
	 *            The values to convert.
	 * @return The values encoded as an 8-bit unsigned integer.
	 */
	public static byte[] toUInt8(short[] values) {
		byte[] tmp = new byte[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Primitive.toUInt8(values[i]);
		}
		return tmp;
	}

	/**
	 * Convert the given value in unsigned 8-bit representation into its actual
	 * value. That is, all return values of this function will be positive.
	 * 
	 * @param value
	 *            The unsigned 8-bit value to convert.
	 * @return The value of the given 8-bit unsigned value.
	 */
	public static short fromUInt8(byte value) {
		return (short) (value & (short) 0xFF);
	}

	/**
	 * Convert the given values in unsigned 8-bit representation into their
	 * actual values. That is, all return values of this function will be
	 * positive.
	 * 
	 * @param values
	 *            The unsigned 8-bit values to convert.
	 * @return The values of the given 8-bit unsigned values.
	 */
	public static short[] fromUInt8(byte[] values) {
		short[] tmp = new short[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Primitive.fromUInt8(values[i]);
		}
		return tmp;
	}

	/**
	 * Convert the given value to an unsigned 16-bit unsigned integer. This
	 * ignores the high 16-bits of the input.
	 * 
	 * @param value
	 *            The value to convert.
	 * @return The value encoded as an 16-bit unsigned integer.
	 */
	public static short toUInt16(int value) {
		// zero out the high 16-bits
		int tmp = (int) ((value >> 16) << 16);
		return (short) (value - tmp);
	}

	/**
	 * Convert the given values to unsigned 16-bit unsigned integers. This
	 * ignores the high 16-bits of the input.
	 * 
	 * @param values
	 *            The values to convert.
	 * @return The values encoded as an 16-bit unsigned integer.
	 */
	public static short[] toUInt16(int[] values) {
		short[] tmp = new short[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Primitive.toUInt16(values[i]);
		}
		return tmp;
	}

	/**
	 * Convert the given value in unsigned 16-bit representation into its actual
	 * value. That is, all return values of this function will be positive.
	 * 
	 * @param value
	 *            The unsigned 16-bit value to convert.
	 * @return The value of the given 16-bit unsigned value.
	 */
	public static int fromUInt16(short value) {
		return (int) (value & 0xFFFF);
	}

	/**
	 * Convert the given values in unsigned 16-bit representation into their
	 * actual values. That is, all return values of this function will be
	 * positive.
	 * 
	 * @param values
	 *            The unsigned 16-bit values to convert.
	 * @return The values of the given 16-bit unsigned values.
	 */
	public static int[] fromUInt16(short[] values) {
		int[] tmp = new int[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Primitive.fromUInt16(values[i]);
		}
		return tmp;
	}

	/**
	 * Convert the given value to an unsigned 32-bit unsigned integer. This
	 * ignores the high 32-bits of the input.
	 * 
	 * @param value
	 *            The value to convert.
	 * @return The value encoded as an 32-bit unsigned integer.
	 */
	public static int toUInt32(long value) {
		// zero out the high 32-bits
		long tmp = (long) ((value >> 32) << 32);
		return (int) (value - tmp);
	}

	/**
	 * Convert the given values to unsigned 32-bit unsigned integers. This
	 * ignores the high 32-bits of the input.
	 * 
	 * @param values
	 *            The values to convert.
	 * @return The values encoded as an 32-bit unsigned integer.
	 */
	public static int[] toUInt32(long[] values) {
		int[] tmp = new int[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Primitive.toUInt32(values[i]);
		}
		return tmp;
	}

	/**
	 * Convert the given value in unsigned 32-bit representation into its actual
	 * value. That is, all return values of this function will be positive.
	 * 
	 * @param value
	 *            The unsigned 32-bit value to convert.
	 * @return The value of the given 32-bit unsigned value.
	 */
	public static long fromUInt32(int value) {
		return (long) (value & 0xFFFFFFFFL);
	}

	/**
	 * Convert the given values in unsigned 32-bit representation into their
	 * actual values. That is, all return values of this function will be
	 * positive.
	 * 
	 * @param values
	 *            The unsigned 32-bit values to convert.
	 * @return The values of the given 64-bit unsigned values.
	 */
	public static long[] fromUInt32(int[] values) {
		long[] tmp = new long[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Primitive.fromUInt32(values[i]);
		}
		return tmp;
	}

	/**
	 * Convert the given value to an unsigned 64-bit unsigned integer. This
	 * ignores the high 64-bits of the input.
	 * 
	 * @param value
	 *            The value to convert.
	 * @return The value encoded as an 64-bit unsigned integer.
	 */
	public static long toUInt64(BigInteger value) {
		return value.longValue();
	}

	/**
	 * Convert the given values to unsigned 64-bit unsigned integers. This
	 * ignores the high 64-bits of the input.
	 * 
	 * @param values
	 *            The values to convert.
	 * @return The values encoded as an 64-bit unsigned integer.
	 */
	public static long[] toUInt64(BigInteger[] values) {
		long[] tmp = new long[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Primitive.toUInt64(values[i]);
		}
		return tmp;
	}

	/**
	 * Convert the given value in unsigned 64-bit representation into its actual
	 * value. That is, all return values of this function will be positive.
	 * 
	 * @param value
	 *            The unsigned 64-bit value to convert.
	 * @return The value of the given 64-bit unsigned value.
	 */
	public static BigInteger fromUInt64(long value) {
		return BigInteger.valueOf(value).and(
				BigInteger.ONE.shiftLeft(64).subtract(BigInteger.ONE));
	}

	/**
	 * Convert the given values in unsigned 64-bit representation into their
	 * actual values. That is, all return values of this function will be
	 * positive.
	 * 
	 * @param values
	 *            The unsigned 64-bit values to convert.
	 * @return The values of the given 64-bit unsigned values.
	 */
	public static BigInteger[] fromUInt64(long[] values) {
		BigInteger[] tmp = new BigInteger[values.length];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Primitive.fromUInt64(values[i]);
		}
		return tmp;
	}
}
