package edu.wpi.rail.jrosbridge.messages.geometry;

import java.io.StringReader;
import java.util.Arrays;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/PoseWithCovariance message. This represents a pose in free
 * space with uncertainty.
 * 
 * The orientation parameters use a fixed-axis representation. # In order, the
 * parameters are: (x, y, z, rotation about X axis, rotation about Y axis,
 * rotation about Z axis).
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 25, 2014
 */
public class PoseWithCovariance extends Message {

	/**
	 * The name of the pose field for the message.
	 */
	public static final String FIELD_POSE = "pose";

	/**
	 * The name of the covariance field for the message.
	 */
	public static final String FIELD_COVARIANCE = "covariance";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/PoseWithCovariance";

	/**
	 * The number of rows in the covariance matrix.
	 */
	public static final int COVARIANCE_ROWS = 6;

	/**
	 * The number of columns in the covariance matrix.
	 */
	public static final int COVARIANCE_COLUMNS = 6;

	/**
	 * The size of the covariance matrix.
	 */
	public static final int COVARIANCE_SIZE = PoseWithCovariance.COVARIANCE_ROWS
			* PoseWithCovariance.COVARIANCE_COLUMNS;

	private Pose pose;
	private double[] covariance;
	private double[][] covarianceMatrix;

	/**
	 * Create a new PoseWithCovariance with all 0 values.
	 */
	public PoseWithCovariance() {
		this(new Pose(), new double[PoseWithCovariance.COVARIANCE_SIZE]);
	}

	/**
	 * Create a new PoseWithCovariance with the given pose. The covariance
	 * matrix will be all 0s.
	 * 
	 * @param pose
	 *            The pose value of the pose.
	 */
	public PoseWithCovariance(Pose pose) {
		this(pose, new double[PoseWithCovariance.COVARIANCE_SIZE]);
	}

	/**
	 * Create a new PoseWithCovariance with the given covariance matrix. If the
	 * given array is not of size PoseWithCovariance.COVARIANCE_SIZE, all 0s
	 * will be used instead. The pose values will be all 0s. The values of the
	 * array will be copied into this object.
	 * 
	 * @param covariance
	 *            The covariance matrix.
	 */
	public PoseWithCovariance(double[] covariance) {
		this(new Pose(), covariance);
	}

	/**
	 * Create a new PoseWithCovariance with the given pose and covariance
	 * matrix. If the given array is not of size
	 * PoseWithCovariance.COVARIANCE_SIZE, all 0s will be used instead. The
	 * values of the array will be copied into this object.
	 * 
	 * @param pose
	 *            The pose value of the pose.
	 * @param covariance
	 *            The covariance matrix.
	 */
	public PoseWithCovariance(Pose pose, double[] covariance) {
		// build the JSON object
		super(
				Json.createObjectBuilder()
						.add(PoseWithCovariance.FIELD_POSE, pose.toJsonObject())
						.add(PoseWithCovariance.FIELD_COVARIANCE,
								Json.createReader(
										new StringReader(
												(covariance.length == PoseWithCovariance.COVARIANCE_SIZE) ? Arrays
														.toString(covariance)
														: Arrays.toString(new double[PoseWithCovariance.COVARIANCE_SIZE])))
										.readArray()).build(),
				PoseWithCovariance.TYPE);

		// create the arrays
		this.covariance = new double[PoseWithCovariance.COVARIANCE_SIZE];
		this.covarianceMatrix = new double[PoseWithCovariance.COVARIANCE_ROWS][PoseWithCovariance.COVARIANCE_COLUMNS];
		if (covariance.length == PoseWithCovariance.COVARIANCE_SIZE) {
			// copy the 1-D array
			System.arraycopy(covariance, 0, this.covariance, 0,
					PoseWithCovariance.COVARIANCE_SIZE);
			// create a 2D matrix
			for (int i = 0; i < PoseWithCovariance.COVARIANCE_ROWS; i++) {
				System.arraycopy(this.covariance, i
						* PoseWithCovariance.COVARIANCE_COLUMNS,
						this.covarianceMatrix[i], 0,
						PoseWithCovariance.COVARIANCE_COLUMNS);
			}
		}
	}

	/**
	 * Get the pose value of this pose.
	 * 
	 * @return The pose value of this pose.
	 */
	public Pose getPose() {
		return this.pose;
	}

	/**
	 * Get the covariance matrix of this pose as an array.
	 * 
	 * @return The covariance matrix of this pose as an array.
	 */
	public double[] getCovariance() {
		return this.covariance;
	}

	/**
	 * Get the covariance matrix of this pose as a 2d-array.
	 * 
	 * @return The covariance matrix of this pose as a 2d-array.
	 */
	public double[][] getCovarianceMatrix() {
		return this.covarianceMatrix;
	}

	/**
	 * Create a deep clone of this PoseWithCovariance.
	 */
	@Override
	public PoseWithCovariance clone() {
		return new PoseWithCovariance(this.pose, this.covariance);
	}
}
