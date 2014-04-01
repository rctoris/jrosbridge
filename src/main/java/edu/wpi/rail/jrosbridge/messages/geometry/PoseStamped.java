package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;

/**
 * The geometry_msgs/PoseStamped message. A Pose with reference coordinate frame
 * and timestamp.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class PoseStamped extends Message {

	/**
	 * The name of the header field for the message.
	 */
	public static final String FIELD_HEADER = "header";

	/**
	 * The name of the pose field for the message.
	 */
	public static final String FIELD_POSE = "pose";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/PoseStamped";

	private final Header header;
	private final Pose pose;

	/**
	 * Create a new PoseStamped with all 0s.
	 */
	public PoseStamped() {
		this(new Header(), new Pose());
	}

	/**
	 * Create a new PoseStamped with the given values.
	 * 
	 * @param header
	 *            The header value of the pose.
	 * @param pose
	 *            The pose value of the pose.
	 */
	public PoseStamped(Header header, Pose pose) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(PoseStamped.FIELD_HEADER, header.toJsonObject())
				.add(PoseStamped.FIELD_POSE, pose.toJsonObject()).build(),
				PoseStamped.TYPE);
		this.header = header;
		this.pose = pose;
	}

	/**
	 * Get the header value of this pose.
	 * 
	 * @return The header value of this pose.
	 */
	public Header getHeader() {
		return this.header;
	}

	/**
	 * Get the pose value of this pose.
	 * 
	 * @return The pose value of this pose.
	 */
	public Pose getPose() {
		return this.pose;
	}

	/**
	 * Create a clone of this PoseStamped.
	 */
	@Override
	public PoseStamped clone() {
		return new PoseStamped(this.header, this.pose);
	}

	/**
	 * Create a new PoseStamped based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A PoseStamped message based on the given JSON string.
	 */
	public static PoseStamped fromJsonString(String jsonString) {
		// convert to a message
		return PoseStamped.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new PoseStamped based on the given Message. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A PoseStamped message based on the given Message.
	 */
	public static PoseStamped fromMessage(Message m) {
		// get it from the JSON object
		return PoseStamped.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new PoseStamped based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A PoseStamped message based on the given JSON object.
	 */
	public static PoseStamped fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Header header = jsonObject.containsKey(PoseStamped.FIELD_HEADER) ? Header
				.fromJsonObject(jsonObject
						.getJsonObject(PoseStamped.FIELD_HEADER))
				: new Header();
		Pose pose = jsonObject.containsKey(PoseStamped.FIELD_POSE) ? Pose
				.fromJsonObject(jsonObject
						.getJsonObject(PoseStamped.FIELD_POSE)) : new Pose();
		return new PoseStamped(header, pose);
	}
}
