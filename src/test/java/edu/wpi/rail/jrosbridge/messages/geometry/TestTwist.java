package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestTwist {

	private Twist empty, t1;

	@Before
	public void setUp() {
		empty = new Twist();
		t1 = new Twist(new Vector3(0.5, 1.5, 3.0),
				new Vector3(-0.5, -1.5, -3.0));
	}

	@Test
	public void testConstructor() {
		assertEquals(new Vector3(), empty.getLinear());
		assertEquals(new Vector3(), empty.getAngular());

		assertEquals("{\"linear\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
				+ "\"angular\":{\"x\":0.0,\"y\":0.0,\"z\":0.0}}",
				empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Vector3(),
				Vector3.fromJsonObject(empty.toJsonObject().getJsonObject(
						Twist.FIELD_LINEAR)));
		assertEquals(
				new Vector3(),
				Vector3.fromJsonObject(empty.toJsonObject().getJsonObject(
						Twist.FIELD_ANGULAR)));

		assertEquals(Twist.TYPE, empty.getMessageType());
	}

	@Test
	public void testVector3AndVector3Constructor() {
		assertEquals(new Vector3(0.5, 1.5, 3.0), t1.getLinear());
		assertEquals(new Vector3(-0.5, -1.5, -3.0), t1.getAngular());

		assertEquals("{\"linear\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
				+ "\"angular\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0}}",
				t1.toString());

		assertEquals(2, t1.toJsonObject().size());
		assertEquals(
				new Vector3(0.5, 1.5, 3.0),
				Vector3.fromJsonObject(t1.toJsonObject().getJsonObject(
						Twist.FIELD_LINEAR)));
		assertEquals(
				new Vector3(-0.5, -1.5, -3.0),
				Vector3.fromJsonObject(t1.toJsonObject().getJsonObject(
						Twist.FIELD_ANGULAR)));

		assertEquals(Twist.TYPE, t1.getMessageType());
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
		Twist clone = t1.clone();
		assertEquals(t1.toString(), clone.toString());
		assertEquals(t1.toJsonObject(), clone.toJsonObject());
		assertEquals(t1.getMessageType(), clone.getMessageType());
		assertEquals(t1.getLinear(), clone.getLinear());
		assertEquals(t1.getAngular(), clone.getAngular());
		assertNotSame(t1, clone);
		assertNotSame(t1.toString(), clone.toString());
		assertNotSame(t1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		Twist p = Twist.fromJsonString(t1.toString());
		assertEquals(t1.toString(), p.toString());
		assertEquals(t1.toJsonObject(), p.toJsonObject());
		assertEquals(t1.getMessageType(), p.getMessageType());
		assertEquals(t1.getLinear(), p.getLinear());
		assertEquals(t1.getAngular(), p.getAngular());
		assertNotSame(t1, p);
		assertNotSame(t1.toString(), p.toString());
		assertNotSame(t1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(t1.toString());
		Twist p = Twist.fromMessage(m);
		assertEquals(t1.toString(), p.toString());
		assertEquals(t1.toJsonObject(), p.toJsonObject());
		assertEquals(t1.getMessageType(), p.getMessageType());
		assertEquals(t1.getLinear(), p.getLinear());
		assertEquals(t1.getAngular(), p.getAngular());
		assertNotSame(t1, p);
		assertNotSame(t1.toString(), p.toString());
		assertNotSame(t1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Twist.FIELD_LINEAR, t1.getLinear().toJsonObject())
				.add(Twist.FIELD_ANGULAR, t1.getAngular().toJsonObject())
				.build();
		Twist p = Twist.fromJsonObject(jsonObject);
		assertEquals(t1.toString(), p.toString());
		assertEquals(t1.toJsonObject(), p.toJsonObject());
		assertEquals(t1.getMessageType(), p.getMessageType());
		assertEquals(t1.getLinear(), p.getLinear());
		assertEquals(t1.getAngular(), p.getAngular());
		assertNotSame(t1, p);
		assertNotSame(t1.toString(), p.toString());
		assertNotSame(t1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoLinear() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Twist.FIELD_ANGULAR, t1.getAngular().toJsonObject())
				.build();
		Twist p = Twist.fromJsonObject(jsonObject);
		assertEquals(new Vector3(), p.getLinear());
		assertEquals(t1.getAngular(), p.getAngular());
	}

	@Test
	public void testFromJsonObjectNoAngular() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Twist.FIELD_LINEAR, t1.getLinear().toJsonObject()).build();
		Twist p = Twist.fromJsonObject(jsonObject);
		assertEquals(t1.getLinear(), p.getLinear());
		assertEquals(new Vector3(), p.getAngular());
	}
}
