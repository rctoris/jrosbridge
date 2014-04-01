package edu.wpi.rail.jrosbridge.messages.std;

import java.io.StringReader;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

/**
 * The std_msgs/UInt32MultiArray message. Please look at the MultiArrayLayout
 * message definition for documentation on all multiarrays.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class UInt32MultiArray extends Message {

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
	public static final java.lang.String TYPE = "std_msgs/UInt32MultiArray";

	private final MultiArrayLayout layout;
	private final int[] data;

	/**
	 * Create a new, empty UInt32MultiArray.
	 */
	public UInt32MultiArray() {
		this(new MultiArrayLayout(), new int[] {});
	}

	/**
	 * Create a new UInt32MultiArray with the given layout and data. The array
	 * of data will be copied into this object. All values will be treated as an
	 * unsigned representation.
	 * 
	 * @param layout
	 *            The specification of data layout.
	 * @param data
	 *            The array of data.
	 */
	public UInt32MultiArray(MultiArrayLayout layout, int[] data) {
		// build the JSON object
		super(Json
				.createObjectBuilder()
				.add(UInt32MultiArray.FIELD_LAYOUT, layout.toJsonObject())
				.add(UInt32MultiArray.FIELD_DATA,
						Json.createReader(
								new StringReader(Arrays.toString(Primitive
										.fromUInt32(data)))).readArray())
				.build(), UInt32MultiArray.TYPE);

		this.layout = layout;
		// copy the array
		this.data = new int[data.length];
		System.arraycopy(data, 0, this.data, 0, data.length);
	}

	/**
	 * Get the layout value of this UInt32MultiArray.
	 * 
	 * @return The layout value of this UInt32MultiArray.
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
	public int get(int index) {
		return this.data[index];
	}

	/**
	 * Get the data array. Note that this array should never be modified
	 * directly.
	 * 
	 * @return The data array.
	 */
	public int[] getData() {
		return this.data;
	}

	/**
	 * Create a clone of this UInt32MultiArray.
	 */
	@Override
	public UInt32MultiArray clone() {
		return new UInt32MultiArray(this.layout, this.data);
	}

	/**
	 * Create a new UInt32MultiArray based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A UInt32MultiArray message based on the given JSON string.
	 */
	public static UInt32MultiArray fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return UInt32MultiArray.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new UInt32MultiArray based on the given Message. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A UInt32MultiArray message based on the given Message.
	 */
	public static UInt32MultiArray fromMessage(Message m) {
		// get it from the JSON object
		return UInt32MultiArray.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new UInt32MultiArray based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A UInt32MultiArray message based on the given JSON object.
	 */
	public static UInt32MultiArray fromJsonObject(JsonObject jsonObject) {
		// check the layout
		MultiArrayLayout layout = jsonObject
				.containsKey(UInt32MultiArray.FIELD_LAYOUT) ? MultiArrayLayout
				.fromJsonObject(jsonObject
						.getJsonObject(UInt32MultiArray.FIELD_LAYOUT))
				: new MultiArrayLayout();

		// check the array
		int[] data = new int[] {};
		JsonArray jsonData = jsonObject
				.getJsonArray(UInt32MultiArray.FIELD_DATA);
		if (jsonData != null) {
			// convert each data
			data = new int[jsonData.size()];
			for (int i = 0; i < data.length; i++) {
				data[i] = Primitive.toUInt32(jsonData.getJsonNumber(i)
						.longValue());
			}
		}
		return new UInt32MultiArray(layout, data);
	}
}
