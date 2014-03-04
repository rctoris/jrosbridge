package edu.wpi.rail.jrosbridge.messages.std;

import java.awt.Color;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The std_msgs/ColorRGBA message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 4, 2014
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

	private float r, g, b, a;

	/**
	 * Create a new ColorRGBA with all 0s for colors and an alpha value of 1.
	 */
	public ColorRGBA() {
		this(0f, 0f, 0f, 1f);
	}

	/**
	 * Create a new ColorRGBA with the given red value (green and blue values
	 * will be 0 and alpha will be 1).
	 * 
	 * @param r
	 *            The r value of the color.
	 */
	public ColorRGBA(float r) {
		this(r, 0f, 0f, 1f);
	}

	/**
	 * Create a new ColorRGBA with the given red and green values (the blue
	 * value will be set to 0 alpha will be 1).
	 * 
	 * @param r
	 *            The r value of the color.
	 * @param g
	 *            The g value of the color.
	 */
	public ColorRGBA(float r, float g) {
		this(r, g, 0f, 1f);
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
	 * Create a deep clone of this ColorRGBA.
	 */
	@Override
	public ColorRGBA clone() {
		return new ColorRGBA(this.r, this.g, this.b, this.a);
	}
}
