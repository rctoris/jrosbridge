package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestQuaternion {

	private Quaternion empty, q1, q2, q3, q4;

	@Before
	public void setUp() {
		empty = new Quaternion();
		q1 = new Quaternion(0.5);
		q2 = new Quaternion(0.5, 1.5);
		q3 = new Quaternion(0.5, 1.5, 3.0);
		q4 = new Quaternion(0.5, 1.5, 3.0, 4.5);
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
	public void testDoubleConstructor() {
		assertEquals(0.5, q1.getX());
		assertEquals(0.0, q1.getY());
		assertEquals(0.0, q1.getZ());
		assertEquals(0.0, q1.getW());

		assertEquals("{\"x\":0.5,\"y\":0.0,\"z\":0.0,\"w\":0.0}", q1.toString());

		assertEquals(4, q1.toJsonObject().size());
		assertEquals(0.5, q1.toJsonObject().getJsonNumber(Quaternion.FIELD_X)
				.doubleValue());
		assertEquals(0.0, q1.toJsonObject().getJsonNumber(Quaternion.FIELD_Y)
				.doubleValue());
		assertEquals(0.0, q1.toJsonObject().getJsonNumber(Quaternion.FIELD_Z)
				.doubleValue());
		assertEquals(0.0, q1.toJsonObject().getJsonNumber(Quaternion.FIELD_W)
				.doubleValue());

		assertEquals(Quaternion.TYPE, empty.getMessageType());
	}

	@Test
	public void testDoubleAndDoubleConstructor() {
		assertEquals(0.5, q2.getX());
		assertEquals(1.5, q2.getY());
		assertEquals(0.0, q2.getZ());
		assertEquals(0.0, q2.getW());

		assertEquals("{\"x\":0.5,\"y\":1.5,\"z\":0.0,\"w\":0.0}", q2.toString());

		assertEquals(4, q2.toJsonObject().size());
		assertEquals(0.5, q2.toJsonObject().getJsonNumber(Quaternion.FIELD_X)
				.doubleValue());
		assertEquals(1.5, q2.toJsonObject().getJsonNumber(Quaternion.FIELD_Y)
				.doubleValue());
		assertEquals(0.0, q2.toJsonObject().getJsonNumber(Quaternion.FIELD_Z)
				.doubleValue());
		assertEquals(0.0, q2.toJsonObject().getJsonNumber(Quaternion.FIELD_W)
				.doubleValue());

		assertEquals(Quaternion.TYPE, empty.getMessageType());
	}

	@Test
	public void testDoubleDoubleAndDoubleConstructor() {
		assertEquals(0.5, q3.getX());
		assertEquals(1.5, q3.getY());
		assertEquals(3.0, q3.getZ());
		assertEquals(0.0, q3.getW());

		assertEquals("{\"x\":0.5,\"y\":1.5,\"z\":3.0,\"w\":0.0}", q3.toString());

		assertEquals(4, q3.toJsonObject().size());
		assertEquals(0.5, q3.toJsonObject().getJsonNumber(Quaternion.FIELD_X)
				.doubleValue());
		assertEquals(1.5, q3.toJsonObject().getJsonNumber(Quaternion.FIELD_Y)
				.doubleValue());
		assertEquals(3.0, q3.toJsonObject().getJsonNumber(Quaternion.FIELD_Z)
				.doubleValue());
		assertEquals(0.0, q3.toJsonObject().getJsonNumber(Quaternion.FIELD_W)
				.doubleValue());

		assertEquals(Quaternion.TYPE, empty.getMessageType());
	}

	@Test
	public void testDoubleDoubleDoubleAndDoubleConstructor() {
		assertEquals(0.5, q4.getX());
		assertEquals(1.5, q4.getY());
		assertEquals(3.0, q4.getZ());
		assertEquals(4.5, q4.getW());

		assertEquals("{\"x\":0.5,\"y\":1.5,\"z\":3.0,\"w\":4.5}", q4.toString());

		assertEquals(4, q4.toJsonObject().size());
		assertEquals(0.5, q4.toJsonObject().getJsonNumber(Quaternion.FIELD_X)
				.doubleValue());
		assertEquals(1.5, q4.toJsonObject().getJsonNumber(Quaternion.FIELD_Y)
				.doubleValue());
		assertEquals(3.0, q4.toJsonObject().getJsonNumber(Quaternion.FIELD_Z)
				.doubleValue());
		assertEquals(4.5, q4.toJsonObject().getJsonNumber(Quaternion.FIELD_W)
				.doubleValue());

		assertEquals(Quaternion.TYPE, empty.getMessageType());
	}

	@Test
	public void testSetType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(q1.toString().hashCode(), q1.hashCode());
		assertEquals(q2.toString().hashCode(), q2.hashCode());
		assertEquals(q3.toString().hashCode(), q3.hashCode());
		assertEquals(q4.toString().hashCode(), q4.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(q1));
		assertFalse(q1.equals(empty));
		assertFalse(empty.equals(q2));
		assertFalse(q2.equals(empty));
		assertFalse(empty.equals(q3));
		assertFalse(q3.equals(empty));
		assertFalse(empty.equals(q4));
		assertFalse(q4.equals(empty));

		assertFalse(q1.equals(q2));
		assertFalse(q1.equals(q3));
		assertFalse(q1.equals(q4));
		assertFalse(q2.equals(q1));
		assertFalse(q2.equals(q3));
		assertFalse(q2.equals(q4));
		assertFalse(q3.equals(q1));
		assertFalse(q3.equals(q2));
		assertFalse(q3.equals(q4));
		assertFalse(q4.equals(q1));
		assertFalse(q4.equals(q2));
		assertFalse(q4.equals(q3));

		assertTrue(empty.equals(empty));
		assertTrue(q1.equals(q1));
		assertTrue(q2.equals(q2));
		assertTrue(q3.equals(q3));
		assertTrue(q4.equals(q4));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Quaternion clone = q3.clone();
		assertEquals(q3.toString(), clone.toString());
		assertEquals(q3.toJsonObject(), clone.toJsonObject());
		assertEquals(q3.getMessageType(), clone.getMessageType());
		assertEquals(q3.getX(), clone.getX());
		assertEquals(q3.getY(), clone.getY());
		assertEquals(q3.getZ(), clone.getZ());
		assertEquals(q3.getW(), clone.getW());
		assertNotSame(q3, clone);
		assertNotSame(q3.toString(), clone.toString());
		assertNotSame(q3.toJsonObject(), clone.toJsonObject());
	}
}
