package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Transform message. This represents the transform between
 * two coordinate frames in free space.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 25, 2014
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

	private Vector3 translation;
	private Quaternion rotation;

	/**
	 * Create a new Transform with all 0s.
	 */
	public Transform() {
		this(new Vector3(), new Quaternion());
	}

	/**
	 * Create a new Transform with the given translation value (rotation will
	 * 0). A deep clone of the values will be taken.
	 * 
	 * @param translation
	 *            The translation value of the transform.
	 */
	public Transform(Vector3 translation) {
		this(translation, new Quaternion());
	}

	/**
	 * Create a new Transform with the given rotation value (translation will
	 * 0). A deep clone of the values will be taken.
	 * 
	 * @param rotation
	 *            The rotation value of the transform.
	 */
	public Transform(Quaternion rotation) {
		this(new Vector3(), rotation);
	}

	/**
	 * Create a new Transform with the given translation and rotation values. A
	 * deep clone of the values will be taken.
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
		this.translation = translation.clone();
		this.rotation = rotation.clone();
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
	 * Create a deep clone of this Transform.
	 */
	@Override
	public Transform clone() {
		return new Transform(this.translation, this.rotation);
	}
}
