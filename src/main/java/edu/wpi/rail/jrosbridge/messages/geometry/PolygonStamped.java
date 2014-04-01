package edu.wpi.rail.jrosbridge.messages.geometry;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;

/**
 * The geometry_msgs/PolygonStamped message. This represents a Polygon with
 * reference coordinate frame and timestamp.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class PolygonStamped extends Message {

	/**
	 * The name of the header field for the message.
	 */
	public static final String FIELD_HEADER = "header";

	/**
	 * The name of the polygon field for the message.
	 */
	public static final String FIELD_POLYGON = "polygon";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/PolygonStamped";

	private final Header header;
	private final Polygon polygon;

	/**
	 * Create a new PolygonStamped with all 0s.
	 */
	public PolygonStamped() {
		this(new Header(), new Polygon());
	}

	/**
	 * Create a new PolygonStamped with the given values.
	 * 
	 * @param header
	 *            The header value of the polygon.
	 * @param polygon
	 *            The polygon value of the polygon.
	 */
	public PolygonStamped(Header header, Polygon polygon) {
		// build the JSON object
		super(Json.createObjectBuilder()
				.add(PolygonStamped.FIELD_HEADER, header.toJsonObject())
				.add(PolygonStamped.FIELD_POLYGON, polygon.toJsonObject())
				.build(), PolygonStamped.TYPE);
		this.header = header;
		this.polygon = polygon;
	}

	/**
	 * Get the header value of this polygon.
	 * 
	 * @return The header value of this polygon.
	 */
	public Header getHeader() {
		return this.header;
	}

	/**
	 * Get the polygon value of this polygon.
	 * 
	 * @return The polygon value of this polygon.
	 */
	public Polygon getPolygon() {
		return this.polygon;
	}

	/**
	 * Create a clone of this PolygonStamped.
	 */
	@Override
	public PolygonStamped clone() {
		return new PolygonStamped(this.header, this.polygon);
	}

	/**
	 * Create a new PolygonStamped based on the given JSON string. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A PolygonStamped message based on the given JSON string.
	 */
	public static PolygonStamped fromJsonString(String jsonString) {
		// convert to a message
		return PolygonStamped.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new PolygonStamped based on the given Message. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A PolygonStamped message based on the given Message.
	 */
	public static PolygonStamped fromMessage(Message m) {
		// get it from the JSON object
		return PolygonStamped.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new PolygonStamped based on the given JSON object. Any missing
	 * values will be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A PolygonStamped message based on the given JSON object.
	 */
	public static PolygonStamped fromJsonObject(JsonObject jsonObject) {
		// check the fields
		Header header = jsonObject.containsKey(PolygonStamped.FIELD_HEADER) ? Header
				.fromJsonObject(jsonObject
						.getJsonObject(PolygonStamped.FIELD_HEADER))
				: new Header();
		Polygon polygon = jsonObject.containsKey(PolygonStamped.FIELD_POLYGON) ? Polygon
				.fromJsonObject(jsonObject
						.getJsonObject(PolygonStamped.FIELD_POLYGON))
				: new Polygon();
		return new PolygonStamped(header, polygon);
	}
}
