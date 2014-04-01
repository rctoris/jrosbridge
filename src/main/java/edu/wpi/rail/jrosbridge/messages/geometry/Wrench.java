package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Wrench message. This represents force in free space,
 * separated into its linear and angular parts.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Wrench extends Message {

	/**
	 * The name of the force field for the message.
	 */
	public static final String FIELD_FORCE = "force";

	/**
	 * The name of the torque field for the message.
	 */
	public static final String FIELD_TORQUE = "torque";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/Wrench";

	private final Vector3 force;
	private final Vector3 torque;

	/**
	 * Create a new Wrench with all 0s.
	 */
	public Wrench() {
		this(new Vector3(), new Vector3());
	}

	/**
	 * Create a new Wrench with the given force and torque values.
	 * 
	 * @param force
	 *            The force value of the wrench.
	 * @param torque
	 *            The torque value of the wrench.
	 */
	public Wrench(Vector3 force, Vector3 torque) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(Wrench.FIELD_FORCE, force.toJsonObject())
				.add(Wrench.FIELD_TORQUE, torque.toJsonObject()).build(),
				Wrench.TYPE);
		this.force = force;
		this.torque = torque;
	}

	/**
	 * Get the force value of this wrench.
	 * 
	 * @return The force value of this wrench.
	 */
	public Vector3 getForce() {
		return this.force;
	}

	/**
	 * Get the torque value of this wrench.
	 * 
	 * @return The torque value of this wrench.
	 */
	public Vector3 getTorque() {
		return this.torque;
	}

	/**
	 * Create a clone of this Wrench.
	 */
	@Override
	public Wrench clone() {
		return new Wrench(this.force.clone(), this.torque.clone());
	}

	/**
	 * Create a new Wrench based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Wrench message based on the given JSON string.
	 */
	public static Wrench fromJsonString(String jsonString) {
		// convert to a message
		return Wrench.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Wrench based on the given Message. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Wrench message based on the given Message.
	 */
	public static Wrench fromMessage(Message m) {
		// get it from the JSON object
		return Wrench.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Wrench based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Wrench message based on the given JSON object.
	 */
	public static Wrench fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Vector3 force = jsonObject.containsKey(Wrench.FIELD_FORCE) ? Vector3
				.fromJsonObject(jsonObject.getJsonObject(Wrench.FIELD_FORCE))
				: new Vector3();
		Vector3 torque = jsonObject.containsKey(Wrench.FIELD_TORQUE) ? Vector3
				.fromJsonObject(jsonObject.getJsonObject(Wrench.FIELD_TORQUE))
				: new Vector3();
		return new Wrench(force, torque);
	}
}
