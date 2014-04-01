package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Twist message. This expresses velocity in free space broken
 * into its linear and angular parts.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Twist extends Message {

	/**
	 * The name of the linear field for the message.
	 */
	public static final String FIELD_LINEAR = "linear";

	/**
	 * The name of the angular field for the message.
	 */
	public static final String FIELD_ANGULAR = "angular";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/Twist";

	private final Vector3 linear;
	private final Vector3 angular;

	/**
	 * Create a new Twist with all 0s.
	 */
	public Twist() {
		this(new Vector3(), new Vector3());
	}

	/**
	 * Create a new Twist with the given linear and angular values.
	 * 
	 * @param linear
	 *            The linear value of the twist.
	 * @param angular
	 *            The angular value of the twist.
	 */
	public Twist(Vector3 linear, Vector3 angular) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(Twist.FIELD_LINEAR, linear.toJsonObject())
				.add(Twist.FIELD_ANGULAR, angular.toJsonObject()).build(),
				Twist.TYPE);
		this.linear = linear;
		this.angular = angular;
	}

	/**
	 * Get the linear value of this twist.
	 * 
	 * @return The linear value of this twist.
	 */
	public Vector3 getLinear() {
		return this.linear;
	}

	/**
	 * Get the angular value of this twist.
	 * 
	 * @return The angular value of this twist.
	 */
	public Vector3 getAngular() {
		return this.angular;
	}

	/**
	 * Create a clone of this Twist.
	 */
	@Override
	public Twist clone() {
		return new Twist(this.linear, this.angular);
	}

	/**
	 * Create a new Twist based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Twist message based on the given JSON string.
	 */
	public static Twist fromJsonString(String jsonString) {
		// convert to a message
		return Twist.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Twist based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Twist message based on the given Message.
	 */
	public static Twist fromMessage(Message m) {
		// get it from the JSON object
		return Twist.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Twist based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Twist message based on the given JSON object.
	 */
	public static Twist fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Vector3 linear = jsonObject.containsKey(Twist.FIELD_LINEAR) ? Vector3
				.fromJsonObject(jsonObject.getJsonObject(Twist.FIELD_LINEAR))
				: new Vector3();
		Vector3 angular = jsonObject.containsKey(Twist.FIELD_ANGULAR) ? Vector3
				.fromJsonObject(jsonObject.getJsonObject(Twist.FIELD_ANGULAR))
				: new Vector3();
		return new Twist(linear, angular);
	}
}
