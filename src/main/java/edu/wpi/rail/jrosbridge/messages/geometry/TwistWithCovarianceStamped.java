package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;

/**
 * The geometry_msgs/TwistWithCovarianceStamped message. This represents an
 * estimated twist with reference coordinate frame and timestamp.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class TwistWithCovarianceStamped extends Message {

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
	public static final String TYPE = "geometry_msgs/TwistWithCovarianceStamped";

	private final Header header;
	private final TwistWithCovariance twist;

	/**
	 * Create a new TwistWithCovarianceStamped with all 0s.
	 */
	public TwistWithCovarianceStamped() {
		this(new Header(), new TwistWithCovariance());
	}

	/**
	 * Create a new TwistWithCovarianceStamped with the given values.
	 * 
	 * @param header
	 *            The header value of the twist.
	 * @param twist
	 *            The twist value of the twist.
	 */
	public TwistWithCovarianceStamped(Header header, TwistWithCovariance twist) {
		// build the JSON object
		super(Json
				.createObjectBuilder()
				.add(TwistWithCovarianceStamped.FIELD_HEADER,
						header.toJsonObject())
				.add(TwistWithCovarianceStamped.FIELD_TWIST,
						twist.toJsonObject()).build(),
				TwistWithCovarianceStamped.TYPE);
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
	public TwistWithCovariance getTwist() {
		return this.twist;
	}

	/**
	 * Create a clone of this TwistWithCovarianceStamped.
	 */
	@Override
	public TwistWithCovarianceStamped clone() {
		return new TwistWithCovarianceStamped(this.header, this.twist);
	}

	/**
	 * Create a new TwistWithCovarianceStamped based on the given JSON string.
	 * Any missing values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A TwistWithCovarianceStamped message based on the given JSON
	 *         string.
	 */
	public static TwistWithCovarianceStamped fromJsonString(String jsonString) {
		// convert to a message
		return TwistWithCovarianceStamped.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new TwistWithCovarianceStamped based on the given Message. Any
	 * missing values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A TwistWithCovarianceStamped message based on the given Message.
	 */
	public static TwistWithCovarianceStamped fromMessage(Message m) {
		// get it from the JSON object
		return TwistWithCovarianceStamped.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new TwistWithCovarianceStamped based on the given JSON object.
	 * Any missing values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A TwistWithCovarianceStamped message based on the given JSON
	 *         object.
	 */
	public static TwistWithCovarianceStamped fromJsonObject(
			JsonObject jsonObject) {
		// check the fields
		Header header = jsonObject
				.containsKey(TwistWithCovarianceStamped.FIELD_HEADER) ? Header
				.fromJsonObject(jsonObject
						.getJsonObject(TwistWithCovarianceStamped.FIELD_HEADER))
				: new Header();
		TwistWithCovariance twist = jsonObject
				.containsKey(TwistWithCovarianceStamped.FIELD_TWIST) ? TwistWithCovariance
				.fromJsonObject(jsonObject
						.getJsonObject(TwistWithCovarianceStamped.FIELD_TWIST))
				: new TwistWithCovariance();
		return new TwistWithCovarianceStamped(header, twist);
	}
}
