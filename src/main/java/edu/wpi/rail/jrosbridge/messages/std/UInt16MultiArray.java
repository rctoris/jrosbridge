package edu.wpi.rail.jrosbridge.messages.std;

import java.io.StringReader;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

/**
 * The std_msgs/UInt16MultiArray message. Please look at the MultiArrayLayout
 * message definition for documentation on all multiarrays.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class UInt16MultiArray extends Message {

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
	public static final java.lang.String TYPE = "std_msgs/UInt16MultiArray";

	private final MultiArrayLayout layout;
	private final short[] data;

	/**
	 * Create a new, empty UInt16MultiArray.
	 */
	public UInt16MultiArray() {
		this(new MultiArrayLayout(), new short[] {});
	}

	/**
	 * Create a new UInt16MultiArray with the given layout and data. The array
	 * of data will be copied into this object. All values will be treated as an
	 * unsigned representation.
	 * 
	 * @param layout
	 *            The specification of data layout.
	 * @param data
	 *            The array of data.
	 */
	public UInt16MultiArray(MultiArrayLayout layout, short[] data) {
		// build the JSON object
		super(Json
				.createObjectBuilder()
				.add(UInt16MultiArray.FIELD_LAYOUT, layout.toJsonObject())
				.add(UInt16MultiArray.FIELD_DATA,
						Json.createReader(
								new StringReader(Arrays.toString(Primitive
										.fromUInt16(data)))).readArray())
				.build(), UInt16MultiArray.TYPE);

		this.layout = layout;
		// copy the array
		this.data = new short[data.length];
		System.arraycopy(data, 0, this.data, 0, data.length);
	}

	/**
	 * Get the layout value of this UInt16MultiArray.
	 * 
	 * @return The layout value of this UInt16MultiArray.
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
	public short get(int index) {
		return this.data[index];
	}

	/**
	 * Get the data array. Note that this array should never be modified
	 * directly.
	 * 
	 * @return The data array.
	 */
	public short[] getData() {
		return this.data;
	}

	/**
	 * Create a clone of this UInt16MultiArray.
	 */
	@Override
	public UInt16MultiArray clone() {
		return new UInt16MultiArray(this.layout, this.data);
	}

	/**
	 * Create a new UInt16MultiArray based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A UInt16MultiArray message based on the given JSON string.
	 */
	public static UInt16MultiArray fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return UInt16MultiArray.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new UInt16MultiArray based on the given Message. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A UInt16MultiArray message based on the given Message.
	 */
	public static UInt16MultiArray fromMessage(Message m) {
		// get it from the JSON object
		return UInt16MultiArray.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new UInt16MultiArray based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A UInt16MultiArray message based on the given JSON object.
	 */
	public static UInt16MultiArray fromJsonObject(JsonObject jsonObject) {
		// check the layout
		MultiArrayLayout layout = jsonObject
				.containsKey(UInt16MultiArray.FIELD_LAYOUT) ? MultiArrayLayout
				.fromJsonObject(jsonObject
						.getJsonObject(UInt16MultiArray.FIELD_LAYOUT))
				: new MultiArrayLayout();

		// check the array
		short[] data = new short[] {};
		JsonArray jsonData = jsonObject
				.getJsonArray(UInt16MultiArray.FIELD_DATA);
		if (jsonData != null) {
			// convert each data
			data = new short[jsonData.size()];
			for (int i = 0; i < data.length; i++) {
				data[i] = Primitive.toUInt16(jsonData.getInt(i));
			}
		}
		return new UInt16MultiArray(layout, data);
	}
}
