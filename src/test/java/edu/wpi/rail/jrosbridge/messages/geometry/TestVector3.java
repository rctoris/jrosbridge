package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestVector3 {

	private Vector3 empty, v1;

	@Before
	public void setUp() {
		empty = new Vector3();
		v1 = new Vector3(0.5, 1.5, 3.0);
	}

	@Test
	public void testConstructor() {
		assertEquals(0.0, empty.getX(), 0);
		assertEquals(0.0, empty.getY(), 0);
		assertEquals(0.0, empty.getZ(), 0);

		assertEquals("{\"x\":0.0,\"y\":0.0,\"z\":0.0}", empty.toString());

		assertEquals(3, empty.toJsonObject().size());
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(Vector3.FIELD_X)
				.doubleValue(), 0);
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(Vector3.FIELD_Y)
				.doubleValue(), 0);
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(Vector3.FIELD_Z)
				.doubleValue(), 0);

		assertEquals(Vector3.TYPE, empty.getMessageType());
	}

	@Test
	public void testDoubleDoubleDoubleConstructor() {
		assertEquals(0.5, v1.getX(), 0);
		assertEquals(1.5, v1.getY(), 0);
		assertEquals(3.0, v1.getZ(), 0);

		assertEquals("{\"x\":0.5,\"y\":1.5,\"z\":3.0}", v1.toString());

		assertEquals(3, v1.toJsonObject().size());
		assertEquals(0.5, v1.toJsonObject().getJsonNumber(Vector3.FIELD_X)
				.doubleValue(), 0);
		assertEquals(1.5, v1.toJsonObject().getJsonNumber(Vector3.FIELD_Y)
				.doubleValue(), 0);
		assertEquals(3.0, v1.toJsonObject().getJsonNumber(Vector3.FIELD_Z)
				.doubleValue(), 0);

		assertEquals(Vector3.TYPE, empty.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(v1.toString().hashCode(), v1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(v1));
		assertFalse(v1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(v1.equals(v1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Vector3 clone = v1.clone();
		assertEquals(v1.toString(), clone.toString());
		assertEquals(v1.toJsonObject(), clone.toJsonObject());
		assertEquals(v1.getMessageType(), clone.getMessageType());
		assertEquals(v1.getX(), clone.getX(), 0);
		assertEquals(v1.getY(), clone.getY(), 0);
		assertEquals(v1.getZ(), clone.getZ(), 0);
		assertNotSame(v1, clone);
		assertNotSame(v1.toString(), clone.toString());
		assertNotSame(v1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		Vector3 p = Vector3.fromJsonString(v1.toString());
		assertEquals(v1.toString(), p.toString());
		assertEquals(v1.toJsonObject(), p.toJsonObject());
		assertEquals(v1.getMessageType(), p.getMessageType());
		assertEquals(v1.getX(), p.getX(), 0);
		assertEquals(v1.getY(), p.getY(), 0);
		assertEquals(v1.getZ(), p.getZ(), 0);
		assertNotSame(v1, p);
		assertNotSame(v1.toString(), p.toString());
		assertNotSame(v1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(v1.toString());
		Vector3 p = Vector3.fromMessage(m);
		assertEquals(v1.toString(), p.toString());
		assertEquals(v1.toJsonObject(), p.toJsonObject());
		assertEquals(v1.getMessageType(), p.getMessageType());
		assertEquals(v1.getX(), p.getX(), 0);
		assertEquals(v1.getY(), p.getY(), 0);
		assertEquals(v1.getZ(), p.getZ(), 0);
		assertNotSame(v1, p);
		assertNotSame(v1.toString(), p.toString());
		assertNotSame(v1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Vector3.FIELD_X, v1.getX())
				.add(Vector3.FIELD_Y, v1.getY())
				.add(Vector3.FIELD_Z, v1.getZ()).build();
		Vector3 p = Vector3.fromJsonObject(jsonObject);
		assertEquals(v1.toString(), p.toString());
		assertEquals(v1.toJsonObject(), p.toJsonObject());
		assertEquals(v1.getMessageType(), p.getMessageType());
		assertEquals(v1.getX(), p.getX(), 0);
		assertEquals(v1.getY(), p.getY(), 0);
		assertEquals(v1.getZ(), p.getZ(), 0);
		assertNotSame(v1, p);
		assertNotSame(v1.toString(), p.toString());
		assertNotSame(v1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoX() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Vector3.FIELD_Y, v1.getY())
				.add(Vector3.FIELD_Z, v1.getZ()).build();
		Vector3 p = Vector3.fromJsonObject(jsonObject);
		assertEquals(0.0, p.getX(), 0);
		assertEquals(v1.getY(), p.getY(), 0);
		assertEquals(v1.getZ(), p.getZ(), 0);
	}

	@Test
	public void testFromJsonObjectNoY() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Vector3.FIELD_X, v1.getX())
				.add(Vector3.FIELD_Z, v1.getZ()).build();
		Vector3 p = Vector3.fromJsonObject(jsonObject);
		assertEquals(v1.getX(), p.getX(), 0);
		assertEquals(0.0, p.getY(), 0);
		assertEquals(v1.getZ(), p.getZ(), 0);
	}

	@Test
	public void testFromJsonObjectNoZ() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Vector3.FIELD_X, v1.getX())
				.add(Vector3.FIELD_Y, v1.getY()).build();
		Vector3 p = Vector3.fromJsonObject(jsonObject);
		assertEquals(v1.getX(), p.getX(), 0);
		assertEquals(v1.getY(), p.getY(), 0);
		assertEquals(0.0, p.getZ(), 0);
	}
}
