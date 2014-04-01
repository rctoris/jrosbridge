package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Transform message. This represents the transform between
 * two coordinate frames in free space.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Transform extends Message {

	/**
	 * The name of the translation field for the message.
	 */
	public static final String FIELD_TRANSLATION = "translation";

	/**
	 * The name of the rotation field for the message.
	 */
	public static final String FIELD_ROTATION = "rotation";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/Transform";

	private final Vector3 translation;
	private final Quaternion rotation;

	/**
	 * Create a new Transform with all 0s.
	 */
	public Transform() {
		this(new Vector3(), new Quaternion());
	}

	/**
	 * Create a new Transform with the given translation and rotation values.
	 * 
	 * @param translation
	 *            The translation value of the transform.
	 * @param rotation
	 *            The rotation value of the transform.
	 */
	public Transform(Vector3 translation, Quaternion rotation) {
		// build the JSON object
		super(
				Json.createObjectBuilder()
						.add(Transform.FIELD_TRANSLATION,
								translation.toJsonObject())
						.add(Transform.FIELD_ROTATION, rotation.toJsonObject())
						.build(), Transform.TYPE);
		this.translation = translation;
		this.rotation = rotation;
	}

	/**
	 * Get the translation value of this transform.
	 * 
	 * @return The translation value of this transform.
	 */
	public Vector3 getTranslation() {
		return this.translation;
	}

	/**
	 * Get the rotation value of this transform.
	 * 
	 * @return The rotation value of this transform.
	 */
	public Quaternion getRotation() {
		return this.rotation;
	}

	/**
	 * Create a clone of this Transform.
	 */
	@Override
	public Transform clone() {
		return new Transform(this.translation, this.rotation);
	}

	/**
	 * Create a new Transform based on the given JSON string. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Transform message based on the given JSON string.
	 */
	public static Transform fromJsonString(String jsonString) {
		// convert to a message
		return Transform.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Transform based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Transform message based on the given Message.
	 */
	public static Transform fromMessage(Message m) {
		// get it from the JSON object
		return Transform.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Transform based on the given JSON object. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Transform message based on the given JSON object.
	 */
	public static Transform fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Vector3 translation = jsonObject.containsKey(Transform.FIELD_TRANSLATION) ? Vector3
				.fromJsonObject(jsonObject.getJsonObject(Transform.FIELD_TRANSLATION))
				: new Vector3();
		Quaternion rotation = jsonObject.containsKey(Transform.FIELD_ROTATION) ? Quaternion
				.fromJsonObject(jsonObject
						.getJsonObject(Transform.FIELD_ROTATION))
				: new Quaternion();
		return new Transform(translation, rotation);
	}
}
