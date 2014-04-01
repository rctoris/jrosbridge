package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Pose message. A representation of pose in free space,
 * composed of position and orientation.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Pose extends Message {

	/**
	 * The name of the position field for the message.
	 */
	public static final String FIELD_POSITION = "position";

	/**
	 * The name of the orientation field for the message.
	 */
	public static final String FIELD_ORIENTATION = "orientation";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/Pose";

	private final Point position;
	private final Quaternion orientation;

	/**
	 * Create a new Pose with all 0s.
	 */
	public Pose() {
		this(new Point(), new Quaternion());
	}

	/**
	 * Create a new Pose with the given position and orientation values.
	 * 
	 * @param position
	 *            The position value of the pose.
	 * @param orientation
	 *            The orientation value of the pose.
	 */
	public Pose(Point position, Quaternion orientation) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(Pose.FIELD_POSITION, position.toJsonObject())
				.add(Pose.FIELD_ORIENTATION, orientation.toJsonObject())
				.build(), Pose.TYPE);
		this.position = position;
		this.orientation = orientation;
	}

	/**
	 * Get the position value of this pose.
	 * 
	 * @return The position value of this pose.
	 */
	public Point getPosition() {
		return this.position;
	}

	/**
	 * Get the orientation value of this pose.
	 * 
	 * @return The orientation value of this pose.
	 */
	public Quaternion getOrientation() {
		return this.orientation;
	}

	/**
	 * Create a clone of this Pose.
	 */
	@Override
	public Pose clone() {
		return new Pose(this.position, this.orientation);
	}

	/**
	 * Create a new Pose based on the given JSON string. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Pose message based on the given JSON string.
	 */
	public static Pose fromJsonString(String jsonString) {
		// convert to a message
		return Pose.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Pose based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Pose message based on the given Message.
	 */
	public static Pose fromMessage(Message m) {
		// get it from the JSON object
		return Pose.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Pose based on the given JSON object. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Pose message based on the given JSON object.
	 */
	public static Pose fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Point position = jsonObject.containsKey(Pose.FIELD_POSITION) ? Point
				.fromJsonObject(jsonObject.getJsonObject(Pose.FIELD_POSITION))
				: new Point();
		Quaternion orientation = jsonObject.containsKey(Pose.FIELD_ORIENTATION) ? Quaternion
				.fromJsonObject(jsonObject
						.getJsonObject(Pose.FIELD_ORIENTATION))
				: new Quaternion();
		return new Pose(position, orientation);
	}
}
