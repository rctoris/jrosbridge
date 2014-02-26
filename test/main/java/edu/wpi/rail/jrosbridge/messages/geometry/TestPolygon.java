package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestPolygon {

	private Polygon empty, p1;

	@Before
	public void setUp() {
		empty = new Polygon();
		p1 = new Polygon(new Point32[] { new Point32(0.5f, 1.5f, 3.0f),
				new Point32(-0.5f, -1.5f, -3.0f) });
	}

	@Test
	public void testConstructor() {
		assertEquals(new Point32[0], empty.getPoints());
		assertEquals(0, empty.size());

		assertEquals("{\"points\":[]}", empty.toString());

		assertEquals(1, empty.toJsonObject().size());
		assertEquals(0, empty.toJsonObject().getJsonArray(Polygon.FIELD_POINTS)
				.size());

		assertEquals(Polygon.TYPE, empty.getMessageType());
	}

	@Test
	public void testPoint32ArrayConstructor() {
		assertEquals(new Point32[] { new Point32(0.5f, 1.5f, 3.0f),
				new Point32(-0.5f, -1.5f, -3.0f) }, p1.getPoints());
		assertEquals(2, p1.size());
		assertEquals(new Point32(0.5f, 1.5f, 3.0f), p1.get(0));
		assertEquals(new Point32(-0.5f, -1.5f, -3.0f), p1.get(1));

		assertEquals(
				"{\"points\":[{\"x\":0.5,\"y\":1.5,\"z\":3.0},{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0}]}",
				p1.toString());

		assertEquals(1, p1.toJsonObject().size());
		assertEquals(2, p1.toJsonObject().getJsonArray(Polygon.FIELD_POINTS)
				.size());
		assertEquals(3, p1.toJsonObject().getJsonArray(Polygon.FIELD_POINTS)
				.getJsonObject(0).size());
		assertEquals(0.5, p1.toJsonObject().getJsonArray(Polygon.FIELD_POINTS)
				.getJsonObject(0).getJsonNumber(Point32.FIELD_X).doubleValue());
		assertEquals(1.5, p1.toJsonObject().getJsonArray(Polygon.FIELD_POINTS)
				.getJsonObject(0).getJsonNumber(Point32.FIELD_Y).doubleValue());
		assertEquals(3.0, p1.toJsonObject().getJsonArray(Polygon.FIELD_POINTS)
				.getJsonObject(0).getJsonNumber(Point32.FIELD_Z).doubleValue());
		assertEquals(3, p1.toJsonObject().getJsonArray(Polygon.FIELD_POINTS)
				.getJsonObject(1).size());
		assertEquals(-0.5, p1.toJsonObject().getJsonArray(Polygon.FIELD_POINTS)
				.getJsonObject(1).getJsonNumber(Point32.FIELD_X).doubleValue());
		assertEquals(-1.5, p1.toJsonObject().getJsonArray(Polygon.FIELD_POINTS)
				.getJsonObject(1).getJsonNumber(Point32.FIELD_Y).doubleValue());
		assertEquals(-3.0, p1.toJsonObject().getJsonArray(Polygon.FIELD_POINTS)
				.getJsonObject(1).getJsonNumber(Point32.FIELD_Z).doubleValue());

		assertEquals(Polygon.TYPE, p1.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(p1.toString().hashCode(), p1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(p1));
		assertFalse(p1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(p1.equals(p1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Polygon clone = p1.clone();
		assertEquals(p1.toString(), clone.toString());
		assertEquals(p1.toJsonObject(), clone.toJsonObject());
		assertEquals(p1.getMessageType(), clone.getMessageType());
		assertEquals(p1.getPoints(), clone.getPoints());
		assertEquals(p1.size(), clone.size());
		assertEquals(p1.get(0), clone.get(0));
		assertEquals(p1.get(1), clone.get(1));
		assertNotSame(p1, clone);
		assertNotSame(p1.toString(), clone.toString());
		assertNotSame(p1.toJsonObject(), clone.toJsonObject());
		assertNotSame(p1.getPoints(), clone.getPoints());
	}
}
