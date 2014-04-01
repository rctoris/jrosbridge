package edu.wpi.rail.jrosbridge.messages.geometry;

import java.io.StringReader;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;

/**
 * The geometry_msgs/PoseArray message. An array of poses with a header for
 * global reference.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class PoseArray extends Message {

	/**
	 * The name of the header field for the message.
	 */
	public static final String FIELD_HEADER = "header";

	/**
	 * The name of the poses field for the message.
	 */
	public static final String FIELD_POSES = "poses";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/PoseArray";

	private final Header header;
	private final Pose[] poses;

	/**
	 * Create a new PoseArray with no poses.
	 */
	public PoseArray() {
		this(new Header(), new Pose[] {});
	}

	/**
	 * Create a new PoseArray with the given set of poses and header. The array
	 * of poses will be copied into this object.
	 * 
	 * @param header
	 *            The message header.
	 * @param poses
	 *            The poses of the pose array.
	 */
	public PoseArray(Header header, Pose[] poses) {
		// build the JSON object
		super(Json
				.createObjectBuilder()
				.add(PoseArray.FIELD_HEADER, header.toJsonObject())
				.add(PoseArray.FIELD_POSES,
						Json.createReader(
								new StringReader(Arrays.deepToString(poses)))
								.readArray()).build(), PoseArray.TYPE);

		this.header = header;
		// copy the poses
		this.poses = new Pose[poses.length];
		System.arraycopy(poses, 0, this.poses, 0, poses.length);
	}

	/**
	 * Get the number of poses in this pose array.
	 * 
	 * @return The number of poses in this pose array.
	 */
	public int size() {
		return this.poses.length;
	}

	/**
	 * Get the pose in the pose array at the given index.
	 * 
	 * @param index
	 *            The index to get the pose of.
	 * @return The pose at the given index.
	 */
	public Pose get(int index) {
		return this.poses[index];
	}

	/**
	 * Get the poses of this pose array. Note that this array should never be
	 * modified directly.
	 * 
	 * @return The poses of this pose array.
	 */
	public Pose[] getPoses() {
		return this.poses;
	}

	/**
	 * Get the header value of this pose array.
	 * 
	 * @return The poses header value of this pose array.
	 */
	public Header getHeader() {
		return this.header;
	}

	/**
	 * Create a clone of this PoseArray.
	 */
	@Override
	public PoseArray clone() {
		return new PoseArray(this.header, this.poses);
	}

	/**
	 * Create a new PoseArray based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A PoseArray message based on the given JSON string.
	 */
	public static PoseArray fromJsonString(String jsonString) {
		// convert to a message
		return PoseArray.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new PoseArray based on the given Message. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A PoseArray message based on the given Message.
	 */
	public static PoseArray fromMessage(Message m) {
		// get it from the JSON object
		return PoseArray.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new PoseArray based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A PoseArray message based on the given JSON object.
	 */
	public static PoseArray fromJsonObject(JsonObject jsonObject) {
		// grab the header if there is one
		Header header = jsonObject.containsKey(PoseArray.FIELD_HEADER) ? Header
				.fromJsonObject(jsonObject
						.getJsonObject(PoseArray.FIELD_HEADER)) : new Header();

		// check the array
		JsonArray jsonPoses = jsonObject.getJsonArray(PoseArray.FIELD_POSES);
		if (jsonPoses != null) {
			// convert each pose
			Pose[] poses = new Pose[jsonPoses.size()];
			for (int i = 0; i < poses.length; i++) {
				poses[i] = Pose.fromJsonObject(jsonPoses.getJsonObject(i));
			}
			return new PoseArray(header, poses);
		} else {
			return new PoseArray(header, new Pose[] {});
		}
	}
}
