package edu.wpi.rail.jrosbridge.math;

/**
 * A Vector3 represents a 3-dimensional vector with x, y, and z components.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 18, 2014
 */
public class Vector3 {

	private double x;
	private double y;
	private double z;

	/**
	 * Create a new vector with all 0s.
	 */
	public Vector3() {
		this(0);
	}

	/**
	 * Create a new vector with the given x value (y and z values will 0).
	 * 
	 * @param x
	 *            The x value of the vector.
	 */
	public Vector3(double x) {
		this(x, 0);
	}

	/**
	 * Create a new vector with the given x and y values (the z value will be
	 * set to 0).
	 * 
	 * @param x
	 *            The x value of the vector.
	 * @param y
	 *            The y value of the vector.
	 */
	public Vector3(double x, double y) {
		this(x, y, 0);
	}

	/**
	 * Create a new vector with the given values.
	 * 
	 * @param x
	 *            The x value of the vector.
	 * @param y
	 *            The y value of the vector.
	 * @param z
	 *            The z value of the vector.
	 */
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Get the x value of this vector.
	 * 
	 * @return The x value of this vector.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Set the x value of this vector.
	 * 
	 * @param x
	 *            The x value of this vector.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Get the y value of this vector.
	 * 
	 * @return The y value of this vector.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Set the x value of this vector.
	 * 
	 * @param y
	 *            The y value of this vector.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Get the z value of this vector.
	 * 
	 * @return The z value of this vector.
	 */
	public double getZ() {
		return this.z;
	}

	/**
	 * Set the z value of this vector.
	 * 
	 * @param z
	 *            The z value of this vector.
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Set the values of this vector to the sum of itself and the given vector.
	 * 
	 * @param v
	 *            The vector to add with.
	 */
	public void add(Vector3 v) {
		this.x += v.getX();
		this.y += v.getY();
		this.z += v.getZ();
	}

	/**
	 * Set the values of this vector to the difference of itself and the given
	 * vector.
	 * 
	 * @param v
	 *            The vector to subtract with.
	 */
	public void subtract(Vector3 v) {
		this.x -= v.getX();
		this.y -= v.getY();
		this.z -= v.getZ();
	};

	/**
	 * Multiply the given Quaternion with this vector.
	 * 
	 * @param q
	 *            The quaternion to multiply with.
	 */
	public void multiplyQuaternion(Quaternion q) {
		double ix = q.getW() * this.x + q.getY() * this.z - q.getZ() * this.y;
		double iy = q.getW() * this.y + q.getZ() * this.x - q.getX() * this.z;
		double iz = q.getW() * this.z + q.getX() * this.y - q.getY() * this.x;
		double iw = -q.getW() * this.x - q.getY() * this.y - q.getZ() * this.z;

		this.x = ix * q.getW() + iw * -q.getX() + iy * -q.getZ() - iz
				* -q.getY();
		this.y = iy * q.getW() + iw * -q.getY() + iz * -q.getX() - ix
				* -q.getZ();
		this.z = iz * q.getW() + iw * -q.getZ() + ix * -q.getY() - iy
				* -q.getX();
	}

	/**
	 * Check if the given Object is the same as the vector. Two vectors are the
	 * same if their x, y, and z values are equal.
	 * 
	 * @param o
	 *            The Object to check.
	 * @return If the Object is equal to this Vector3.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof Vector3) {
			Vector3 p = (Vector3) o;
			return p.getX() == this.getX() && p.getY() == this.getY()
					&& p.getZ() == this.getZ();
		} else {
			return false;
		}
	}

	/**
	 * Create a clone of this Vector3.
	 * 
	 * @return A clone of this Vector3.
	 */
	public Vector3 clone() {
		return new Vector3(this.x, this.y, this.z);
	}
}
