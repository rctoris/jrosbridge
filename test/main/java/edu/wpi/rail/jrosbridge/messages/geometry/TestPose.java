package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestPose {

	private Pose empty, p1;

	@Before
	public void setUp() {
		empty = new Pose();
		p1 = new Pose(new Point(0.5, 1.5, 3.0), new Quaternion(-0.5, -1.5,
				-3.0, -4.5));
	}

	@Test
	public void testConstructor() {
		assertEquals(new Point(), empty.getPosition());
		assertEquals(new Quaternion(), empty.getOrientation());

		assertEquals("{\"position\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
				+ "\"orientation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0,\"w\":0.0}}",
				empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Point(),
				Point.fromJsonObject(empty.toJsonObject().getJsonObject(
						Pose.FIELD_POSITION)));
		assertEquals(
				new Quaternion(),
				Quaternion.fromJsonObject(empty.toJsonObject().getJsonObject(
						Pose.FIELD_ORIENTATION)));

		assertEquals(Pose.TYPE, empty.getMessageType());
	}

	@Test
	public void testPointAndQuaternionConstructor() {
		assertEquals(new Point(0.5, 1.5, 3.0), p1.getPosition());
		assertEquals(new Quaternion(-0.5, -1.5, -3.0, -4.5),
				p1.getOrientation());

		assertEquals(
				"{\"position\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
						+ "\"orientation\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0,\"w\":-4.5}}",
				p1.toString());

		assertEquals(2, p1.toJsonObject().size());
		assertEquals(
				new Point(0.5, 1.5, 3.0),
				Point.fromJsonObject(p1.toJsonObject().getJsonObject(
						Pose.FIELD_POSITION)));
		assertEquals(
				new Quaternion(-0.5, -1.5, -3.0, -4.5),
				Quaternion.fromJsonObject(p1.toJsonObject().getJsonObject(
						Pose.FIELD_ORIENTATION)));

		assertEquals(Pose.TYPE, p1.getMessageType());
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
		Pose clone = p1.clone();
		assertEquals(p1.toString(), clone.toString());
		assertEquals(p1.toJsonObject(), clone.toJsonObject());
		assertEquals(p1.getMessageType(), clone.getMessageType());
		assertEquals(p1.getPosition(), clone.getPosition());
		assertEquals(p1.getOrientation(), clone.getOrientation());
		assertNotSame(p1, clone);
		assertNotSame(p1.toString(), clone.toString());
		assertNotSame(p1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		Pose p = Pose.fromJsonString(p1.toString());
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getPosition(), p.getPosition());
		assertEquals(p1.getOrientation(), p.getOrientation());
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(p1.toString());
		Pose p = Pose.fromMessage(m);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getPosition(), p.getPosition());
		assertEquals(p1.getOrientation(), p.getOrientation());
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(Pose.FIELD_POSITION, p1.getPosition().toJsonObject())
				.add(Pose.FIELD_ORIENTATION, p1.getOrientation().toJsonObject())
				.build();
		Pose p = Pose.fromJsonObject(jsonObject);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getPosition(), p.getPosition());
		assertEquals(p1.getOrientation(), p.getOrientation());
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoPosition() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(Pose.FIELD_ORIENTATION, p1.getOrientation().toJsonObject())
				.build();
		Pose p = Pose.fromJsonObject(jsonObject);
		assertEquals(new Point(), p.getPosition());
		assertEquals(p1.getOrientation(), p.getOrientation());
	}

	@Test
	public void testFromJsonObjectNoOrientation() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Pose.FIELD_POSITION, p1.getPosition().toJsonObject())
				.build();
		Pose p = Pose.fromJsonObject(jsonObject);
		assertEquals(p1.getPosition(), p.getPosition());
		assertEquals(new Quaternion(), p.getOrientation());
	}
}
