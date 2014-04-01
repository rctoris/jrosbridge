package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;

/**
 * The geometry_msgs/WrenchStamped message. A wrench with reference coordinate
 * frame and timestamp.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class WrenchStamped extends Message {

	/**
	 * The name of the header field for the message.
	 */
	public static final String FIELD_HEADER = "header";

	/**
	 * The name of the wrench field for the message.
	 */
	public static final String FIELD_WRENCH = "wrench";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/WrenchStamped";

	private final Header header;
	private final Wrench wrench;

	/**
	 * Create a new WrenchStamped with all 0s.
	 */
	public WrenchStamped() {
		this(new Header(), new Wrench());
	}

	/**
	 * Create a new WrenchStamped with the given values.
	 * 
	 * @param header
	 *            The header value of the wrench.
	 * @param wrench
	 *            The wrench value of the wrench.
	 */
	public WrenchStamped(Header header, Wrench wrench) {
		// build the JSON object
		super(
				Json.createObjectBuilder()
						.add(WrenchStamped.FIELD_HEADER, header.toJsonObject())
						.add(WrenchStamped.FIELD_WRENCH, wrench.toJsonObject())
						.build(), WrenchStamped.TYPE);
		this.header = header;
		this.wrench = wrench;
	}

	/**
	 * Get the header value of this wrench.
	 * 
	 * @return The header value of this wrench.
	 */
	public Header getHeader() {
		return this.header;
	}

	/**
	 * Get the wrench value of this wrench.
	 * 
	 * @return The wrench value of this wrench.
	 */
	public Wrench getWrench() {
		return this.wrench;
	}

	/**
	 * Create a clone of this WrenchStamped.
	 */
	@Override
	public WrenchStamped clone() {
		return new WrenchStamped(this.header, this.wrench);
	}

	/**
	 * Create a new WrenchStamped based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A WrenchStamped message based on the given JSON string.
	 */
	public static WrenchStamped fromJsonString(String jsonString) {
		// convert to a message
		return WrenchStamped.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new WrenchStamped based on the given Message. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A WrenchStamped message based on the given Message.
	 */
	public static WrenchStamped fromMessage(Message m) {
		// get it from the JSON object
		return WrenchStamped.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new WrenchStamped based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A WrenchStamped message based on the given JSON object.
	 */
	public static WrenchStamped fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Header header = jsonObject.containsKey(WrenchStamped.FIELD_HEADER) ? Header
				.fromJsonObject(jsonObject
						.getJsonObject(WrenchStamped.FIELD_HEADER))
				: new Header();
		Wrench wrench = jsonObject.containsKey(WrenchStamped.FIELD_WRENCH) ? Wrench
				.fromJsonObject(jsonObject
						.getJsonObject(WrenchStamped.FIELD_WRENCH))
				: new Wrench();
		return new WrenchStamped(header, wrench);
	}
}
