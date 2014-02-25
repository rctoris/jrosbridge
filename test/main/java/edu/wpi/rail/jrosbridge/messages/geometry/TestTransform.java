package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTransform {

	private Transform empty, p1, p2, p3;

	@Before
	public void setUp() {
		empty = new Transform();
		p1 = new Transform(new Vector3(0.5, 1.5, 3.0));
		p2 = new Transform(new Quaternion(-0.5, -1.5, -3.0, -4.5));
		p3 = new Transform(new Vector3(0.5, 1.5, 3.0), new Quaternion(-0.5,
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
		assertEquals(3,
				empty.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.size());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.getJsonNumber(Vector3.FIELD_X).doubleValue());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.getJsonNumber(Vector3.FIELD_Y).doubleValue());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.getJsonNumber(Vector3.FIELD_Z).doubleValue());
		assertEquals(4,
				empty.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.size());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_X).doubleValue());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_Y).doubleValue());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_Z).doubleValue());
		assertEquals(0.0,
				empty.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_W).doubleValue());

		assertEquals(Transform.TYPE, empty.getMessageType());
	}

	@Test
	public void testVector3Constructor() {
		assertEquals(new Vector3(0.5, 1.5, 3.0), p1.getTranslation());
		assertEquals(new Quaternion(), p1.getRotation());

		assertEquals("{\"translation\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
				+ "\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0,\"w\":0.0}}",
				p1.toString());

		assertEquals(2, p1.toJsonObject().size());
		assertEquals(3,
				p1.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.size());
		assertEquals(0.5,
				p1.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.getJsonNumber(Vector3.FIELD_X).doubleValue());
		assertEquals(1.5,
				p1.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.getJsonNumber(Vector3.FIELD_Y).doubleValue());
		assertEquals(3.0,
				p1.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.getJsonNumber(Vector3.FIELD_Z).doubleValue());
		assertEquals(4,
				p1.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.size());
		assertEquals(0.0,
				p1.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_X).doubleValue());
		assertEquals(0.0,
				p1.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_Y).doubleValue());
		assertEquals(0.0,
				p1.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_Z).doubleValue());
		assertEquals(0.0,
				p1.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_W).doubleValue());

		assertEquals(Transform.TYPE, p1.getMessageType());
	}

	@Test
	public void testQuaternionConstructor() {
		assertEquals(new Vector3(), p2.getTranslation());
		assertEquals(new Quaternion(-0.5, -1.5, -3.0, -4.5), p2.getRotation());

		assertEquals(
				"{\"translation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
						+ "\"rotation\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0,\"w\":-4.5}}",
				p2.toString());

		assertEquals(2, p2.toJsonObject().size());
		assertEquals(3,
				p2.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.size());
		assertEquals(0.0,
				p2.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.getJsonNumber(Vector3.FIELD_X).doubleValue());
		assertEquals(0.0,
				p2.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.getJsonNumber(Vector3.FIELD_Y).doubleValue());
		assertEquals(0.0,
				p2.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.getJsonNumber(Vector3.FIELD_Z).doubleValue());
		assertEquals(4,
				p2.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.size());
		assertEquals(-0.5,
				p2.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_X).doubleValue());
		assertEquals(-1.5,
				p2.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_Y).doubleValue());
		assertEquals(-3.0,
				p2.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_Z).doubleValue());
		assertEquals(-4.5,
				p2.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_W).doubleValue());

		assertEquals(Transform.TYPE, p2.getMessageType());
	}

	@Test
	public void testVector3AndQuaternionConstructor() {
		assertEquals(new Vector3(0.5, 1.5, 3.0), p3.getTranslation());
		assertEquals(new Quaternion(-0.5, -1.5, -3.0, -4.5), p3.getRotation());

		assertEquals(
				"{\"translation\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
						+ "\"rotation\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0,\"w\":-4.5}}",
				p3.toString());

		assertEquals(2, p3.toJsonObject().size());
		assertEquals(3,
				p3.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.size());
		assertEquals(0.5,
				p3.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.getJsonNumber(Vector3.FIELD_X).doubleValue());
		assertEquals(1.5,
				p3.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.getJsonNumber(Vector3.FIELD_Y).doubleValue());
		assertEquals(3.0,
				p3.toJsonObject().getJsonObject(Transform.FIELD_TRANSLATION)
						.getJsonNumber(Vector3.FIELD_Z).doubleValue());
		assertEquals(4,
				p3.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.size());
		assertEquals(-0.5,
				p3.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_X).doubleValue());
		assertEquals(-1.5,
				p3.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_Y).doubleValue());
		assertEquals(-3.0,
				p3.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_Z).doubleValue());
		assertEquals(-4.5,
				p3.toJsonObject().getJsonObject(Transform.FIELD_ROTATION)
						.getJsonNumber(Quaternion.FIELD_W).doubleValue());

		assertEquals(Transform.TYPE, p3.getMessageType());
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
		Transform clone = p3.clone();
		assertEquals(p3.toString(), clone.toString());
		assertEquals(p3.toJsonObject(), clone.toJsonObject());
		assertEquals(p3.getMessageType(), clone.getMessageType());
		assertEquals(p3.getTranslation(), clone.getTranslation());
		assertEquals(p3.getRotation(), clone.getRotation());
		assertNotSame(p3, clone);
		assertNotSame(p3.toString(), clone.toString());
		assertNotSame(p3.toJsonObject(), clone.toJsonObject());
		assertNotSame(p3.getTranslation(), clone.getTranslation());
		assertNotSame(p3.getRotation(), clone.getRotation());
	}
}
