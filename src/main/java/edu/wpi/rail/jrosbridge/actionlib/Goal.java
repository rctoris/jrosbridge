package edu.wpi.rail.jrosbridge.actionlib;

import java.util.Date;

import edu.wpi.rail.jrosbridge.core.Message;

public class Goal {

	private ActionClient actionClient;
	private Message goalMessage;
	private boolean isFinished;
	private String goalID;

	public Goal(ActionClient actionClient, Message goalMessage) {
		// assign the fields
		this.actionClient = actionClient;
		this.goalMessage = goalMessage;
		this.isFinished = false;

		// create a random ID
		Date date = new Date();
		this.goalID = "goal_" + (int) (Math.random() * Integer.MAX_VALUE) + "_"
				+ date.getTime();

		// TODO

		// add the goal to the client
		this.actionClient.addGoal(this);
	}

	public String getGoalID() {
		return this.goalID;
	}
}
