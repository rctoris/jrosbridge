package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestTransform {

	private Transform empty, t1;

	@Before
	public void setUp() {
		empty = new Transform();
		t1 = new Transform(new Vector3(0.5, 1.5, 3.0), new Quaternion(-0.5,
				-1.5, -3.0, -4.5));
	}

	@Test
	public void testConstructor() {
		assertEquals(new Vector3(), empty.getTranslation());
		assertEquals(new Quaternion(), empty.getRotation());

		assertEquals("{\"translation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
				+ "\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0,\"w\":0.0}}",
				empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Vector3(),
				Vector3.fromJsonObject(empty.toJsonObject().getJsonObject(
						Transform.FIELD_TRANSLATION)));
		assertEquals(
				new Quaternion(),
				Quaternion.fromJsonObject(empty.toJsonObject().getJsonObject(
						Transform.FIELD_ROTATION)));

		assertEquals(Transform.TYPE, empty.getMessageType());
	}

	@Test
	public void testVector3AndQuaternionConstructor() {
		assertEquals(new Vector3(0.5, 1.5, 3.0), t1.getTranslation());
		assertEquals(new Quaternion(-0.5, -1.5, -3.0, -4.5), t1.getRotation());

		assertEquals(
				"{\"translation\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
						+ "\"rotation\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0,\"w\":-4.5}}",
				t1.toString());

		assertEquals(2, t1.toJsonObject().size());
		assertEquals(
				new Vector3(0.5, 1.5, 3.0),
				Vector3.fromJsonObject(t1.toJsonObject().getJsonObject(
						Transform.FIELD_TRANSLATION)));
		assertEquals(
				new Quaternion(-0.5, -1.5, -3.0, -4.5),
				Quaternion.fromJsonObject(t1.toJsonObject().getJsonObject(
						Transform.FIELD_ROTATION)));

		assertEquals(Transform.TYPE, t1.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(t1.toString().hashCode(), t1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(t1));
		assertFalse(t1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(t1.equals(t1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Transform clone = t1.clone();
		assertEquals(t1.toString(), clone.toString());
		assertEquals(t1.toJsonObject(), clone.toJsonObject());
		assertEquals(t1.getMessageType(), clone.getMessageType());
		assertEquals(t1.getTranslation(), clone.getTranslation());
		assertEquals(t1.getRotation(), clone.getRotation());
		assertNotSame(t1, clone);
		assertNotSame(t1.toString(), clone.toString());
		assertNotSame(t1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		Transform p = Transform.fromJsonString(t1.toString());
		assertEquals(t1.toString(), p.toString());
		assertEquals(t1.toJsonObject(), p.toJsonObject());
		assertEquals(t1.getMessageType(), p.getMessageType());
		assertEquals(t1.getTranslation(), p.getTranslation());
		assertEquals(t1.getRotation(), p.getRotation());
		assertNotSame(t1, p);
		assertNotSame(t1.toString(), p.toString());
		assertNotSame(t1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(t1.toString());
		Transform p = Transform.fromMessage(m);
		assertEquals(t1.toString(), p.toString());
		assertEquals(t1.toJsonObject(), p.toJsonObject());
		assertEquals(t1.getMessageType(), p.getMessageType());
		assertEquals(t1.getTranslation(), p.getTranslation());
		assertEquals(t1.getRotation(), p.getRotation());
		assertNotSame(t1, p);
		assertNotSame(t1.toString(), p.toString());
		assertNotSame(t1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(Transform.FIELD_TRANSLATION,
						t1.getTranslation().toJsonObject())
				.add(Transform.FIELD_ROTATION, t1.getRotation().toJsonObject())
				.build();
		Transform p = Transform.fromJsonObject(jsonObject);
		assertEquals(t1.toString(), p.toString());
		assertEquals(t1.toJsonObject(), p.toJsonObject());
		assertEquals(t1.getMessageType(), p.getMessageType());
		assertEquals(t1.getTranslation(), p.getTranslation());
		assertEquals(t1.getRotation(), p.getRotation());
		assertNotSame(t1, p);
		assertNotSame(t1.toString(), p.toString());
		assertNotSame(t1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoTranslation() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Transform.FIELD_ROTATION, t1.getRotation().toJsonObject())
				.build();
		Transform p = Transform.fromJsonObject(jsonObject);
		assertEquals(new Point(), p.getTranslation());
		assertEquals(t1.getRotation(), p.getRotation());
	}

	@Test
	public void testFromJsonObjectNoRotation() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(Transform.FIELD_TRANSLATION,
						t1.getTranslation().toJsonObject()).build();
		Transform p = Transform.fromJsonObject(jsonObject);
		assertEquals(t1.getTranslation(), p.getTranslation());
		assertEquals(new Quaternion(), p.getRotation());
	}
}
