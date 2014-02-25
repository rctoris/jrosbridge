package edu.wpi.rail.jrosbridge.math;

import edu.wpi.rail.jrosbridge.messages.geometry.Quaternion;
import edu.wpi.rail.jrosbridge.messages.geometry.Vector3;

/**
 * A Transform in 3-space.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 18, 2014
 */
public class Transform {

	private Vector3 translation;
	private Quaternion rotation;

	/**
	 * Create a new transform with all 0 values.
	 */
	public Transform() {
		this(new Vector3(), new Quaternion());
	}

	/**
	 * Create a transform with the given translational component and a 0
	 * rotational component. The values are copied directly into this object
	 * (i.e., cloned).
	 * 
	 * @param translation
	 *            The translational component of this transform.
	 */
	public Transform(Vector3 translation) {
		this(translation, new Quaternion());
	}

	/**
	 * Create a transform with the given rotational component and a 0
	 * translational component. The values are copied directly into this object
	 * (i.e., cloned).
	 * 
	 * @param rotation
	 *            The rotational component of this transform.
	 */
	public Transform(Quaternion rotation) {
		this(new Vector3(), rotation);
	}

	/**
	 * Create a transform with the given rotational and translational
	 * components. The values are copied directly into this object (i.e.,
	 * cloned).
	 * 
	 * @param translation
	 *            The translational component of this transform.
	 * @param rotation
	 *            The rotational component of this transform.
	 */
	public Transform(Vector3 translation, Quaternion rotation) {
		this.translation = translation.clone();
		this.rotation = rotation.clone();
	}

	/**
	 * Get the translational component of this transform.
	 * 
	 * @return The translational component of this transform.
	 */
	public Vector3 getTranslation() {
		return this.translation;
	}

	/**
	 * Set the translational component of this transform.
	 * 
	 * @param translation
	 *            The translational component of this transform.
	 */
	public void setTranslation(Vector3 translation) {
		this.translation = translation;
	}

	/**
	 * Get the rotational component of this transform.
	 * 
	 * @return The rotational component of this transform.
	 */
	public Quaternion getRotation() {
		return this.rotation;
	}

	/**
	 * Set the rotational component of this transform.
	 * 
	 * @param rotation
	 *            The rotational component of this transform.
	 */
	public void setRotation(Quaternion rotation) {
		this.rotation = rotation;
	}

	/**
	 * Check if the given Object is the same as the transform. Two transforms
	 * are the same if their translational and rotational components are equal.
	 * 
	 * @param o
	 *            The Object to check.
	 * @return If the Object is equal to this Transform.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof Transform) {
			Transform tf = (Transform) o;
			return this.translation.equals(tf.getTranslation())
					&& this.rotation.equals(tf.getRotation());
		} else {
			return false;
		}
	}

	/**
	 * Create a clone of this Transform.
	 * 
	 * @return A clone of this Transform.
	 */
	public Transform clone() {
		return new Transform(this.translation, this.rotation);
	}
}
