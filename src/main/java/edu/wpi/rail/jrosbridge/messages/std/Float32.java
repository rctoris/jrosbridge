package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Float32 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Float32 extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Float32";

	private final float data;

	/**
	 * Create a new Float32 with a default of 0.
	 */
	public Float32() {
		this(0f);
	}

	/**
	 * Create a new Float32 with the given data value.
	 * 
	 * @param data
	 *            The data value of the float.
	 */
	public Float32(float data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Float32.FIELD_DATA, data).build(),
				Float32.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this float.
	 * 
	 * @return The data value of this float.
	 */
	public float getData() {
		return this.data;
	}

	/**
	 * Create a clone of this Float32.
	 */
	@Override
	public Float32 clone() {
		return new Float32(this.data);
	}

	/**
	 * Create a new Float32 based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Float32 message based on the given JSON string.
	 */
	public static Float32 fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return Float32.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Float32 based on the given Message. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Float32 message based on the given Message.
	 */
	public static Float32 fromMessage(Message m) {
		// get it from the JSON object
		return Float32.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Float32 based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Float32 message based on the given JSON object.
	 */
	public static Float32 fromJsonObject(JsonObject jsonObject) {
		// check the fields
		float data = jsonObject.containsKey(Float32.FIELD_DATA) ? (float) jsonObject
				.getJsonNumber(Float32.FIELD_DATA).doubleValue() : 0f;
		return new Float32(data);
	}
}
