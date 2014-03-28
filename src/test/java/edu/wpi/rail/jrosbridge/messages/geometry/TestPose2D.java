package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestPose2D {

	private Pose2D empty, p1;

	@Before
	public void setUp() {
		empty = new Pose2D();
		p1 = new Pose2D(0.5, 1.5, 3.0);
	}

	@Test
	public void testConstructor() {
		assertEquals(0.0, empty.getX(), 0);
		assertEquals(0.0, empty.getY(), 0);
		assertEquals(0.0, empty.getTheta(), 0);

		assertEquals("{\"x\":0.0,\"y\":0.0,\"theta\":0.0}", empty.toString());

		assertEquals(3, empty.toJsonObject().size());
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(Pose2D.FIELD_X)
				.doubleValue(), 0);
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(Pose2D.FIELD_Y)
				.doubleValue(), 0);
		assertEquals(0.0, empty.toJsonObject()
				.getJsonNumber(Pose2D.FIELD_THETA).doubleValue(), 0);

		assertEquals(Pose2D.TYPE, empty.getMessageType());
	}

	@Test
	public void testDoubleDoubleAndDoubleConstructor() {
		assertEquals(0.5, p1.getX(), 0);
		assertEquals(1.5, p1.getY(), 0);
		assertEquals(3.0, p1.getTheta(), 0);

		assertEquals("{\"x\":0.5,\"y\":1.5,\"theta\":3.0}", p1.toString());

		assertEquals(3, p1.toJsonObject().size());
		assertEquals(0.5, p1.toJsonObject().getJsonNumber(Pose2D.FIELD_X)
				.doubleValue(), 0);
		assertEquals(1.5, p1.toJsonObject().getJsonNumber(Pose2D.FIELD_Y)
				.doubleValue(), 0);
		assertEquals(3.0, p1.toJsonObject().getJsonNumber(Pose2D.FIELD_THETA)
				.doubleValue(), 0);

		assertEquals(Pose2D.TYPE, empty.getMessageType());
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
		Pose2D clone = p1.clone();
		assertEquals(p1.toString(), clone.toString());
		assertEquals(p1.toJsonObject(), clone.toJsonObject());
		assertEquals(p1.getMessageType(), clone.getMessageType());
		assertEquals(p1.getX(), clone.getX(), 0);
		assertEquals(p1.getY(), clone.getY(), 0);
		assertEquals(p1.getTheta(), clone.getTheta(), 0);
		assertNotSame(p1, clone);
		assertNotSame(p1.toString(), clone.toString());
		assertNotSame(p1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		Pose2D p = Pose2D.fromJsonString(p1.toString());
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getX(), p.getX(), 0);
		assertEquals(p1.getY(), p.getY(), 0);
		assertEquals(p1.getTheta(), p.getTheta(), 0);
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(p1.toString());
		Pose2D p = Pose2D.fromMessage(m);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getX(), p.getX(), 0);
		assertEquals(p1.getY(), p.getY(), 0);
		assertEquals(p1.getTheta(), p.getTheta(), 0);
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Pose2D.FIELD_X, p1.getX()).add(Pose2D.FIELD_Y, p1.getY())
				.add(Pose2D.FIELD_THETA, p1.getTheta()).build();
		Pose2D p = Pose2D.fromJsonObject(jsonObject);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getX(), p.getX(), 0);
		assertEquals(p1.getY(), p.getY(), 0);
		assertEquals(p1.getTheta(), p.getTheta(), 0);
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoX() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Pose2D.FIELD_Y, p1.getY())
				.add(Pose2D.FIELD_THETA, p1.getTheta()).build();
		Pose2D p = Pose2D.fromJsonObject(jsonObject);
		assertEquals(0.0, p.getX(), 0);
		assertEquals(p1.getY(), p.getY(), 0);
		assertEquals(p1.getTheta(), p.getTheta(), 0);
	}

	@Test
	public void testFromJsonObjectNoY() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Pose2D.FIELD_X, p1.getX())
				.add(Pose2D.FIELD_THETA, p1.getTheta()).build();
		Pose2D p = Pose2D.fromJsonObject(jsonObject);
		assertEquals(p1.getX(), p.getX(), 0);
		assertEquals(0.0, p.getY(), 0);
		assertEquals(p1.getTheta(), p.getTheta(), 0);
	}

	@Test
	public void testFromJsonObjectNoZ() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Pose2D.FIELD_X, p1.getX()).add(Pose2D.FIELD_Y, p1.getY())
				.build();
		Pose2D p = Pose2D.fromJsonObject(jsonObject);
		assertEquals(p1.getX(), p.getX(), 0);
		assertEquals(p1.getY(), p.getY(), 0);
		assertEquals(0.0, p.getTheta(), 0);
	}
}
