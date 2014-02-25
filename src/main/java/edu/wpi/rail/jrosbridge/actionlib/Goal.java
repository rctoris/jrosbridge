package edu.wpi.rail.jrosbridge.actionlib;

import java.util.Date;

import edu.wpi.rail.jrosbridge.messages.Message;

public class Goal {

	private ActionClient actionClient;
	private Message goalMessage;
	private boolean isFinished;
	private String goalID;
	
	private ArrayList<GoalHandler>

	public Goal(ActionClient actionClient, Message goal) {
		// assign the fields
		this.actionClient = actionClient;
		this.goalMessage = goalMessage;
		this.isFinished = false;

		// create a random ID
		Date date = new Date();
		this.goalID = "goal_" + (int) (Math.random() * Integer.MAX_VALUE) + "_"
				+ date.getTime();

		// create the goal message
		this.goalMessage = new GoalMessage(this.goalID, goal);

		  this.on('status', function(status) {
		    that.status = status;
		  });

		  this.on('result', function(result) {
		    that.isFinished = true;
		    that.result = result;
		  });

		  this.on('feedback', function(feedback) {
		    that.feedback = feedback;
		  });

		// add the goal to the client
		this.actionClient.addGoal(this);
	}

	public String getGoalID() {
		return this.goalID;
	}
}
