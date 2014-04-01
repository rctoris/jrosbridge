package edu.wpi.rail.jrosbridge.messages.std;

import java.io.StringReader;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

/**
 * The std_msgs/MultiArrayLayout message. The multiarray declares a generic
 * multi-dimensional array of a particular data type. Dimensions are ordered
 * from outer most to inner most.
 * 
 * Accessors should ALWAYS be written in terms of dimension stride and specified
 * outer-most dimension first.
 * 
 * multiarray(i,j,k) = data[data_offset + dim_stride[1]*i + dim_stride[2]*j + k]
 * 
 * A standard, 3-channel 640x480 image with interleaved color channels would be
 * specified as:
 * 
 * dim[0].label = "height"
 * 
 * dim[0].size = 480
 * 
 * dim[0].stride = 3*640*480 = 921600 (note dim[0] stride is just size of image)
 * 
 * dim[1].label = "width"
 * 
 * dim[1].size = 640
 * 
 * dim[1].stride = 3*640 = 1920
 * 
 * dim[2].label = "channel"
 * 
 * dim[2].size = 3
 * 
 * dim[2].stride = 3
 * 
 * multiarray(i,j,k) refers to the ith row, jth column, and kth channel.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class MultiArrayLayout extends Message {

	/**
	 * The name of the dimensions field for the message.
	 */
	public static final java.lang.String FIELD_DIM = "dim";

	/**
	 * The name of the data offset field for the message.
	 */
	public static final java.lang.String FIELD_DATA_OFFSET = "data_offset";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/MultiArrayLayout";

	private final MultiArrayDimension[] dim;
	private final int dataOffset;

	/**
	 * Create a new MultiArrayLayout with empty values.
	 */
	public MultiArrayLayout() {
		this(new MultiArrayDimension[] {}, 0);
	}

	/**
	 * Create a new MultiArrayLayout with the given set of layouts. The array of
	 * layouts will be copied into this object. The data offset is treated as a
	 * 32-bit unsigned integer. The array of properties will be copied into this
	 * object.
	 * 
	 * @param dim
	 *            The array of dimension properties.
	 * @param dataOffset
	 *            The padding bytes at front of the data.
	 */
	public MultiArrayLayout(MultiArrayDimension[] dim, int dataOffset) {
		// build the JSON object
		super(Json
				.createObjectBuilder()
				.add(MultiArrayLayout.FIELD_DIM,
						Json.createReader(
								new StringReader(Arrays.deepToString(dim)))
								.readArray())
				.add(MultiArrayLayout.FIELD_DATA_OFFSET,
						Primitive.fromUInt32(dataOffset)).build(),
				MultiArrayLayout.TYPE);

		// copy the array
		this.dim = new MultiArrayDimension[dim.length];
		System.arraycopy(dim, 0, this.dim, 0, dim.length);
		this.dataOffset = dataOffset;
	}

	/**
	 * Get the number of dimension properties in this array layout.
	 * 
	 * @return The number of dimension properties in this array layout.
	 */
	public int size() {
		return this.dim.length;
	}

	/**
	 * Get the dimension at the given index.
	 * 
	 * @param index
	 *            The index to get the dimension property of.
	 * @return The dimension property at the given index.
	 */
	public MultiArrayDimension get(int index) {
		return this.dim[index];
	}

	/**
	 * Get the dimension properties. Note that this array should never be
	 * modified directly.
	 * 
	 * @return The layouts of this array layout.
	 */
	public MultiArrayDimension[] getDim() {
		return this.dim;
	}

	/**
	 * Get the data offset value.
	 * 
	 * @return The data offset value.
	 */
	public int getDataOffset() {
		return this.dataOffset;
	}

	/**
	 * Create a clone of this MultiArrayLayout.
	 */
	@Override
	public MultiArrayLayout clone() {
		return new MultiArrayLayout(this.dim, this.dataOffset);
	}

	/**
	 * Create a new MultiArrayLayout based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A MultiArrayLayout message based on the given JSON string.
	 */
	public static MultiArrayLayout fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return MultiArrayLayout.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new MultiArrayLayout based on the given Message. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A MultiArrayLayout message based on the given Message.
	 */
	public static MultiArrayLayout fromMessage(Message m) {
		// get it from the JSON object
		return MultiArrayLayout.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new MultiArrayLayout based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A MultiArrayLayout message based on the given JSON object.
	 */
	public static MultiArrayLayout fromJsonObject(JsonObject jsonObject) {
		// check the array
		MultiArrayDimension[] dim = new MultiArrayDimension[] {};
		JsonArray jsonDim = jsonObject.getJsonArray(MultiArrayLayout.FIELD_DIM);
		if (jsonDim != null) {
			// convert each property
			dim = new MultiArrayDimension[jsonDim.size()];
			for (int i = 0; i < dim.length; i++) {
				dim[i] = MultiArrayDimension.fromJsonObject(jsonDim
						.getJsonObject(i));
			}
		}

		// check the offset
		int dataOffset = jsonObject
				.containsKey(MultiArrayLayout.FIELD_DATA_OFFSET) ? Primitive
				.toUInt32(jsonObject.getJsonNumber(
						MultiArrayLayout.FIELD_DATA_OFFSET).longValue()) : 0;

		return new MultiArrayLayout(dim, dataOffset);
	}
}
