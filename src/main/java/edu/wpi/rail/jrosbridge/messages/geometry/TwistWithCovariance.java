package edu.wpi.rail.jrosbridge.messages.geometry;

import java.io.StringReader;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/TwistWithCovariance message. This expresses velocity in
 * free space with uncertainty.
 * 
 * The orientation parameters use a fixed-axis representation. In order, the
 * parameters are: (x, y, z, rotation about X axis, rotation about Y axis,
 * rotation about Z axis)
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class TwistWithCovariance extends Message {

	/**
	 * The name of the twist field for the message.
	 */
	public static final String FIELD_TWIST = "twist";

	/**
	 * The name of the covariance field for the message.
	 */
	public static final String FIELD_COVARIANCE = "covariance";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/TwistWithCovariance";

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
	public static final int COVARIANCE_SIZE = TwistWithCovariance.COVARIANCE_ROWS
			* TwistWithCovariance.COVARIANCE_COLUMNS;

	private final Twist twist;
	private final double[] covariance;
	private final double[][] covarianceMatrix;

	/**
	 * Create a new TwistWithCovariance with all 0 values.
	 */
	public TwistWithCovariance() {
		this(new Twist(), new double[] {});
	}

	/**
	 * Create a new TwistWithCovariance with the given twist. The covariance
	 * matrix will be all 0s.
	 * 
	 * @param twist
	 *            The twist value of the twist.
	 */
	public TwistWithCovariance(Twist twist) {
		this(twist, new double[TwistWithCovariance.COVARIANCE_SIZE]);
	}

	/**
	 * Create a new TwistWithCovariance with the given twist and covariance
	 * matrix. If the given array is not of size
	 * TwistWithCovariance.COVARIANCE_SIZE, all 0s will be used instead. The
	 * values of the array will be copied into this object.
	 * 
	 * @param twist
	 *            The twist value of the twist.
	 * @param covariance
	 *            The covariance matrix as an array.
	 */
	public TwistWithCovariance(Twist twist, double[] covariance) {
		// build the JSON object
		super(
				Json.createObjectBuilder()
						.add(TwistWithCovariance.FIELD_TWIST,
								twist.toJsonObject())
						.add(TwistWithCovariance.FIELD_COVARIANCE,
								Json.createReader(
										new StringReader(
												(covariance.length == TwistWithCovariance.COVARIANCE_SIZE) ? Arrays
														.toString(covariance)
														: Arrays.toString(new double[TwistWithCovariance.COVARIANCE_SIZE])))
										.readArray()).build(),
				TwistWithCovariance.TYPE);

		this.twist = twist;
		// create the arrays
		this.covariance = new double[TwistWithCovariance.COVARIANCE_SIZE];
		this.covarianceMatrix = new double[TwistWithCovariance.COVARIANCE_ROWS][TwistWithCovariance.COVARIANCE_COLUMNS];
		if (covariance.length == TwistWithCovariance.COVARIANCE_SIZE) {
			// copy the 1-D array
			System.arraycopy(covariance, 0, this.covariance, 0,
					TwistWithCovariance.COVARIANCE_SIZE);
			// create a 2D matrix
			for (int i = 0; i < TwistWithCovariance.COVARIANCE_ROWS; i++) {
				System.arraycopy(this.covariance, i
						* TwistWithCovariance.COVARIANCE_COLUMNS,
						this.covarianceMatrix[i], 0,
						TwistWithCovariance.COVARIANCE_COLUMNS);
			}
		}
	}

	/**
	 * Get the twist value of this twist.
	 * 
	 * @return The twist value of this twist.
	 */
	public Twist getTwist() {
		return this.twist;
	}

	/**
	 * Get the covariance matrix of this twist as an array. Note that this array
	 * should never be modified directly.
	 * 
	 * @return The covariance matrix of this twist as an array.
	 */
	public double[] getCovariance() {
		return this.covariance;
	}

	/**
	 * Get the covariance matrix of this twist as a 2d-array. Note that this
	 * array should never be modified directly.
	 * 
	 * @return The covariance matrix of this twist as a 2d-array.
	 */
	public double[][] getCovarianceMatrix() {
		return this.covarianceMatrix;
	}

	/**
	 * Create a clone of this TwistWithCovariance.
	 */
	@Override
	public TwistWithCovariance clone() {
		return new TwistWithCovariance(this.twist, this.covariance);
	}

	/**
	 * Create a new TwistWithCovariance based on the given JSON string. Any
	 * missing values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A TwistWithCovariance message based on the given JSON string.
	 */
	public static TwistWithCovariance fromJsonString(String jsonString) {
		// convert to a message
		return TwistWithCovariance.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new TwistWithCovariance based on the given Message. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A TwistWithCovariance message based on the given Message.
	 */
	public static TwistWithCovariance fromMessage(Message m) {
		// get it from the JSON object
		return TwistWithCovariance.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new TwistWithCovariance based on the given JSON object. Any
	 * missing values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A TwistWithCovariance message based on the given JSON object.
	 */
	public static TwistWithCovariance fromJsonObject(JsonObject jsonObject) {
		// grab the twist if there is one
		Twist twist = jsonObject.containsKey(TwistWithCovariance.FIELD_TWIST) ? Twist
				.fromJsonObject(jsonObject
						.getJsonObject(TwistWithCovariance.FIELD_TWIST))
				: new Twist();

		// check the array
		JsonArray jsonArray = jsonObject
				.getJsonArray(TwistWithCovariance.FIELD_COVARIANCE);
		if (jsonArray != null) {
			// convert each value
			double[] twists = new double[jsonArray.size()];
			for (int i = 0; i < twists.length; i++) {
				twists[i] = jsonArray.getJsonNumber(i).doubleValue();
			}
			return new TwistWithCovariance(twist, twists);
		} else {
			return new TwistWithCovariance(twist,
					new double[TwistWithCovariance.COVARIANCE_SIZE]);
		}
	}
}
