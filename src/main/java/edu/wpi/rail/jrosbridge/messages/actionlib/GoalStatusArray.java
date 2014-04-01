package edu.wpi.rail.jrosbridge.messages.actionlib;

import java.io.StringReader;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;

/**
 * The actionlib_msgs/GoalStatusArray message. Stores the statuses for goals
 * that are currently being tracked by an action server.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class GoalStatusArray extends Message {

	/**
	 * The name of the header field for the message.
	 */
	public static final String FIELD_HEADER = "header";

	/**
	 * The name of the status list field for the message.
	 */
	public static final String FIELD_STATUS_LIST = "status_list";

	/**
	 * The message type.
	 */
	public static final String TYPE = "actionlib_msgs/GoalStatusArray";

	private final Header header;
	private final GoalStatus[] statusList;

	/**
	 * Create a new, empty GoalStatusArray.
	 */
	public GoalStatusArray() {
		this(new Header(), new GoalStatus[] {});
	}

	/**
	 * Create a new GoalStatusArray with the given set of statuses and header.
	 * The array of statuses will be copied into this object.
	 * 
	 * @param header
	 *            The message header.
	 * @param statusList
	 *            The statuses of the status array.
	 */
	public GoalStatusArray(Header header, GoalStatus[] statusList) {
		// build the JSON object
		super(
				Json.createObjectBuilder()
						.add(GoalStatusArray.FIELD_HEADER,
								header.toJsonObject())
						.add(GoalStatusArray.FIELD_STATUS_LIST,
								Json.createReader(
										new StringReader(Arrays
												.deepToString(statusList)))
										.readArray()).build(),
				GoalStatusArray.TYPE);

		this.header = header;
		// copy the status array
		this.statusList = new GoalStatus[statusList.length];
		System.arraycopy(statusList, 0, this.statusList, 0, statusList.length);
	}

	/**
	 * Get the number of statuses in this status array.
	 * 
	 * @return The number of statuses in this status array.
	 */
	public int size() {
		return this.statusList.length;
	}

	/**
	 * Get the status in the status array at the given index.
	 * 
	 * @param index
	 *            The index to get the status of.
	 * @return The status at the given index.
	 */
	public GoalStatus get(int index) {
		return this.statusList[index];
	}

	/**
	 * Get the statuses of this status array. Note that this array should never
	 * be modified directly.
	 * 
	 * @return The statuses of this status array.
	 */
	public GoalStatus[] getStatusList() {
		return this.statusList;
	}

	/**
	 * Get the header value of this status array.
	 * 
	 * @return The statuses header value of this status array.
	 */
	public Header getHeader() {
		return this.header;
	}

	/**
	 * Create a clone of this GoalStatusArray.
	 */
	@Override
	public GoalStatusArray clone() {
		return new GoalStatusArray(this.header, this.statusList);
	}

	/**
	 * Create a new GoalStatusArray based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A GoalStatusArray message based on the given JSON string.
	 */
	public static GoalStatusArray fromJsonString(String jsonString) {
		// convert to a message
		return GoalStatusArray.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new GoalStatusArray based on the given Message. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A GoalStatusArray message based on the given Message.
	 */
	public static GoalStatusArray fromMessage(Message m) {
		// get it from the JSON object
		return GoalStatusArray.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new GoalStatusArray based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A GoalStatusArray message based on the given JSON object.
	 */
	public static GoalStatusArray fromJsonObject(JsonObject jsonObject) {
		// grab the header if there is one
		Header header = jsonObject.containsKey(GoalStatusArray.FIELD_HEADER) ? Header
				.fromJsonObject(jsonObject
						.getJsonObject(GoalStatusArray.FIELD_HEADER))
				: new Header();

		// check the array
		JsonArray jsonPoses = jsonObject
				.getJsonArray(GoalStatusArray.FIELD_STATUS_LIST);
		if (jsonPoses != null) {
			// convert each status
			GoalStatus[] statuses = new GoalStatus[jsonPoses.size()];
			for (int i = 0; i < statuses.length; i++) {
				statuses[i] = GoalStatus.fromJsonObject(jsonPoses
						.getJsonObject(i));
			}
			return new GoalStatusArray(header, statuses);
		} else {
			return new GoalStatusArray(header, new GoalStatus[] {});
		}
	}
}
