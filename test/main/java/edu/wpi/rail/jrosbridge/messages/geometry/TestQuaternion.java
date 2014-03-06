package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestQuaternion {

	private Quaternion empty, q1;

	@Before
	public void setUp() {
		empty = new Quaternion();
		q1 = new Quaternion(0.5, 1.5, 3.0, 4.5);
	}

	@Test
	public void testConstructor() {
		assertEquals(0.0, empty.getX());
		assertEquals(0.0, empty.getY());
		assertEquals(0.0, empty.getZ());
		assertEquals(0.0, empty.getW());

		assertEquals("{\"x\":0.0,\"y\":0.0,\"z\":0.0,\"w\":0.0}",
				empty.toString());

		assertEquals(4, empty.toJsonObject().size());
		assertEquals(0.0, empty.toJsonObject()
				.getJsonNumber(Quaternion.FIELD_X).doubleValue());
		assertEquals(0.0, empty.toJsonObject()
				.getJsonNumber(Quaternion.FIELD_Y).doubleValue());
		assertEquals(0.0, empty.toJsonObject()
				.getJsonNumber(Quaternion.FIELD_Z).doubleValue());
		assertEquals(0.0, empty.toJsonObject()
				.getJsonNumber(Quaternion.FIELD_W).doubleValue());

		assertEquals(Quaternion.TYPE, empty.getMessageType());
	}

	@Test
	public void testDoubleDoubleDoubleAndDoubleConstructor() {
		assertEquals(0.5, q1.getX());
		assertEquals(1.5, q1.getY());
		assertEquals(3.0, q1.getZ());
		assertEquals(4.5, q1.getW());

		assertEquals("{\"x\":0.5,\"y\":1.5,\"z\":3.0,\"w\":4.5}", q1.toString());

		assertEquals(4, q1.toJsonObject().size());
		assertEquals(0.5, q1.toJsonObject().getJsonNumber(Quaternion.FIELD_X)
				.doubleValue());
		assertEquals(1.5, q1.toJsonObject().getJsonNumber(Quaternion.FIELD_Y)
				.doubleValue());
		assertEquals(3.0, q1.toJsonObject().getJsonNumber(Quaternion.FIELD_Z)
				.doubleValue());
		assertEquals(4.5, q1.toJsonObject().getJsonNumber(Quaternion.FIELD_W)
				.doubleValue());

		assertEquals(Quaternion.TYPE, empty.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(q1.toString().hashCode(), q1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(q1));
		assertFalse(q1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(q1.equals(q1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Quaternion clone = q1.clone();
		assertEquals(q1.toString(), clone.toString());
		assertEquals(q1.toJsonObject(), clone.toJsonObject());
		assertEquals(q1.getMessageType(), clone.getMessageType());
		assertEquals(q1.getX(), clone.getX());
		assertEquals(q1.getY(), clone.getY());
		assertEquals(q1.getZ(), clone.getZ());
		assertEquals(q1.getW(), clone.getW());
		assertNotSame(q1, clone);
		assertNotSame(q1.toString(), clone.toString());
		assertNotSame(q1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		Quaternion p = Quaternion.fromJsonString(q1.toString());
		assertEquals(q1.toString(), p.toString());
		assertEquals(q1.toJsonObject(), p.toJsonObject());
		assertEquals(q1.getMessageType(), p.getMessageType());
		assertEquals(q1.getX(), p.getX());
		assertEquals(q1.getY(), p.getY());
		assertEquals(q1.getZ(), p.getZ());
		assertNotSame(q1, p);
		assertNotSame(q1.toString(), p.toString());
		assertNotSame(q1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(q1.toString());
		Quaternion p = Quaternion.fromMessage(m);
		assertEquals(q1.toString(), p.toString());
		assertEquals(q1.toJsonObject(), p.toJsonObject());
		assertEquals(q1.getMessageType(), p.getMessageType());
		assertEquals(q1.getX(), p.getX());
		assertEquals(q1.getY(), p.getY());
		assertEquals(q1.getZ(), p.getZ());
		assertEquals(q1.getW(), p.getW());
		assertNotSame(q1, p);
		assertNotSame(q1.toString(), p.toString());
		assertNotSame(q1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Quaternion.FIELD_X, q1.getX())
				.add(Quaternion.FIELD_Y, q1.getY())
				.add(Quaternion.FIELD_Z, q1.getZ())
				.add(Quaternion.FIELD_W, q1.getW()).build();
		Quaternion p = Quaternion.fromJsonObject(jsonObject);
		assertEquals(q1.toString(), p.toString());
		assertEquals(q1.toJsonObject(), p.toJsonObject());
		assertEquals(q1.getMessageType(), p.getMessageType());
		assertEquals(q1.getX(), p.getX());
		assertEquals(q1.getY(), p.getY());
		assertEquals(q1.getZ(), p.getZ());
		assertEquals(q1.getW(), p.getW());
		assertNotSame(q1, p);
		assertNotSame(q1.toString(), p.toString());
		assertNotSame(q1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoX() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Quaternion.FIELD_Y, q1.getY())
				.add(Quaternion.FIELD_Z, q1.getZ())
				.add(Quaternion.FIELD_W, q1.getW()).build();
		Quaternion p = Quaternion.fromJsonObject(jsonObject);
		assertEquals(0.0, p.getX());
		assertEquals(q1.getY(), p.getY());
		assertEquals(q1.getZ(), p.getZ());
		assertEquals(q1.getW(), p.getW());
	}

	@Test
	public void testFromJsonObjectNoY() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Quaternion.FIELD_X, q1.getX())
				.add(Quaternion.FIELD_Z, q1.getZ())
				.add(Quaternion.FIELD_W, q1.getW()).build();
		Quaternion p = Quaternion.fromJsonObject(jsonObject);
		assertEquals(q1.getX(), p.getX());
		assertEquals(0.0, p.getY());
		assertEquals(q1.getZ(), p.getZ());
		assertEquals(q1.getW(), p.getW());
	}

	@Test
	public void testFromJsonObjectNoZ() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Quaternion.FIELD_X, q1.getX())
				.add(Quaternion.FIELD_Y, q1.getY())
				.add(Quaternion.FIELD_W, q1.getW()).build();
		Quaternion p = Quaternion.fromJsonObject(jsonObject);
		assertEquals(q1.getX(), p.getX());
		assertEquals(q1.getY(), p.getY());
		assertEquals(0.0, p.getZ());
		assertEquals(q1.getW(), p.getW());
	}

	@Test
	public void testFromJsonObjectNoW() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Quaternion.FIELD_X, q1.getX())
				.add(Quaternion.FIELD_Y, q1.getY())
				.add(Quaternion.FIELD_Z, q1.getZ()).build();
		Quaternion p = Quaternion.fromJsonObject(jsonObject);
		assertEquals(q1.getX(), p.getX());
		assertEquals(q1.getY(), p.getY());
		assertEquals(q1.getZ(), p.getZ());
		assertEquals(0.0, p.getW());
	}
}
