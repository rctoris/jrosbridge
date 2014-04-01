package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;

/**
 * The geometry_msgs/QuaternionStamped message. This represents a Quaternion
 * with reference coordinate frame and timestamp.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class QuaternionStamped extends Message {

	/**
	 * The name of the header field for the message.
	 */
	public static final String FIELD_HEADER = "header";

	/**
	 * The name of the quaternion field for the message.
	 */
	public static final String FIELD_QUATERNION = "quaternion";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/QuaternionStamped";

	private final Header header;
	private final Quaternion quaternion;

	/**
	 * Create a new QuaternionStamped with all 0s.
	 */
	public QuaternionStamped() {
		this(new Header(), new Quaternion());
	}

	/**
	 * Create a new QuaternionStamped with the given values.
	 * 
	 * @param header
	 *            The header value of the quaternion.
	 * @param quaternion
	 *            The quaternion value of the quaternion.
	 */
	public QuaternionStamped(Header header, Quaternion quaternion) {
		// build the JSON object
		super(Json
				.createObjectBuilder()
				.add(QuaternionStamped.FIELD_HEADER, header.toJsonObject())
				.add(QuaternionStamped.FIELD_QUATERNION,
						quaternion.toJsonObject()).build(),
				QuaternionStamped.TYPE);
		this.header = header;
		this.quaternion = quaternion;
	}

	/**
	 * Get the header value of this quaternion.
	 * 
	 * @return The header value of this quaternion.
	 */
	public Header getHeader() {
		return this.header;
	}

	/**
	 * Get the quaternion value of this quaternion.
	 * 
	 * @return The quaternion value of this quaternion.
	 */
	public Quaternion getQuaternion() {
		return this.quaternion;
	}

	/**
	 * Create a clone of this QuaternionStamped.
	 */
	@Override
	public QuaternionStamped clone() {
		return new QuaternionStamped(this.header, this.quaternion);
	}

	/**
	 * Create a new QuaternionStamped based on the given JSON string. Any
	 * missing values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A QuaternionStamped message based on the given JSON string.
	 */
	public static QuaternionStamped fromJsonString(String jsonString) {
		// convert to a message
		return QuaternionStamped.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new QuaternionStamped based on the given Message. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A QuaternionStamped message based on the given Message.
	 */
	public static QuaternionStamped fromMessage(Message m) {
		// get it from the JSON object
		return QuaternionStamped.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new QuaternionStamped based on the given JSON object. Any
	 * missing values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A QuaternionStamped message based on the given JSON object.
	 */
	public static QuaternionStamped fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Header header = jsonObject.containsKey(QuaternionStamped.FIELD_HEADER) ? Header
				.fromJsonObject(jsonObject
						.getJsonObject(QuaternionStamped.FIELD_HEADER))
				: new Header();
		Quaternion quaternion = jsonObject
				.containsKey(QuaternionStamped.FIELD_QUATERNION) ? Quaternion
				.fromJsonObject(jsonObject
						.getJsonObject(QuaternionStamped.FIELD_QUATERNION))
				: new Quaternion();
		return new QuaternionStamped(header, quaternion);
	}
}
