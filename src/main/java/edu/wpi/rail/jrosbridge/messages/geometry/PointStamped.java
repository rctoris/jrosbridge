package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;

/**
 * The geometry_msgs/PointStamped message. This represents a Point with
 * reference coordinate frame and timestamp.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class PointStamped extends Message {

	/**
	 * The name of the header field for the message.
	 */
	public static final String FIELD_HEADER = "header";

	/**
	 * The name of the point field for the message.
	 */
	public static final String FIELD_POINT = "point";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/PointStamped";

	private final Header header;
	private final Point point;

	/**
	 * Create a new PointStamped with all 0s.
	 */
	public PointStamped() {
		this(new Header(), new Point());
	}

	/**
	 * Create a new PointStamped with the given values.
	 * 
	 * @param header
	 *            The header value of the point.
	 * @param point
	 *            The point value of the point.
	 */
	public PointStamped(Header header, Point point) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(PointStamped.FIELD_HEADER, header.toJsonObject())
				.add(PointStamped.FIELD_POINT, point.toJsonObject()).build(),
				PointStamped.TYPE);
		this.header = header;
		this.point = point;
	}

	/**
	 * Get the header value of this point.
	 * 
	 * @return The header value of this point.
	 */
	public Header getHeader() {
		return this.header;
	}

	/**
	 * Get the point value of this point.
	 * 
	 * @return The point value of this point.
	 */
	public Point getPoint() {
		return this.point;
	}

	/**
	 * Create a clone of this PointStamped.
	 */
	@Override
	public PointStamped clone() {
		return new PointStamped(this.header, this.point);
	}

	/**
	 * Create a new PointStamped based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A PointStamped message based on the given JSON string.
	 */
	public static PointStamped fromJsonString(String jsonString) {
		// convert to a message
		return PointStamped.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new PointStamped based on the given Message. Any missing values
	 * will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A PointStamped message based on the given Message.
	 */
	public static PointStamped fromMessage(Message m) {
		// get it from the JSON object
		return PointStamped.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new PointStamped based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A PointStamped message based on the given JSON object.
	 */
	public static PointStamped fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Header header = jsonObject.containsKey(PointStamped.FIELD_HEADER) ? Header
				.fromJsonObject(jsonObject
						.getJsonObject(PointStamped.FIELD_HEADER))
				: new Header();
		Point point = jsonObject.containsKey(PointStamped.FIELD_POINT) ? Point
				.fromJsonObject(jsonObject
						.getJsonObject(PointStamped.FIELD_POINT)) : new Point();
		return new PointStamped(header, point);
	}
}
