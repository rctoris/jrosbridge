package edu.wpi.rail.jrosbridge.math;

import edu.wpi.rail.jrosbridge.messages.geometry.Quaternion;
import edu.wpi.rail.jrosbridge.messages.geometry.Vector3;

/**
 * A Pose in 3-space.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 18, 2014
 */
public class Pose {

	private Vector3 position;
	private Quaternion orientation;

	/**
	 * Create a new pose with all 0 values.
	 */
	public Pose() {
		this(new Vector3(), new Quaternion());
	}

	/**
	 * Create a pose with the given position and a 0 orientation. The values are
	 * copied directly into this object (i.e., cloned).
	 * 
	 * @param position
	 *            The position of this pose.
	 */
	public Pose(Vector3 position) {
		this(position, new Quaternion());
	}

	/**
	 * Create a pose with the given orientation and a 0 position. The values are
	 * copied directly into this object (i.e., cloned).
	 * 
	 * @param orientation
	 *            The orientation of this pose.
	 */
	public Pose(Quaternion orientation) {
		this(new Vector3(), orientation);
	}

	/**
	 * Create a pose with the given orientation and position. The values are
	 * copied directly into this object (i.e., cloned).
	 * 
	 * @param position
	 *            The position of this pose.
	 * @param orientation
	 *            The orientation of this pose.
	 */
	public Pose(Vector3 position, Quaternion orientation) {
		this.position = position.clone();
		this.orientation = orientation.clone();
	}

	/**
	 * Get the position of this pose.
	 * 
	 * @return The position of this pose.
	 */
	public Vector3 getPosition() {
		return this.position;
	}

	/**
	 * Set the position of this pose.
	 * 
	 * @param position
	 *            The position of this pose.
	 */
	public void setPosition(Vector3 position) {
		this.position = position;
	}

	/**
	 * Get the orientation of this pose.
	 * 
	 * @return The orientation of this pose.
	 */
	public Quaternion getOrientation() {
		return this.orientation;
	}

	/**
	 * Set the orientation of this pose.
	 * 
	 * @param orientation
	 *            The orientation of this pose.
	 */
	public void setOrientation(Quaternion orientation) {
		this.orientation = orientation;
	}

	/**
	 * Apply a transform against this pose.
	 * 
	 * @param tf
	 *            the transform
	 */
	public void applyTransform(Transform tf) {
		this.position.multiplyQuaternion(tf.getRotation());
		this.position.add(tf.getTranslation());
		Quaternion tmp = tf.getRotation().clone();
		tmp.multiply(this.orientation);
		this.orientation = tmp;
	}

	/**
	 * Check if the given Object is the same as the pose. Two poses are the same
	 * if their positions and orientations are equal.
	 * 
	 * @param o
	 *            The Object to check.
	 * @return If the Object is equal to this Pose.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof Pose) {
			Pose p = (Pose) o;
			return this.position.equals(p.getPosition())
					&& this.orientation.equals(p.getOrientation());
		} else {
			return false;
		}
	}

	/**
	 * Create a clone of this Pose.
	 * 
	 * @return A clone of this Pose.
	 */
	public Pose clone() {
		return new Pose(this.position, this.orientation);
	}
}
