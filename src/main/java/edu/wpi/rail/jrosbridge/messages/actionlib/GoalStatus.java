package edu.wpi.rail.jrosbridge.messages.actionlib;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

/**
 * The actionlib_msgs/GoalStatus message. The goal ID should store the time at
 * which this goal was requested. It is used by an action server when it tries
 * to preempt all goals that were requested before a certain time
 * 
 * The id provides a way to associate feedback and result message with specific
 * goal requests. The id specified must be unique.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class GoalStatus extends Message {

	/**
	 * The name of the goal ID field for the message.
	 */
	public static final String FIELD_GOAL_ID = "goal_id";

	/**
	 * The name of the status field for the message.
	 */
	public static final String FIELD_STATUS = "status";

	/**
	 * The name of the text field for the message.
	 */
	public static final String FIELD_TEXT = "text";

	/**
	 * The message type.
	 */
	public static final String TYPE = "actionlib_msgs/GoalStatus";

	/**
	 * The goal has yet to be processed by the action server.
	 */
	public static final byte PENDING = 0;

	/**
	 * The goal is currently being processed by the action server.
	 */
	public static final byte ACTIVE = 1;

	/**
	 * The goal received a cancel request after it started executing and has
	 * since completed its execution (Terminal State).
	 */
	public static final byte PREEMPTED = 2;

	/**
	 * The goal was achieved successfully by the action server (Terminal State).
	 */
	public static final byte SUCCEEDED = 3;

	/**
	 * The goal was aborted during execution by the action server due to some
	 * failure (Terminal State).
	 */
	public static final byte ABORTED = 4;

	/**
	 * The goal was rejected by the action server without being processed,
	 * because the goal was unattainable or invalid (Terminal State).
	 */
	public static final byte REJECTED = 5;

	/**
	 * The goal received a cancel request after it started executing and has not
	 * yet completed execution.
	 */
	public static final byte PREEMPTING = 6;

	/**
	 * The goal received a cancel request before it started executing, but the
	 * action server has not yet confirmed that the goal is canceled.
	 */
	public static final byte RECALLING = 7;

	/**
	 * The goal received a cancel request before it started executing and was
	 * successfully cancelled (Terminal State).
	 */
	public static final byte RECALLED = 8;

	/**
	 * An action client can determine that a goal is LOST. This should not be
	 * sent over the wire by an action server.
	 */
	public static final byte LOST = 9;

	private final GoalID goalID;
	private final byte status;
	private final String text;

	/**
	 * Create a new empty GoalStatus.
	 */
	public GoalStatus() {
		this(new GoalID(), (byte) 0, "");
	}

	/**
	 * Create a new GoalStatus with the given values.
	 * 
	 * @param goalID
	 *            The goal ID for the status.
	 * @param status
	 *            The status value treated as an unsigned 8-bit integer.
	 * @param text
	 *            Allow for the user to associate a string with GoalStatus for
	 *            debugging.
	 */
	public GoalStatus(GoalID goalID, byte status, String text) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(GoalStatus.FIELD_GOAL_ID, goalID.toJsonObject())
				.add(GoalStatus.FIELD_STATUS, Primitive.fromUInt8(status))
				.add(GoalStatus.FIELD_TEXT, text).build(), GoalStatus.TYPE);
		this.goalID = goalID;
		this.status = status;
		this.text = text;
	}

	/**
	 * Get the goal ID value of this GoalStatus.
	 * 
	 * @return The goal ID value of this GoalStatus.
	 */
	public GoalID getGoalID() {
		return this.goalID;
	}

	/**
	 * Get the status value of this GoalStatus. This number should be treated as
	 * an unsigned 8-bit integer.
	 * 
	 * @return The status value of this GoalStatus.
	 */
	public byte getStatus() {
		return this.status;
	}

	/**
	 * Get the text value of this GoalStatus.
	 * 
	 * @return The text value of this GoalStatus.
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Create a clone of this GoalStatus.
	 */
	@Override
	public GoalStatus clone() {
		return new GoalStatus(this.goalID, this.status, this.text);
	}

	/**
	 * Create a new GoalStatus based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A GoalStatus message based on the given JSON string.
	 */
	public static GoalStatus fromJsonString(String jsonString) {
		// convert to a message
		return GoalStatus.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new GoalStatus based on the given Message. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A GoalStatus message based on the given Message.
	 */
	public static GoalStatus fromMessage(Message m) {
		// get it from the JSON object
		return GoalStatus.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new GoalStatus based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A GoalStatus message based on the given JSON object.
	 */
	public static GoalStatus fromJsonObject(JsonObject jsonObject) {
		// check the fields
		GoalID goalID = jsonObject.containsKey(GoalStatus.FIELD_GOAL_ID) ? GoalID
				.fromJsonObject(jsonObject
						.getJsonObject(GoalStatus.FIELD_GOAL_ID))
				: new GoalID();
		byte status = jsonObject.containsKey(GoalStatus.FIELD_STATUS) ? Primitive
				.toUInt8((short) jsonObject.getInt(GoalStatus.FIELD_STATUS))
				: 0;
		String text = jsonObject.containsKey(GoalStatus.FIELD_TEXT) ? jsonObject
				.getString(GoalStatus.FIELD_TEXT) : "";
		return new GoalStatus(goalID, status, text);
	}
}
