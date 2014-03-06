package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestWrench {

	private Wrench empty, w1;

	@Before
	public void setUp() {
		empty = new Wrench();
		w1 = new Wrench(new Vector3(0.5, 1.5, 3.0), new Vector3(-0.5, -1.5,
				-3.0));
	}

	@Test
	public void testConstructor() {
		assertEquals(new Vector3(), empty.getForce());
		assertEquals(new Vector3(), empty.getTorque());

		assertEquals("{\"force\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
				+ "\"torque\":{\"x\":0.0,\"y\":0.0,\"z\":0.0}}",
				empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Vector3(),
				Vector3.fromJsonObject(empty.toJsonObject().getJsonObject(
						Wrench.FIELD_FORCE)));
		assertEquals(
				new Vector3(),
				Vector3.fromJsonObject(empty.toJsonObject().getJsonObject(
						Wrench.FIELD_TORQUE)));

		assertEquals(Wrench.TYPE, empty.getMessageType());
	}

	@Test
	public void testVector3AndVector3Constructor() {
		assertEquals(new Vector3(0.5, 1.5, 3.0), w1.getForce());
		assertEquals(new Vector3(-0.5, -1.5, -3.0), w1.getTorque());

		assertEquals("{\"force\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
				+ "\"torque\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0}}",
				w1.toString());

		assertEquals(2, w1.toJsonObject().size());
		assertEquals(
				new Vector3(0.5, 1.5, 3.0),
				Vector3.fromJsonObject(w1.toJsonObject().getJsonObject(
						Wrench.FIELD_FORCE)));
		assertEquals(
				new Vector3(-0.5, -1.5, -3.0),
				Vector3.fromJsonObject(w1.toJsonObject().getJsonObject(
						Wrench.FIELD_TORQUE)));

		assertEquals(Wrench.TYPE, w1.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(w1.toString().hashCode(), w1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(w1));
		assertFalse(w1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(w1.equals(w1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Wrench clone = w1.clone();
		assertEquals(w1.toString(), clone.toString());
		assertEquals(w1.toJsonObject(), clone.toJsonObject());
		assertEquals(w1.getMessageType(), clone.getMessageType());
		assertEquals(w1.getForce(), clone.getForce());
		assertEquals(w1.getTorque(), clone.getTorque());
		assertNotSame(w1, clone);
		assertNotSame(w1.toString(), clone.toString());
		assertNotSame(w1.toJsonObject(), clone.toJsonObject());
		assertNotSame(w1.getForce(), clone.getForce());
		assertNotSame(w1.getTorque(), clone.getTorque());
	}

	@Test
	public void testFromJsonString() {
		Wrench p = Wrench.fromJsonString(w1.toString());
		assertEquals(w1.toString(), p.toString());
		assertEquals(w1.toJsonObject(), p.toJsonObject());
		assertEquals(w1.getMessageType(), p.getMessageType());
		assertEquals(w1.getForce(), p.getForce());
		assertEquals(w1.getTorque(), p.getTorque());
		assertNotSame(w1, p);
		assertNotSame(w1.toString(), p.toString());
		assertNotSame(w1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(w1.toString());
		Wrench p = Wrench.fromMessage(m);
		assertEquals(w1.toString(), p.toString());
		assertEquals(w1.toJsonObject(), p.toJsonObject());
		assertEquals(w1.getMessageType(), p.getMessageType());
		assertEquals(w1.getForce(), p.getForce());
		assertEquals(w1.getTorque(), p.getTorque());
		assertNotSame(w1, p);
		assertNotSame(w1.toString(), p.toString());
		assertNotSame(w1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Wrench.FIELD_FORCE, w1.getForce().toJsonObject())
				.add(Wrench.FIELD_TORQUE, w1.getTorque().toJsonObject())
				.build();
		Wrench p = Wrench.fromJsonObject(jsonObject);
		assertEquals(w1.toString(), p.toString());
		assertEquals(w1.toJsonObject(), p.toJsonObject());
		assertEquals(w1.getMessageType(), p.getMessageType());
		assertEquals(w1.getForce(), p.getForce());
		assertEquals(w1.getTorque(), p.getTorque());
		assertNotSame(w1, p);
		assertNotSame(w1.toString(), p.toString());
		assertNotSame(w1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoForce() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Wrench.FIELD_TORQUE, w1.getTorque().toJsonObject())
				.build();
		Wrench p = Wrench.fromJsonObject(jsonObject);
		assertEquals(new Vector3(), p.getForce());
		assertEquals(w1.getTorque(), p.getTorque());
	}

	@Test
	public void testFromJsonObjectNoTorque() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Wrench.FIELD_FORCE, w1.getForce().toJsonObject()).build();
		Wrench p = Wrench.fromJsonObject(jsonObject);
		assertEquals(w1.getForce(), p.getForce());
		assertEquals(new Vector3(), p.getTorque());
	}
}
