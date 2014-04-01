package edu.wpi.rail.jrosbridge.messages.actionlib;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Time;

/**
 * The actionlib_msgs/GoalID message. The stamp should store the time at which
 * this goal was requested. It is used by an action server when it tries to
 * preempt all goals that were requested before a certain time
 * 
 * The id provides a way to associate feedback and result message with specific
 * goal requests. The id specified must be unique.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class GoalID extends Message {

	/**
	 * The name of the stamp field for the message.
	 */
	public static final String FIELD_STAMP = "stamp";

	/**
	 * The name of the ID field for the message.
	 */
	public static final String FIELD_ID = "id";

	/**
	 * The message type.
	 */
	public static final String TYPE = "actionlib_msgs/GoalID";

	private final Time stamp;
	private final String id;

	/**
	 * Create a new empty GoalID.
	 */
	public GoalID() {
		this(new Time(), "");
	}

	/**
	 * Create a new GoalID with the given values.
	 * 
	 * @param stamp
	 *            The timestamp for the ID.
	 * @param ID
	 *            The unique goal ID.
	 */
	public GoalID(Time stamp, String id) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(GoalID.FIELD_STAMP, stamp.toJsonObject())
				.add(GoalID.FIELD_ID, id).build(), GoalID.TYPE);
		this.stamp = stamp;
		this.id = id;
	}

	/**
	 * Get the stamp value of this GoalID.
	 * 
	 * @return The stamp value of this GoalID.
	 */
	public Time getStamp() {
		return this.stamp;
	}

	/**
	 * Get the ID value of this GoalID.
	 * 
	 * @return The ID value of this GoalID.
	 */
	public String getID() {
		return this.id;
	}

	/**
	 * Create a clone of this GoalID.
	 */
	@Override
	public GoalID clone() {
		return new GoalID(this.stamp, this.id);
	}

	/**
	 * Create a new GoalID based on the given JSON string. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A GoalID message based on the given JSON string.
	 */
	public static GoalID fromJsonString(String jsonString) {
		// convert to a message
		return GoalID.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new GoalID based on the given Message. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A GoalID message based on the given Message.
	 */
	public static GoalID fromMessage(Message m) {
		// get it from the JSON object
		return GoalID.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new GoalID based on the given JSON object. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A GoalID message based on the given JSON object.
	 */
	public static GoalID fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Time stamp = jsonObject.containsKey(GoalID.FIELD_STAMP) ? Time
				.fromJsonObject(jsonObject.getJsonObject(GoalID.FIELD_STAMP))
				: new Time();
		String id = jsonObject.containsKey(GoalID.FIELD_ID) ? jsonObject
				.getString(GoalID.FIELD_ID) : "";
		return new GoalID(stamp, id);
	}
}
