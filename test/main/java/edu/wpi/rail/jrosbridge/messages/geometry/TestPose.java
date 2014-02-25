package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestPose {

	private Pose empty, p1, p2, p3;

	@Before
	public void setUp() {
		empty = new Pose();
		p1 = new Pose(new Point(0.5, 1.5, 3.0));
		p2 = new Pose(new Quaternion(-0.5, -1.5, -3.0, -4.5));
		p3 = new Pose(new Point(0.5, 1.5, 3.0), new Quaternion(-0.5, -1.5,
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
		assertEquals(3, empty.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.size());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
						.getJsonNumber(Point.FIELD_X).doubleValue());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
						.getJsonNumber(Point.FIELD_Y).doubleValue());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
						.getJsonNumber(Point.FIELD_Z).doubleValue());
		assertEquals(4,
				empty.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.size());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_X).doubleValue());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_Y).doubleValue());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_Z).doubleValue());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_W).doubleValue());

		assertEquals(Pose.TYPE, empty.getMessageType());
	}

	@Test
	public void testPointConstructor() {
		assertEquals(new Point(0.5, 1.5, 3.0), p1.getPosition());
		assertEquals(new Quaternion(), p1.getOrientation());

		assertEquals("{\"position\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
				+ "\"orientation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0,\"w\":0.0}}",
				p1.toString());

		assertEquals(2, p1.toJsonObject().size());
		assertEquals(3, p1.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.size());
		assertEquals(0.5, p1.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.getJsonNumber(Point.FIELD_X).doubleValue());
		assertEquals(1.5, p1.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.getJsonNumber(Point.FIELD_Y).doubleValue());
		assertEquals(3.0, p1.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.getJsonNumber(Point.FIELD_Z).doubleValue());
		assertEquals(4, p1.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
				.size());
		assertEquals(0.0,
				p1.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_X).doubleValue());
		assertEquals(0.0,
				p1.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_Y).doubleValue());
		assertEquals(0.0,
				p1.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_Z).doubleValue());
		assertEquals(0.0,
				p1.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_W).doubleValue());

		assertEquals(Pose.TYPE, p1.getMessageType());
	}

	@Test
	public void testQuaternionConstructor() {
		assertEquals(new Point(), p2.getPosition());
		assertEquals(new Quaternion(-0.5, -1.5, -3.0, -4.5),
				p2.getOrientation());

		assertEquals(
				"{\"position\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
						+ "\"orientation\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0,\"w\":-4.5}}",
				p2.toString());

		assertEquals(2, p2.toJsonObject().size());
		assertEquals(3, p2.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.size());
		assertEquals(0.0, p2.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.getJsonNumber(Point.FIELD_X).doubleValue());
		assertEquals(0.0, p2.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.getJsonNumber(Point.FIELD_Y).doubleValue());
		assertEquals(0.0, p2.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.getJsonNumber(Point.FIELD_Z).doubleValue());
		assertEquals(4, p2.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
				.size());
		assertEquals(-0.5,
				p2.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_X).doubleValue());
		assertEquals(-1.5,
				p2.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_Y).doubleValue());
		assertEquals(-3.0,
				p2.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_Z).doubleValue());
		assertEquals(-4.5,
				p2.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_W).doubleValue());

		assertEquals(Pose.TYPE, p2.getMessageType());
	}

	@Test
	public void testPointAndQuaternionConstructor() {
		assertEquals(new Point(0.5, 1.5, 3.0), p3.getPosition());
		assertEquals(new Quaternion(-0.5, -1.5, -3.0, -4.5),
				p3.getOrientation());

		assertEquals(
				"{\"position\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
						+ "\"orientation\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0,\"w\":-4.5}}",
				p3.toString());

		assertEquals(2, p3.toJsonObject().size());
		assertEquals(3, p3.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.size());
		assertEquals(0.5, p3.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.getJsonNumber(Point.FIELD_X).doubleValue());
		assertEquals(1.5, p3.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.getJsonNumber(Point.FIELD_Y).doubleValue());
		assertEquals(3.0, p3.toJsonObject().getJsonObject(Pose.FIELD_POSITION)
				.getJsonNumber(Point.FIELD_Z).doubleValue());
		assertEquals(4, p3.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
				.size());
		assertEquals(-0.5,
				p3.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_X).doubleValue());
		assertEquals(-1.5,
				p3.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_Y).doubleValue());
		assertEquals(-3.0,
				p3.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_Z).doubleValue());
		assertEquals(-4.5,
				p3.toJsonObject().getJsonObject(Pose.FIELD_ORIENTATION)
						.getJsonNumber(Quaternion.FIELD_W).doubleValue());

		assertEquals(Pose.TYPE, p3.getMessageType());
	}

	@Test
	public void testSetType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(p1.toString().hashCode(), p1.hashCode());
		assertEquals(p2.toString().hashCode(), p2.hashCode());
		assertEquals(p3.toString().hashCode(), p3.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(p1));
		assertFalse(p1.equals(empty));
		assertFalse(empty.equals(p2));
		assertFalse(p2.equals(empty));
		assertFalse(empty.equals(p3));
		assertFalse(p3.equals(empty));

		assertFalse(p1.equals(p2));
		assertFalse(p1.equals(p3));
		assertFalse(p2.equals(p1));
		assertFalse(p2.equals(p3));
		assertFalse(p3.equals(p1));
		assertFalse(p3.equals(p2));

		assertTrue(empty.equals(empty));
		assertTrue(p1.equals(p1));
		assertTrue(p2.equals(p2));
		assertTrue(p3.equals(p3));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Pose clone = p3.clone();
		assertEquals(p3.toString(), clone.toString());
		assertEquals(p3.toJsonObject(), clone.toJsonObject());
		assertEquals(p3.getMessageType(), clone.getMessageType());
		assertEquals(p3.getPosition(), clone.getPosition());
		assertEquals(p3.getOrientation(), clone.getOrientation());
		assertNotSame(p3, clone);
		assertNotSame(p3.toString(), clone.toString());
		assertNotSame(p3.toJsonObject(), clone.toJsonObject());
		assertNotSame(p3.getPosition(), clone.getPosition());
		assertNotSame(p3.getOrientation(), clone.getOrientation());
	}
}
