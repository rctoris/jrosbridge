package edu.wpi.rail.jrosbridge.messages.geometry;

import java.io.StringReader;
import java.util.Arrays;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The geometry_msgs/Polygon message. A specification of a polygon where the
 * first and last polygons are assumed to be connected.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 25, 2014
 */
public class Polygon extends Message {

	/**
	 * The name of the points field for the message.
	 */
	public static final String FIELD_POINTS = "points";

	/**
	 * The message type.
	 */
	public static final String TYPE = "geometry_msgs/Polygon";

	private Point32[] points;

	/**
	 * Create a new Polygon with no points.
	 */
	public Polygon() {
		this(new Point32[0]);
	}

	/**
	 * Create a new Polygon with the given set of points. The array of points
	 * will be copied into this object.
	 * 
	 * @param points
	 *            The points of the polygon.
	 */
	public Polygon(Point32[] points) {
		// build the JSON object
		super(Json
				.createObjectBuilder()
				.add(Polygon.FIELD_POINTS,
						Json.createReader(
								new StringReader(Arrays.deepToString(points)))
								.readArray()).build(), Polygon.TYPE);

		// copy the points
		this.points = new Point32[points.length];
		System.arraycopy(points, 0, this.points, 0, points.length);
	}

	/**
	 * Get the number of points in this polygon.
	 * 
	 * @return The number of points in this polygon.
	 */
	public int size() {
		return this.points.length;
	}

	/**
	 * Get the point in the polygon at the given index.
	 * 
	 * @param index
	 *            The index to get the point of.
	 * @return The point at the given index.
	 */
	public Point32 get(int index) {
		return this.points[index];
	}

	/**
	 * Get the points of this polygon. Note that this array should never be
	 * modified directly.
	 * 
	 * @return The x value of this polygon.
	 */
	public Point32[] getPoints() {
		return this.points;
	}

	/**
	 * Create a deep clone of this Polygon.
	 */
	@Override
	public Polygon clone() {
		return new Polygon(this.points);
	}
}
