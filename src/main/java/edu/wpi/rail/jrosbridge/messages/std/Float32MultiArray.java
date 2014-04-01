package edu.wpi.rail.jrosbridge.messages.std;

import java.io.StringReader;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Float32MultiArray message. Please look at the MultiArrayLayout
 * message definition for documentation on all multiarrays.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Float32MultiArray extends Message {

	/**
	 * The name of the layout field for the message.
	 */
	public static final java.lang.String FIELD_LAYOUT = "layout";

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Float32MultiArray";

	private final MultiArrayLayout layout;
	private final float[] data;

	/**
	 * Create a new, empty Float32MultiArray.
	 */
	public Float32MultiArray() {
		this(new MultiArrayLayout(), new float[] {});
	}

	/**
	 * Create a new Float32MultiArray with the given layout and data. The array
	 * of data will be copied into this object.
	 * 
	 * @param layout
	 *            The specification of data layout.
	 * @param data
	 *            The array of data.
	 */
	public Float32MultiArray(MultiArrayLayout layout, float[] data) {
		// build the JSON object
		super(Json
				.createObjectBuilder()
				.add(Float32MultiArray.FIELD_LAYOUT, layout.toJsonObject())
				.add(Float32MultiArray.FIELD_DATA,
						Json.createReader(
								new StringReader(Arrays.toString(data)))
								.readArray()).build(), Float32MultiArray.TYPE);
		this.layout = layout;
		// copy the array
		this.data = new float[data.length];
		System.arraycopy(data, 0, this.data, 0, data.length);
	}

	/**
	 * Get the layout value of this Float32MultiArray.
	 * 
	 * @return The layout value of this Float32MultiArray.
	 */
	public MultiArrayLayout getLayout() {
		return this.layout;
	}

	/**
	 * Get the size of the array.
	 * 
	 * @return The size of the array.
	 */
	public int size() {
		return this.data.length;
	}

	/**
	 * Get the data value at the given index.
	 * 
	 * @param index
	 *            The index to get the data value of.
	 * @return The data value at the given index.
	 */
	public float get(int index) {
		return this.data[index];
	}

	/**
	 * Get the data array. Note that this array should never be modified
	 * directly.
	 * 
	 * @return The data array.
	 */
	public float[] getData() {
		return this.data;
	}

	/**
	 * Create a clone of this Float32MultiArray.
	 */
	@Override
	public Float32MultiArray clone() {
		return new Float32MultiArray(this.layout, this.data);
	}

	/**
	 * Create a new Float32MultiArray based on the given JSON string. Any
	 * missing values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Float32MultiArray message based on the given JSON string.
	 */
	public static Float32MultiArray fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return Float32MultiArray.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Float32MultiArray based on the given Message. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Float32MultiArray message based on the given Message.
	 */
	public static Float32MultiArray fromMessage(Message m) {
		// get it from the JSON object
		return Float32MultiArray.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Float32MultiArray based on the given JSON object. Any
	 * missing values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Float32MultiArray message based on the given JSON object.
	 */
	public static Float32MultiArray fromJsonObject(JsonObject jsonObject) {
		// check the layout
		MultiArrayLayout layout = jsonObject
				.containsKey(Float32MultiArray.FIELD_LAYOUT) ? MultiArrayLayout
				.fromJsonObject(jsonObject
						.getJsonObject(Float32MultiArray.FIELD_LAYOUT))
				: new MultiArrayLayout();

		// check the array
		float[] data = new float[] {};
		JsonArray jsonData = jsonObject
				.getJsonArray(Float32MultiArray.FIELD_DATA);
		if (jsonData != null) {
			// convert each data
			data = new float[jsonData.size()];
			for (int i = 0; i < data.length; i++) {
				data[i] = (float) jsonData.getJsonNumber(i).doubleValue();
			}
		}
		return new Float32MultiArray(layout, data);
	}
}
