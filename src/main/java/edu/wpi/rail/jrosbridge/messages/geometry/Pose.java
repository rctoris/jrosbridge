package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Pose message. A representation of pose in free space,
 * composed of position and orientation.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 25, 2014
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

	private Point position;
	private Quaternion orientation;

	/**
	 * Create a new Pose with all 0s.
	 */
	public Pose() {
		this(new Point(), new Quaternion());
	}

	/**
	 * Create a new Pose with the given position value (orientation will 0). A
	 * deep clone of the values will be taken.
	 * 
	 * @param position
	 *            The position value of the pose.
	 */
	public Pose(Point position) {
		this(position, new Quaternion());
	}

	/**
	 * Create a new Pose with the given orientation value (position will 0). A
	 * deep clone of the values will be taken.
	 * 
	 * @param orientation
	 *            The orientation value of the pose.
	 */
	public Pose(Quaternion orientation) {
		this(new Point(), orientation);
	}

	/**
	 * Create a new Pose with the given position and orientation values. A deep
	 * clone of the values will be taken.
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
		this.position = position.clone();
		this.orientation = orientation.clone();
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
	 * Create a deep clone of this Pose.
	 */
	@Override
	public Pose clone() {
		return new Pose(this.position, this.orientation);
	}
}
