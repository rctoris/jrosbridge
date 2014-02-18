package edu.wpi.rail.jrosbridge.math;

/**
 * A Vector3 represents a 4-dimensional vector with x, y, z, and w components.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 18, 2014
 */
public class Quaternion {

	private double x;
	private double y;
	private double z;
	private double w;

	/**
	 * Create a new quaternion with all 0s.
	 */
	public Quaternion() {
		this(0);
	}

	/**
	 * Create a new quaternion with the given x value (y, z, and w values will
	 * 0).
	 * 
	 * @param x
	 *            The x value of the quaternion.
	 */
	public Quaternion(double x) {
		this(x, 0);
	}

	/**
	 * Create a new quaternion with the given x and y values (z and w values
	 * will be set to 0).
	 * 
	 * @param x
	 *            The x value of the quaternion.
	 * @param y
	 *            The y value of the quaternion.
	 */
	public Quaternion(double x, double y) {
		this(x, y, 0);
	}

	/**
	 * Create a new quaternion with the given x, y, and z values (the w value
	 * will be set to 0).
	 * 
	 * @param x
	 *            The x value of the quaternion.
	 * @param y
	 *            The y value of the quaternion.
	 * @param z
	 *            The z value of the quaternion.
	 */
	public Quaternion(double x, double y, double z) {
		this(x, y, z, 0);
	}

	/**
	 * Create a new quaternion with the given values.
	 * 
	 * @param x
	 *            The x value of the quaternion.
	 * @param y
	 *            The y value of the quaternion.
	 * @param z
	 *            The z value of the quaternion.
	 * @param w
	 *            The w value of the quaternion.
	 */
	public Quaternion(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	/**
	 * Get the x value of this quaternion.
	 * 
	 * @return The x value of this quaternion.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Set the x value of this quaternion.
	 * 
	 * @param x
	 *            The x value of this quaternion.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Get the y value of this quaternion.
	 * 
	 * @return The y value of this quaternion.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Set the x value of this quaternion.
	 * 
	 * @param y
	 *            The y value of this quaternion.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Get the z value of this quaternion.
	 * 
	 * @return The z value of this quaternion.
	 */
	public double getZ() {
		return this.z;
	}

	/**
	 * Set the z value of this quaternion.
	 * 
	 * @param z
	 *            The z value of this quaternion.
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Get the w value of this quaternion.
	 * 
	 * @return The w value of this quaternion.
	 */
	public double getW() {
		return this.w;
	}

	/**
	 * Set the w value of this quaternion.
	 * 
	 * @param w
	 *            The w value of this quaternion.
	 */
	public void setW(double w) {
		this.w = w;
	}

	/**
	 * Perform a conjugation on this quaternion.
	 */
	public void conjugate() {
		this.x *= -1;
		this.y *= -1;
		this.z *= -1;
	}

	/**
	 * Perform a normalization on this quaternion.
	 */
	public void normalize() {
		double l = Math.sqrt(this.x * this.x + this.y * this.y + this.z
				* this.z + this.w * this.w);
		if (l == 0) {
			this.x = 0;
			this.y = 0;
			this.z = 0;
			this.w = 1;
		} else {
			l = 1 / l;
			this.x = this.x * l;
			this.y = this.y * l;
			this.z = this.z * l;
			this.w = this.w * l;
		}
	}

	/**
	 * Convert this quaternion into its inverse.
	 */
	public void invert() {
		this.conjugate();
		this.normalize();
	}

	/**
	 * Set the values of this quaternion to the product of itself and the given
	 * quaternion.
	 * 
	 * @param q
	 *            The quaternion to multiply with.
	 */
	public void multiply(Quaternion q) {
		double newX = this.x * q.getW() + this.y * q.getZ() - this.z * q.getY()
				+ this.w * q.getX();
		double newY = -this.x * q.getZ() + this.y * q.getW() + this.z
				* q.getX() + this.w * q.getY();
		double newZ = this.x * q.getY() - this.y * q.getX() + this.z * q.getW()
				+ this.w * q.getZ();
		double newW = -this.x * q.getX() - this.y * q.getY() - this.z
				* q.getZ() + this.w * q.getW();
		this.x = newX;
		this.y = newY;
		this.z = newZ;
		this.w = newW;
	}

	/**
	 * Check if the given Object is the same as the quaternion. Two quaternions
	 * are the same if their x, y, z, and w values are equal.
	 * 
	 * @param o
	 *            The Object to check.
	 * @return If the Object is equal to this Quaternion.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof Quaternion) {
			Quaternion q = (Quaternion) o;
			return q.getX() == this.getX() && q.getY() == this.getY()
					&& q.getZ() == this.getZ() && q.getW() == this.getW();
		} else {
			return false;
		}
	}

	/**
	 * Create a clone of this Quaternion.
	 * 
	 * @return A clone of this Quaternion.
	 */
	public Quaternion clone() {
		return new Quaternion(this.x, this.y, this.z, this.w);
	}
}
