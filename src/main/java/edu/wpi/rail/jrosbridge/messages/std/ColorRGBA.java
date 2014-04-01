package edu.wpi.rail.jrosbridge.messages.std;

import java.awt.Color;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/ColorRGBA message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class ColorRGBA extends Message {

	/**
	 * The name of the r field for the message.
	 */
	public static final java.lang.String FIELD_R = "r";

	/**
	 * The name of the g field for the message.
	 */
	public static final java.lang.String FIELD_G = "g";

	/**
	 * The name of the b field for the message.
	 */
	public static final java.lang.String FIELD_B = "b";

	/**
	 * The name of the a field for the message.
	 */
	public static final java.lang.String FIELD_A = "a";

	/**
	 * The message type.
	 */
	public static final java.lang.String TYPE = "std_msgs/ColorRGBA";

	private final float r, g, b, a;

	/**
	 * Create a new ColorRGBA with all 0s for colors and an alpha value of 1.
	 */
	public ColorRGBA() {
		this(0f, 0f, 0f, 1f);
	}

	/**
	 * Create a new ColorRGBA with the given color values (alpha will be 1).
	 * 
	 * @param r
	 *            The r value of the color.
	 * @param g
	 *            The g value of the color.
	 * @param b
	 *            The b value of the color.
	 */
	public ColorRGBA(float r, float g, float b) {
		this(r, g, b, 1f);
	}

	/**
	 * Create a new ColorRGBA with the given color values.
	 * 
	 * @param r
	 *            The r value of the color.
	 * @param g
	 *            The g value of the color.
	 * @param b
	 *            The b value of the color.
	 * @param a
	 *            The a value of the color.
	 */
	public ColorRGBA(float r, float g, float b, float a) {
		// build the JSON object
		super(Json.createObjectBuilder().add(ColorRGBA.FIELD_R, r)
				.add(ColorRGBA.FIELD_G, g).add(ColorRGBA.FIELD_B, b)
				.add(ColorRGBA.FIELD_A, a).build(), ColorRGBA.TYPE);
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	/**
	 * Get the r value of this color.
	 * 
	 * @return The r value of this color.
	 */
	public float getR() {
		return this.r;
	}

	/**
	 * Get the g value of this color.
	 * 
	 * @return The g value of this color.
	 */
	public float getG() {
		return this.g;
	}

	/**
	 * Get the b value of this color.
	 * 
	 * @return The b value of this color.
	 */
	public float getB() {
		return this.b;
	}

	/**
	 * Get the a value of this color.
	 * 
	 * @return The a value of this color.
	 */
	public float getA() {
		return this.a;
	}

	/**
	 * Convert this color message to a new Java Color object.
	 * 
	 * @return A new Java color object based on this message.
	 */
	public Color toColor() {
		return new Color(this.r, this.g, this.b, this.a);
	}

	/**
	 * Create a clone of this ColorRGBA.
	 */
	@Override
	public ColorRGBA clone() {
		return new ColorRGBA(this.r, this.g, this.b, this.a);
	}

	/**
	 * Create a new ColorRGBA based on the color information in the Java Color
	 * object.
	 * 
	 * @param c
	 *            The Java Color object containing the color information.
	 * @return A new ColorRGBA message based on the given color information.
	 */
	public static ColorRGBA fromColor(Color c) {
		float r = (float) c.getRed() / 255.0f;
		float g = (float) c.getGreen() / 255.0f;
		float b = (float) c.getBlue() / 255.0f;
		float a = (float) c.getAlpha() / 255.0f;
		return new ColorRGBA(r, g, b, a);
	}

	/**
	 * Create a new ColorRGBA based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A ColorRGBA message based on the given JSON string.
	 */
	public static ColorRGBA fromJsonString(java.lang.String jsonString) {
		// convert to a message
		return ColorRGBA.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new ColorRGBA based on the given Message. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A ColorRGBA message based on the given Message.
	 */
	public static ColorRGBA fromMessage(Message m) {
		// get it from the JSON object
		return ColorRGBA.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new ColorRGBA based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A ColorRGBA message based on the given JSON object.
	 */
	public static ColorRGBA fromJsonObject(JsonObject jsonObject) {
		// check the fields
		float r = jsonObject.containsKey(ColorRGBA.FIELD_R) ? (float) jsonObject
				.getJsonNumber(ColorRGBA.FIELD_R).doubleValue() : 0f;
		float g = jsonObject.containsKey(ColorRGBA.FIELD_G) ? (float) jsonObject
				.getJsonNumber(ColorRGBA.FIELD_G).doubleValue() : 0f;
		float b = jsonObject.containsKey(ColorRGBA.FIELD_B) ? (float) jsonObject
				.getJsonNumber(ColorRGBA.FIELD_B).doubleValue() : 0f;
		float a = jsonObject.containsKey(ColorRGBA.FIELD_A) ? (float) jsonObject
				.getJsonNumber(ColorRGBA.FIELD_A).doubleValue() : 0f;

		return new ColorRGBA(r, g, b, a);
	}
}
