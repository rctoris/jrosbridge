package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;

/**
 * The geometry_msgs/PoseWithCovarianceStamped message. This expresses an
 * estimated pose with a reference coordinate frame and timestamp.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class PoseWithCovarianceStamped extends Message {

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
	public static final String TYPE = "geometry_msgs/PoseWithCovarianceStamped";

	private final Header header;
	private final PoseWithCovariance pose;

	/**
	 * Create a new PoseWithCovarianceStamped with all 0s.
	 */
	public PoseWithCovarianceStamped() {
		this(new Header(), new PoseWithCovariance());
	}

	/**
	 * Create a new PoseWithCovarianceStamped with the given values.
	 * 
	 * @param header
	 *            The header value of the pose.
	 * @param pose
	 *            The pose value of the pose.
	 */
	public PoseWithCovarianceStamped(Header header, PoseWithCovariance pose) {
		// build the JSON object
		super(Json
				.createObjectBuilder()
				.add(PoseWithCovarianceStamped.FIELD_HEADER,
						header.toJsonObject())
				.add(PoseWithCovarianceStamped.FIELD_POSE, pose.toJsonObject())
				.build(), PoseWithCovarianceStamped.TYPE);
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
	public PoseWithCovariance getPose() {
		return this.pose;
	}

	/**
	 * Create a clone of this PoseWithCovarianceStamped.
	 */
	@Override
	public PoseWithCovarianceStamped clone() {
		return new PoseWithCovarianceStamped(this.header, this.pose);
	}

	/**
	 * Create a new PoseWithCovarianceStamped based on the given JSON string.
	 * Any missing values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A PoseWithCovarianceStamped message based on the given JSON
	 *         string.
	 */
	public static PoseWithCovarianceStamped fromJsonString(String jsonString) {
		// convert to a message
		return PoseWithCovarianceStamped.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new PoseWithCovarianceStamped based on the given Message. Any
	 * missing values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A PoseWithCovarianceStamped message based on the given Message.
	 */
	public static PoseWithCovarianceStamped fromMessage(Message m) {
		// get it from the JSON object
		return PoseWithCovarianceStamped.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new PoseWithCovarianceStamped based on the given JSON object.
	 * Any missing values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A PoseWithCovarianceStamped message based on the given JSON
	 *         object.
	 */
	public static PoseWithCovarianceStamped fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Header header = jsonObject
				.containsKey(PoseWithCovarianceStamped.FIELD_HEADER) ? Header
				.fromJsonObject(jsonObject
						.getJsonObject(PoseWithCovarianceStamped.FIELD_HEADER))
				: new Header();
		PoseWithCovariance pose = jsonObject
				.containsKey(PoseWithCovarianceStamped.FIELD_POSE) ? PoseWithCovariance
				.fromJsonObject(jsonObject
						.getJsonObject(PoseWithCovarianceStamped.FIELD_POSE))
				: new PoseWithCovariance();
		return new PoseWithCovarianceStamped(header, pose);
	}
}
