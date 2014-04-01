package edu.wpi.rail.jrosbridge.messages.std;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/Float64 message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Float64 extends Message {

	/**
	 * The name of the data field for the message.
	 */
	public static final java.lang.String FIELD_DATA = "data";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/Float64";

	private final double data;

	/**
	 * Create a new Float64 with a default of 0.
	 */
	public Float64() {
		this(0);
	}

	/**
	 * Create a new Float64 with the given data value.
	 * 
	 * @param data
	 *            The data value of the double.
	 */
	public Float64(double data) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Float64.FIELD_DATA, data).build(),
				Float64.TYPE);
		this.data = data;
	}

	/**
	 * Get the data value of this double.
	 * 
	 * @return The data value of this double.
	 */
	public double getData() {
		return this.data;
	}

	/**
	 * Create a clone of this Float64.
	 */
	@Override
	public Float64 clone() {
		return new Float64(this.data);
	}

	/**
	 * Create a new Float64 based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Float64 message based on the given JSON string.
	 */
	public static Float64 fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return Float64.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Float64 based on the given Message. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Float64 message based on the given Message.
	 */
	public static Float64 fromMessage(Message m) {
		// get it from the JSON object
		return Float64.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Float64 based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Float64 message based on the given JSON object.
	 */
	public static Float64 fromJsonObject(JsonObject jsonObject) {
		// check the fields
		double data = jsonObject.containsKey(Float64.FIELD_DATA) ? jsonObject
				.getJsonNumber(Float64.FIELD_DATA).doubleValue() : 0;
		return new Float64(data);
	}
}
