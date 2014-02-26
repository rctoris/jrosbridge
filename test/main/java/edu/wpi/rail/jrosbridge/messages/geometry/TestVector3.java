package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestVector3 {

	private Vector3 empty, v1, v2, v3;

	@Before
	public void setUp() {
		empty = new Vector3();
		v1 = new Vector3(0.5);
		v2 = new Vector3(0.5, 1.5);
		v3 = new Vector3(0.5, 1.5, 3.0);
	}

	@Test
	public void testConstructor() {
		assertEquals(0.0, empty.getX());
		assertEquals(0.0, empty.getY());
		assertEquals(0.0, empty.getZ());

		assertEquals("{\"x\":0.0,\"y\":0.0,\"z\":0.0}", empty.toString());

		assertEquals(3, empty.toJsonObject().size());
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(Vector3.FIELD_X)
				.doubleValue());
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(Vector3.FIELD_Y)
				.doubleValue());
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(Vector3.FIELD_Z)
				.doubleValue());

		assertEquals(Vector3.TYPE, empty.getMessageType());
	}

	@Test
	public void testDoubleConstructor() {
		assertEquals(0.5, v1.getX());
		assertEquals(0.0, v1.getY());
		assertEquals(0.0, v1.getZ());

		assertEquals("{\"x\":0.5,\"y\":0.0,\"z\":0.0}", v1.toString());

		assertEquals(3, v1.toJsonObject().size());
		assertEquals(0.5, v1.toJsonObject().getJsonNumber(Vector3.FIELD_X)
				.doubleValue());
		assertEquals(0.0, v1.toJsonObject().getJsonNumber(Vector3.FIELD_Y)
				.doubleValue());
		assertEquals(0.0, v1.toJsonObject().getJsonNumber(Vector3.FIELD_Z)
				.doubleValue());

		assertEquals(Vector3.TYPE, empty.getMessageType());
	}

	@Test
	public void testDoubleDoubleConstructor() {
		assertEquals(0.5, v2.getX());
		assertEquals(1.5, v2.getY());
		assertEquals(0.0, v2.getZ());

		assertEquals("{\"x\":0.5,\"y\":1.5,\"z\":0.0}", v2.toString());

		assertEquals(3, v2.toJsonObject().size());
		assertEquals(0.5, v2.toJsonObject().getJsonNumber(Vector3.FIELD_X)
				.doubleValue());
		assertEquals(1.5, v2.toJsonObject().getJsonNumber(Vector3.FIELD_Y)
				.doubleValue());
		assertEquals(0.0, v2.toJsonObject().getJsonNumber(Vector3.FIELD_Z)
				.doubleValue());

		assertEquals(Vector3.TYPE, empty.getMessageType());
	}

	@Test
	public void testDoubleDoubleDoubleConstructor() {
		assertEquals(0.5, v3.getX());
		assertEquals(1.5, v3.getY());
		assertEquals(3.0, v3.getZ());

		assertEquals("{\"x\":0.5,\"y\":1.5,\"z\":3.0}", v3.toString());

		assertEquals(3, v3.toJsonObject().size());
		assertEquals(0.5, v3.toJsonObject().getJsonNumber(Vector3.FIELD_X)
				.doubleValue());
		assertEquals(1.5, v3.toJsonObject().getJsonNumber(Vector3.FIELD_Y)
				.doubleValue());
		assertEquals(3.0, v3.toJsonObject().getJsonNumber(Vector3.FIELD_Z)
				.doubleValue());

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
		assertEquals(v2.toString().hashCode(), v2.hashCode());
		assertEquals(v3.toString().hashCode(), v3.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(v1));
		assertFalse(v1.equals(empty));
		assertFalse(empty.equals(v2));
		assertFalse(v2.equals(empty));
		assertFalse(empty.equals(v3));
		assertFalse(v3.equals(empty));

		assertFalse(v1.equals(v2));
		assertFalse(v1.equals(v3));
		assertFalse(v2.equals(v1));
		assertFalse(v2.equals(v3));
		assertFalse(v3.equals(v1));
		assertFalse(v3.equals(v2));

		assertTrue(empty.equals(empty));
		assertTrue(v1.equals(v1));
		assertTrue(v2.equals(v2));
		assertTrue(v3.equals(v3));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Vector3 clone = v3.clone();
		assertEquals(v3.toString(), clone.toString());
		assertEquals(v3.toJsonObject(), clone.toJsonObject());
		assertEquals(v3.getMessageType(), clone.getMessageType());
		assertEquals(v3.getX(), clone.getX());
		assertEquals(v3.getY(), clone.getY());
		assertEquals(v3.getZ(), clone.getZ());
		assertNotSame(v3, clone);
		assertNotSame(v3.toString(), clone.toString());
		assertNotSame(v3.toJsonObject(), clone.toJsonObject());
	}
}
