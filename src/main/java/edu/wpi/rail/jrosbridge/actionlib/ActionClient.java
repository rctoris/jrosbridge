package edu.wpi.rail.jrosbridge.actionlib;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.json.JsonArray;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.actionlib.handler.TimeoutHandler;
import edu.wpi.rail.jrosbridge.core.Message;
import edu.wpi.rail.jrosbridge.core.Ros;
import edu.wpi.rail.jrosbridge.core.Topic;
import edu.wpi.rail.jrosbridge.core.callback.TopicCallback;

public class ActionClient {

	private static final String ACTIONLIB_MSGS_GOAL_STATUS_ARRAY = "actionlib_msgs/GoalStatusArray";

	private static final String ACTIONLIB_MSGS_GOAL_ID = "actionlib_msgs/GoalID";

	private static final String FEEDBACK_MSG = "Feedback";
	private static final String RESULT_MSG = "Result";
	private static final String GOAL_MSG = "Goal";

	private static final String FEEDBACK_TOPIC = "/feedback";
	private static final String STATUS_TOPIC = "/status";
	private static final String RESULT_TOPIC = "/result";
	private static final String GOAL_TOPIC = "/goal";
	private static final String CANCEL_TOPIC = "/cancel";

	private static final String FIELD_STATUS_LIST = "status_list";
	private static final String FIELD_GOAL_ID = "goal_id";
	private static final String FIELD_ID = "id";

	private Ros ros;
	private String serverName;
	private String actionName;
	private int timeout;
	private TimeoutHandler handler;

	// associated goals for this client
	private HashMap<String, Goal> goals;

	// flag to check if a status has been received
	private boolean receivedStatus;

	// actionlib associated topics
	private Topic feedbackListener;
	private Topic statusListener;
	private Topic resultListener;
	private Topic goalTopic;
	private Topic cancelTopic;

	public ActionClient(Ros ros, String serverName, String actionName) {
		this(ros, serverName, actionName, 0, null);
	}

	public ActionClient(Ros ros, String serverName, String actionName,
			int timeout, TimeoutHandler handler) {
		// copy fields
		this.ros = ros;
		this.serverName = serverName;
		this.actionName = actionName;
		this.timeout = timeout;
		this.handler = handler;

		// empty goal list
		this.goals = new HashMap<String, Goal>();
		// flag used to check for incoming status during timeout
		this.receivedStatus = false;

		// create the topics associated with actionlib
		this.feedbackListener = new Topic(this.ros, this.serverName
				+ ActionClient.FEEDBACK_TOPIC, this.actionName
				+ ActionClient.FEEDBACK_MSG);
		this.statusListener = new Topic(this.ros, this.serverName
				+ ActionClient.STATUS_TOPIC,
				ActionClient.ACTIONLIB_MSGS_GOAL_STATUS_ARRAY);
		this.resultListener = new Topic(this.ros, this.serverName
				+ ActionClient.RESULT_TOPIC, this.actionName
				+ ActionClient.RESULT_MSG);
		this.goalTopic = new Topic(this.ros, this.serverName
				+ ActionClient.GOAL_TOPIC, this.actionName
				+ ActionClient.GOAL_MSG);
		this.cancelTopic = new Topic(this.ros, this.serverName
				+ ActionClient.CANCEL_TOPIC,
				ActionClient.ACTIONLIB_MSGS_GOAL_ID);

		// advertise the goal and cancel topics
		this.goalTopic.advertise();
		this.cancelTopic.advertise();

		// subscribe to the status topic
		this.statusListener.subscribe(new TopicCallback() {
			@Override
			public void handleMessage(Message message) {
				// set the flag to end the timeout
				ActionClient.this.receivedStatus = true;

				// parse the JSON
				JsonObject json = message.toJsonObject();

				// array of statuses
				JsonArray statusList = json
						.getJsonArray(ActionClient.FIELD_STATUS_LIST);

				// check each status
				for (int i = 0; i < statusList.size(); i++) {
					JsonObject status = statusList.getJsonObject(i);
					// check for the goal
					String id = status
							.getJsonObject(ActionClient.FIELD_GOAL_ID)
							.getString(ActionClient.FIELD_ID);
					if (ActionClient.this.goals.containsKey(id)) {
						// TODO
					}
				}
			}
		});

		this.feedbackListener.subscribe(new TopicCallback() {
			@Override
			public void handleMessage(Message message) {
				// TODO
			}
		});

		// subscribe to the result topic
		this.resultListener.subscribe(new TopicCallback() {
			@Override
			public void handleMessage(Message message) {
				// TODO
			}
		});

		// register the timeout if there is one
		if (this.timeout > 0) {
			Timer t = new Timer();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					// check for a status
					if (!ActionClient.this.receivedStatus) {
						ActionClient.this.handler.handleTimeout();
					}
				}
			}, this.timeout);
		}
	}

	/**
	 * Get the ROS connection handle for this topic.
	 * 
	 * @return The ROS connection handle for this topic.
	 */
	public Ros getRos() {
		return this.ros;
	}

	public String getServerName() {
		return this.serverName;
	}

	public String getActionName() {
		return this.actionName;
	}

	public int getTimeout() {
		return this.timeout;
	}

	public TimeoutHandler getHandler() {
		return this.handler;
	}
	
	void addGoal(Goal g) {
		this.goals.put(g.getGoalID(), g);
	}

	/**
	 * Cancel all goals associated with this ActionClient.
	 */
	public void cancel() {
		// send it an empty message
		Message cancelMessage = new Message();
		this.cancelTopic.publish(cancelMessage);
	}
}
