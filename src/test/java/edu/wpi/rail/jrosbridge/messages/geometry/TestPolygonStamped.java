package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;
import edu.wpi.rail.jrosbridge.primitives.Time;

public class TestPolygonStamped {

	private PolygonStamped empty, p1;

	@Before
	public void setUp() {
		empty = new PolygonStamped();
		p1 = new PolygonStamped(new Header(123, new Time(10, 20), "test"),
				new Polygon(new Point32[] { new Point32(0.5f, 1.5f, 3.0f),
						new Point32(-0.5f, -1.5f, -3.0f) }));
	}

	@Test
	public void testConstructor() {
		assertEquals(new Header(), empty.getHeader());
		assertEquals(new Polygon(), empty.getPolygon());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":0,\"stamp\":{\"secs\":0,\"nsecs\":0},\"frame_id\":\"\"}"
						+ ",\"polygon\":{\"points\":[]}}", empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Header(),
				Header.fromJsonObject(empty.toJsonObject().getJsonObject(
						PolygonStamped.FIELD_HEADER)));
		assertEquals(
				new Polygon(),
				Polygon.fromJsonObject(empty.toJsonObject().getJsonObject(
						PolygonStamped.FIELD_POLYGON)));

		assertEquals(PolygonStamped.TYPE, empty.getMessageType());
	}

	@Test
	public void testHeaderAndPolygonConstructor() {
		assertEquals(new Header(123, new Time(10, 20), "test"), p1.getHeader());
		assertEquals(new Polygon(new Point32[] { new Point32(0.5f, 1.5f, 3.0f),
				new Point32(-0.5f, -1.5f, -3.0f) }), p1.getPolygon());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":123,\"stamp\":{\"secs\":10,\"nsecs\":20},\"frame_id\":\"test\"}"
						+ ",\"polygon\":{\"points\":[{\"x\":0.5,\"y\":1.5,\"z\":3.0},{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0}]}}",
				p1.toString());

		assertEquals(2, p1.toJsonObject().size());
		assertEquals(
				new Header(123, new Time(10, 20), "test"),
				Header.fromJsonObject(p1.toJsonObject().getJsonObject(
						PolygonStamped.FIELD_HEADER)));
		assertEquals(
				new Polygon(new Point32[] { new Point32(0.5f, 1.5f, 3.0f),
						new Point32(-0.5f, -1.5f, -3.0f) }),
				Polygon.fromJsonObject(p1.toJsonObject().getJsonObject(
						PolygonStamped.FIELD_POLYGON)));

		assertEquals(PolygonStamped.TYPE, p1.getMessageType());
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
		PolygonStamped clone = p1.clone();
		assertEquals(p1.toString(), clone.toString());
		assertEquals(p1.toJsonObject(), clone.toJsonObject());
		assertEquals(p1.getMessageType(), clone.getMessageType());
		assertEquals(p1.getHeader(), clone.getHeader());
		assertEquals(p1.getPolygon(), clone.getPolygon());
		assertNotSame(p1, clone);
		assertNotSame(p1.toString(), clone.toString());
		assertNotSame(p1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		PolygonStamped p = PolygonStamped.fromJsonString(p1.toString());
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getHeader(), p.getHeader());
		assertEquals(p1.getPolygon(), p.getPolygon());
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(p1.toString());
		PolygonStamped p = PolygonStamped.fromMessage(m);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getHeader(), p.getHeader());
		assertEquals(p1.getPolygon(), p.getPolygon());
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(PolygonStamped.FIELD_HEADER, p1.getHeader().toJsonObject())
				.add(PolygonStamped.FIELD_POLYGON,
						p1.getPolygon().toJsonObject()).build();
		PolygonStamped p = PolygonStamped.fromJsonObject(jsonObject);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getHeader(), p.getHeader());
		assertEquals(p1.getPolygon(), p.getPolygon());
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoHeader() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(PolygonStamped.FIELD_POLYGON,
						p1.getPolygon().toJsonObject()).build();
		PolygonStamped p = PolygonStamped.fromJsonObject(jsonObject);
		assertEquals(new Header(), p.getHeader());
		assertEquals(p1.getPolygon(), p.getPolygon());
	}

	@Test
	public void testFromJsonObjectNoPolygon() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(PolygonStamped.FIELD_HEADER, p1.getHeader().toJsonObject())
				.build();
		PolygonStamped p = PolygonStamped.fromJsonObject(jsonObject);
		assertEquals(p1.getHeader(), p.getHeader());
		assertEquals(new Polygon(), p.getPolygon());
	}
}
