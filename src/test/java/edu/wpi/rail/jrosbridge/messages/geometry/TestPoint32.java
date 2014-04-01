package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestPoint32 {

	private Point32 empty, p1;

	@Before
	public void setUp() {
		empty = new Point32();
		p1 = new Point32(0.5f, 1.5f, 3.0f);
	}

	@Test
	public void testConstructor() {
		assertEquals(0.0f, empty.getX(), 0);
		assertEquals(0.0f, empty.getY(), 0);
		assertEquals(0.0f, empty.getZ(), 0);

		assertEquals("{\"x\":0.0,\"y\":0.0,\"z\":0.0}", empty.toString());

		assertEquals(3, empty.toJsonObject().size());
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(Point32.FIELD_X)
				.doubleValue(), 0);
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(Point32.FIELD_Y)
				.doubleValue(), 0);
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(Point32.FIELD_Z)
				.doubleValue(), 0);

		assertEquals(Point32.TYPE, empty.getMessageType());
	}

	@Test
	public void testFloatFloatAndFloatConstructor() {
		assertEquals(0.5f, p1.getX(), 0);
		assertEquals(1.5f, p1.getY(), 0);
		assertEquals(3.0f, p1.getZ(), 0);

		assertEquals("{\"x\":0.5,\"y\":1.5,\"z\":3.0}", p1.toString());

		assertEquals(3, p1.toJsonObject().size());
		assertEquals(0.5, p1.toJsonObject().getJsonNumber(Point32.FIELD_X)
				.doubleValue(), 0);
		assertEquals(1.5, p1.toJsonObject().getJsonNumber(Point32.FIELD_Y)
				.doubleValue(), 0);
		assertEquals(3.0, p1.toJsonObject().getJsonNumber(Point32.FIELD_Z)
				.doubleValue(), 0);

		assertEquals(Point32.TYPE, empty.getMessageType());
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
		Point32 clone = p1.clone();
		assertEquals(p1.toString(), clone.toString());
		assertEquals(p1.toJsonObject(), clone.toJsonObject());
		assertEquals(p1.getMessageType(), clone.getMessageType());
		assertEquals(p1.getX(), clone.getX(), 0);
		assertEquals(p1.getY(), clone.getY(), 0);
		assertEquals(p1.getZ(), clone.getZ(), 0);
		assertNotSame(p1, clone);
		assertNotSame(p1.toString(), clone.toString());
		assertNotSame(p1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		Point32 p = Point32.fromJsonString(p1.toString());
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getX(), p.getX(), 0);
		assertEquals(p1.getY(), p.getY(), 0);
		assertEquals(p1.getZ(), p.getZ(), 0);
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(p1.toString());
		Point32 p = Point32.fromMessage(m);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getX(), p.getX(), 0);
		assertEquals(p1.getY(), p.getY(), 0);
		assertEquals(p1.getZ(), p.getZ(), 0);
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Point32.FIELD_X, p1.getX())
				.add(Point32.FIELD_Y, p1.getY())
				.add(Point32.FIELD_Z, p1.getZ()).build();
		Point32 p = Point32.fromJsonObject(jsonObject);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getX(), p.getX(), 0);
		assertEquals(p1.getY(), p.getY(), 0);
		assertEquals(p1.getZ(), p.getZ(), 0);
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoX() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Point32.FIELD_Y, p1.getY())
				.add(Point32.FIELD_Z, p1.getZ()).build();
		Point32 p = Point32.fromJsonObject(jsonObject);
		assertEquals(0.0f, p.getX(), 0);
		assertEquals(p1.getY(), p.getY(), 0);
		assertEquals(p1.getZ(), p.getZ(), 0);
	}

	@Test
	public void testFromJsonObjectNoY() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Point32.FIELD_X, p1.getX())
				.add(Point32.FIELD_Z, p1.getZ()).build();
		Point32 p = Point32.fromJsonObject(jsonObject);
		assertEquals(p1.getX(), p.getX(), 0);
		assertEquals(0.0f, p.getY(), 0);
		assertEquals(p1.getZ(), p.getZ(), 0);
	}

	@Test
	public void testFromJsonObjectNoZ() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Point32.FIELD_X, p1.getX())
				.add(Point32.FIELD_Y, p1.getY()).build();
		Point32 p = Point32.fromJsonObject(jsonObject);
		assertEquals(p1.getX(), p.getX(), 0);
		assertEquals(p1.getY(), p.getY(), 0);
		assertEquals(0.0f, p.getZ(), 0);
	}
}
