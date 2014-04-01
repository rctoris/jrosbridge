package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;

/**
 * The geometry_msgs/TwistStamped message. A twist with reference coordinate
 * frame and timestamp.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class TwistStamped extends Message {

	/**
	 * The name of the header field for the message.
	 */
	public static final String FIELD_HEADER = "header";

	/**
	 * The name of the twist field for the message.
	 */
	public static final String FIELD_TWIST = "twist";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/TwistStamped";

	private final Header header;
	private final Twist twist;

	/**
	 * Create a new TwistStamped with all 0s.
	 */
	public TwistStamped() {
		this(new Header(), new Twist());
	}

	/**
	 * Create a new TwistStamped with the given values.
	 * 
	 * @param header
	 *            The header value of the twist.
	 * @param twist
	 *            The twist value of the twist.
	 */
	public TwistStamped(Header header, Twist twist) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(TwistStamped.FIELD_HEADER, header.toJsonObject())
				.add(TwistStamped.FIELD_TWIST, twist.toJsonObject()).build(),
				TwistStamped.TYPE);
		this.header = header;
		this.twist = twist;
	}

	/**
	 * Get the header value of this twist.
	 * 
	 * @return The header value of this twist.
	 */
	public Header getHeader() {
		return this.header;
	}

	/**
	 * Get the twist value of this twist.
	 * 
	 * @return The twist value of this twist.
	 */
	public Twist getTwist() {
		return this.twist;
	}

	/**
	 * Create a clone of this TwistStamped.
	 */
	@Override
	public TwistStamped clone() {
		return new TwistStamped(this.header, this.twist);
	}

	/**
	 * Create a new TwistStamped based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A TwistStamped message based on the given JSON string.
	 */
	public static TwistStamped fromJsonString(String jsonString) {
		// convert to a message
		return TwistStamped.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new TwistStamped based on the given Message. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A TwistStamped message based on the given Message.
	 */
	public static TwistStamped fromMessage(Message m) {
		// get it from the JSON object
		return TwistStamped.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new TwistStamped based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A TwistStamped message based on the given JSON object.
	 */
	public static TwistStamped fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Header header = jsonObject.containsKey(TwistStamped.FIELD_HEADER) ? Header
				.fromJsonObject(jsonObject
						.getJsonObject(TwistStamped.FIELD_HEADER))
				: new Header();
		Twist twist = jsonObject.containsKey(TwistStamped.FIELD_TWIST) ? Twist
				.fromJsonObject(jsonObject
						.getJsonObject(TwistStamped.FIELD_TWIST)) : new Twist();
		return new TwistStamped(header, twist);
	}
}
