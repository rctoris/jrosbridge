package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;

/**
 * The geometry_msgs/TransformStamped message. This expresses a transform from
 * coordinate frame header.frame_id to the coordinate frame child_frame_id
 * 
 * This message is mostly used by the tf package. See its documentation for more
 * information.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class TransformStamped extends Message {

	/**
	 * The name of the header field for the message.
	 */
	public static final String FIELD_HEADER = "header";

	/**
	 * The name of the child frame ID field for the message.
	 */
	public static final String FIELD_CHILD_FRAME_ID = "child_frame_id";

	/**
	 * The name of the transform field for the message.
	 */
	public static final String FIELD_TRANSFORM = "transform";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/TransformStamped";

	private final Header header;
	private final String childFrameID;
	private final Transform transform;

	/**
	 * Create a new TransformStamped with all 0s.
	 */
	public TransformStamped() {
		this(new Header(), "", new Transform());
	}

	/**
	 * Create a new TransformStamped with the given values.
	 * 
	 * @param header
	 *            The header value of the transform.
	 * @param childFrameID
	 *            The child frame ID value of the transform.
	 * @param transform
	 *            The transform value of the transform.
	 */
	public TransformStamped(Header header, String childFrameID,
			Transform transform) {
		// build the JSON object
		super(
				Json.createObjectBuilder()
						.add(TransformStamped.FIELD_HEADER,
								header.toJsonObject())
						.add(TransformStamped.FIELD_CHILD_FRAME_ID,
								childFrameID)
						.add(TransformStamped.FIELD_TRANSFORM,
								transform.toJsonObject()).build(),
				TransformStamped.TYPE);
		this.header = header;
		this.childFrameID = childFrameID;
		this.transform = transform;
	}

	/**
	 * Get the header value of this transform.
	 * 
	 * @return The header value of this transform.
	 */
	public Header getHeader() {
		return this.header;
	}

	/**
	 * Get the child frame ID value of this transform.
	 * 
	 * @return The child frame ID value of this transform.
	 */
	public String getChildFrameID() {
		return this.childFrameID;
	}

	/**
	 * Get the transform value of this transform.
	 * 
	 * @return The transform value of this transform.
	 */
	public Transform getTransform() {
		return this.transform;
	}

	/**
	 * Create a clone of this TransformStamped.
	 */
	@Override
	public TransformStamped clone() {
		return new TransformStamped(this.header, this.childFrameID,
				this.transform);
	}

	/**
	 * Create a new TransformStamped based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A TransformStamped message based on the given JSON string.
	 */
	public static TransformStamped fromJsonString(String jsonString) {
		// convert to a message
		return TransformStamped.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new TransformStamped based on the given Message. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A TransformStamped message based on the given Message.
	 */
	public static TransformStamped fromMessage(Message m) {
		// get it from the JSON object
		return TransformStamped.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new TransformStamped based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A TransformStamped message based on the given JSON object.
	 */
	public static TransformStamped fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Header header = jsonObject.containsKey(TransformStamped.FIELD_HEADER) ? Header
				.fromJsonObject(jsonObject
						.getJsonObject(TransformStamped.FIELD_HEADER))
				: new Header();
		String childFrameID = jsonObject
				.containsKey(TransformStamped.FIELD_CHILD_FRAME_ID) ? jsonObject
				.getString(TransformStamped.FIELD_CHILD_FRAME_ID) : "";
		Transform transform = jsonObject
				.containsKey(TransformStamped.FIELD_TRANSFORM) ? Transform
				.fromJsonObject(jsonObject
						.getJsonObject(TransformStamped.FIELD_TRANSFORM))
				: new Transform();
		return new TransformStamped(header, childFrameID, transform);
	}
}
