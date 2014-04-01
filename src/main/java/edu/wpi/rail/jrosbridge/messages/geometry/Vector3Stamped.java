package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;

/**
 * The geometry_msgs/Vector3Stamped message. This represents a Vector3 with
 * reference coordinate frame and timestamp.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Vector3Stamped extends Message {

	/**
	 * The name of the header field for the message.
	 */
	public static final String FIELD_HEADER = "header";

	/**
	 * The name of the vector field for the message.
	 */
	public static final String FIELD_VECTOR = "vector";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/Vector3Stamped";

	private final Header header;
	private final Vector3 vector;

	/**
	 * Create a new Vector3Stamped with all 0s.
	 */
	public Vector3Stamped() {
		this(new Header(), new Vector3());
	}

	/**
	 * Create a new Vector3Stamped with the given values.
	 * 
	 * @param header
	 *            The header value of the vector.
	 * @param vector
	 *            The vector value of the vector.
	 */
	public Vector3Stamped(Header header, Vector3 vector) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(Vector3Stamped.FIELD_HEADER, header.toJsonObject())
				.add(Vector3Stamped.FIELD_VECTOR, vector.toJsonObject())
				.build(), Vector3Stamped.TYPE);
		this.header = header;
		this.vector = vector;
	}

	/**
	 * Get the header value of this vector.
	 * 
	 * @return The header value of this vector.
	 */
	public Header getHeader() {
		return this.header;
	}

	/**
	 * Get the vector value of this vector.
	 * 
	 * @return The vector value of this vector.
	 */
	public Vector3 getVector3() {
		return this.vector;
	}

	/**
	 * Create a clone of this Vector3Stamped.
	 */
	@Override
	public Vector3Stamped clone() {
		return new Vector3Stamped(this.header, this.vector);
	}

	/**
	 * Create a new Vector3Stamped based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Vector3Stamped message based on the given JSON string.
	 */
	public static Vector3Stamped fromJsonString(String jsonString) {
		// convert to a message
		return Vector3Stamped.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Vector3Stamped based on the given Message. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Vector3Stamped message based on the given Message.
	 */
	public static Vector3Stamped fromMessage(Message m) {
		// get it from the JSON object
		return Vector3Stamped.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Vector3Stamped based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Vector3Stamped message based on the given JSON object.
	 */
	public static Vector3Stamped fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Header header = jsonObject.containsKey(Vector3Stamped.FIELD_HEADER) ? Header
				.fromJsonObject(jsonObject
						.getJsonObject(Vector3Stamped.FIELD_HEADER))
				: new Header();
		Vector3 vector = jsonObject.containsKey(Vector3Stamped.FIELD_VECTOR) ? Vector3
				.fromJsonObject(jsonObject
						.getJsonObject(Vector3Stamped.FIELD_VECTOR))
				: new Vector3();
		return new Vector3Stamped(header, vector);
	}
}
