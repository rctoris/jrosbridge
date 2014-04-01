package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

/**
 * The std_msgs/MultiArrayDimension message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class MultiArrayDimension extends Message {

	/**
	 * The name of the label field for the message.
	 */
	public static final java.lang.String FIELD_LABEL = "label";

	/**
	 * The name of the size field for the message.
	 */
	public static final java.lang.String FIELD_SIZE = "size";

	/**
	 * The name of the stride field for the message.
	 */
	public static final java.lang.String FIELD_STRIDE = "stride";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/MultiArrayDimension";

	private final java.lang.String label;
	private final int size, stride;

	/**
	 * Create a new MultiArrayDimension with all empty values.
	 */
	public MultiArrayDimension() {
		this("", 0, 0);
	}

	/**
	 * Create a new MultiArrayDimension with the given values.
	 * 
	 * @param label
	 *            The label of given dimension.
	 * @param size
	 *            The size of given dimension (in type units) treated as an
	 *            unsigned 32-bit integer.
	 * @param stride
	 *            The stride of given dimension treated as an unsigned 32-bit
	 *            integer.
	 */
	public MultiArrayDimension(java.lang.String label, int size, int stride) {
		// build the JSON object
		super(
				Json.createObjectBuilder()
						.add(MultiArrayDimension.FIELD_LABEL, label)
						.add(MultiArrayDimension.FIELD_SIZE,
								Primitive.fromUInt32(size))
						.add(MultiArrayDimension.FIELD_STRIDE,
								Primitive.fromUInt32(stride)).build(),
				MultiArrayDimension.TYPE);
		this.label = label;
		this.size = size;
		this.stride = stride;
	}

	/**
	 * Get the label value of this message.
	 * 
	 * @return The label value of this message.
	 */
	public java.lang.String getLabel() {
		return this.label;
	}

	/**
	 * Get the size value of this message which should be treated as an unsigned
	 * 32-bit integer.
	 * 
	 * @return The size value of this message.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Get the stride value of this message which should be treated as an
	 * unsigned 32-bit integer.
	 * 
	 * @return The stride value of this message.
	 */
	public int getStride() {
		return this.stride;
	}

	/**
	 * Create a clone of this MultiArrayDimension.
	 */
	@Override
	public MultiArrayDimension clone() {
		return new MultiArrayDimension(this.label, this.size, this.stride);
	}

	/**
	 * Create a new MultiArrayDimension based on the given JSON string. Any
	 * missing values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A MultiArrayDimension message based on the given JSON string.
	 */
	public static MultiArrayDimension fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return MultiArrayDimension.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new MultiArrayDimension based on the given Message. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A MultiArrayDimension message based on the given Message.
	 */
	public static MultiArrayDimension fromMessage(Message m) {
		// get it from the JSON object
		return MultiArrayDimension.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new MultiArrayDimension based on the given JSON object. Any
	 * missing values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A MultiArrayDimension message based on the given JSON object.
	 */
	public static MultiArrayDimension fromJsonObject(JsonObject jsonObject) {
		// check the fields
		java.lang.String label = jsonObject
				.containsKey(MultiArrayDimension.FIELD_LABEL) ? jsonObject
				.getString(MultiArrayDimension.FIELD_LABEL) : "";
		long size64 = jsonObject.containsKey(MultiArrayDimension.FIELD_SIZE) ? jsonObject
				.getJsonNumber(MultiArrayDimension.FIELD_SIZE).longValue() : 0L;
		long stride64 = jsonObject
				.containsKey(MultiArrayDimension.FIELD_STRIDE) ? jsonObject
				.getJsonNumber(MultiArrayDimension.FIELD_STRIDE).longValue()
				: 0L;

		// convert to a 32-bit number
		int size32 = Primitive.toUInt32(size64);
		int stride32 = Primitive.toUInt32(stride64);
		return new MultiArrayDimension(label, size32, stride32);
	}
}
