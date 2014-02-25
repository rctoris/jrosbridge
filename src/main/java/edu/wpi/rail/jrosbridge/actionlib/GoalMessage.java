package edu.wpi.rail.jrosbridge.actionlib;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.core.Param;
import edu.wpi.rail.jrosbridge.messages.Message;

public class GoalMessage extends Message {

	public static final String FIELD_GOAL_ID_STAMP_SECS = "secs";
	public static final String FIELD_GOAL_ID_STAMP_NSECS = "nsecs";
	public static final String FIELD_GOAL_ID_STAMP = "stamp";
	public static final String FIELD_GOAL_ID_ID = "id";
	public static final String FIELD_GOAL_ID = "id";
	public static final String FIELD_GOAL = "id";

	public GoalMessage() {
		// empty goal
		this("", new Message());
	}

	public GoalMessage(String id, Message goal) {
		// construct the stamp message
		JsonObject stamp = Json.createObjectBuilder()
				.add(GoalMessage.FIELD_GOAL_ID_STAMP_SECS, 0)
				.add(GoalMessage.FIELD_GOAL_ID_STAMP_NSECS, 0).build();

		// construct the goal_id message
		JsonObject goalID = Json.createObjectBuilder()
				.add(GoalMessage.FIELD_GOAL_ID_STAMP, stamp)
				.add(GoalMessage.FIELD_GOAL_ID_ID, id).build();

		// construct the entire message
		this.jsonObject = Json.createObjectBuilder()
				.add(GoalMessage.FIELD_GOAL_ID, goalID)
				.add(GoalMessage.FIELD_GOAL, goal.toJsonObject()).build();
	}
}
