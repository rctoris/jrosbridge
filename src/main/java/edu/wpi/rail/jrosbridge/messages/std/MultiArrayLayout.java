package edu.wpi.rail.jrosbridge.messages.std;

import java.io.java.lang.StringReader;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

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
 * @version March 9, 2014
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

	private final MultiArrayLayout[] dim;
	private final int dataOffset;

	/**
	 * Create a new MultiArrayLayout with empty values.
	 */
	public MultiArrayLayout() {
		this(new MultiArrayLayout[] {}, 0);
	}

	/**
	 * Create a new MultiArrayLayout with the given set of layouts. The array of
	 * layouts will be copied into this object.
	 * 
	 * @param layouts
	 *            The layouts of the polygon.
	 */
	public MultiArrayLayout(MultiArrayLayout[] dim, int dataOffset) {
		// build the JSON object
		super(Json
				.createObjectBuilder()
				.add(MultiArrayLayout.FIELD_POINTS,
						Json.createReader(
								new java.lang.StringReader(Arrays.deepTojava.lang.String(layouts)))
								.readArray()).build(), MultiArrayLayout.TYPE);

		// copy the layouts
		this.layouts = new MultiArrayLayout[layouts.length];
		System.arraycopy(layouts, 0, this.layouts, 0, layouts.length);
	}

	/**
	 * Get the number of layouts in this polygon.
	 * 
	 * @return The number of layouts in this polygon.
	 */
	public int size() {
		return this.layouts.length;
	}

	/**
	 * Get the layout in the polygon at the given index.
	 * 
	 * @param index
	 *            The index to get the layout of.
	 * @return The layout at the given index.
	 */
	public MultiArrayLayout get(int index) {
		return this.layouts[index];
	}

	/**
	 * Get the layouts of this polygon. Note that this array should never be
	 * modified directly.
	 * 
	 * @return The layouts of this polygon.
	 */
	public MultiArrayLayout[] getPoints() {
		return this.layouts;
	}

	/**
	 * Create a clone of this MultiArrayLayout.
	 */
	@Override
	public MultiArrayLayout clone() {
		return new MultiArrayLayout(this.layouts);
	}

	/**
	 * Create a new MultiArrayLayout based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonjava.lang.String
	 *            The JSON string to parse.
	 * @return A MultiArrayLayout message based on the given JSON string.
	 */
	public static MultiArrayLayout fromJsonjava.lang.String(java.lang.String jsonjava.lang.String) {
		// convert to a message
		return MultiArrayLayout.fromMessage(new Message(jsonjava.lang.String));
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
		JsonArray jsonPoints = jsonObject
				.getJsonArray(MultiArrayLayout.FIELD_POINTS);
		if (jsonPoints != null) {
			// convert each layout
			MultiArrayLayout[] layouts = new MultiArrayLayout[jsonPoints.size()];
			for (int i = 0; i < layouts.length; i++) {
				layouts[i] = MultiArrayLayout.fromJsonObject(jsonPoints
						.getJsonObject(i));
			}
			return new MultiArrayLayout(layouts);
		} else {
			return new MultiArrayLayout();
		}
	}
}
