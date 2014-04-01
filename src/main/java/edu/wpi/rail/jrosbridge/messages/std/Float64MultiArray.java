package edu.wpi.rail.jrosbridge.messages.std;

import java.io.StringReader;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Float64MultiArray message. Please look at the MultiArrayLayout
 * message definition for documentation on all multiarrays.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Float64MultiArray extends Message {

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
	public static final java.lang.String TYPE = "std_msgs/Float64MultiArray";

	private final MultiArrayLayout layout;
	private final double[] data;

	/**
	 * Create a new, empty Float64MultiArray.
	 */
	public Float64MultiArray() {
		this(new MultiArrayLayout(), new double[] {});
	}

	/**
	 * Create a new Float64MultiArray with the given layout and data. The array
	 * of data will be copied into this object.
	 * 
	 * @param layout
	 *            The specification of data layout.
	 * @param data
	 *            The array of data.
	 */
	public Float64MultiArray(MultiArrayLayout layout, double[] data) {
		// build the JSON object
		super(Json
				.createObjectBuilder()
				.add(Float64MultiArray.FIELD_LAYOUT, layout.toJsonObject())
				.add(Float64MultiArray.FIELD_DATA,
						Json.createReader(
								new StringReader(Arrays.toString(data)))
								.readArray()).build(), Float64MultiArray.TYPE);
		this.layout = layout;
		// copy the array
		this.data = new double[data.length];
		System.arraycopy(data, 0, this.data, 0, data.length);
	}

	/**
	 * Get the layout value of this Float64MultiArray.
	 * 
	 * @return The layout value of this Float64MultiArray.
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
	public double get(int index) {
		return this.data[index];
	}

	/**
	 * Get the data array. Note that this array should never be modified
	 * directly.
	 * 
	 * @return The data array.
	 */
	public double[] getData() {
		return this.data;
	}

	/**
	 * Create a clone of this Float64MultiArray.
	 */
	@Override
	public Float64MultiArray clone() {
		return new Float64MultiArray(this.layout, this.data);
	}

	/**
	 * Create a new Float64MultiArray based on the given JSON string. Any
	 * missing values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Float64MultiArray message based on the given JSON string.
	 */
	public static Float64MultiArray fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return Float64MultiArray.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Float64MultiArray based on the given Message. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Float64MultiArray message based on the given Message.
	 */
	public static Float64MultiArray fromMessage(Message m) {
		// get it from the JSON object
		return Float64MultiArray.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Float64MultiArray based on the given JSON object. Any
	 * missing values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Float64MultiArray message based on the given JSON object.
	 */
	public static Float64MultiArray fromJsonObject(JsonObject jsonObject) {
		// check the layout
		MultiArrayLayout layout = jsonObject
				.containsKey(Float64MultiArray.FIELD_LAYOUT) ? MultiArrayLayout
				.fromJsonObject(jsonObject
						.getJsonObject(Float64MultiArray.FIELD_LAYOUT))
				: new MultiArrayLayout();

		// check the array
		double[] data = new double[] {};
		JsonArray jsonData = jsonObject
				.getJsonArray(Float64MultiArray.FIELD_DATA);
		if (jsonData != null) {
			// convert each data
			data = new double[jsonData.size()];
			for (int i = 0; i < data.length; i++) {
				data[i] = (double) jsonData.getJsonNumber(i).doubleValue();
			}
		}
		return new Float64MultiArray(layout, data);
	}
}
