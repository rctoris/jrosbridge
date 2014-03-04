package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBool {

	private Bool empty, b1;

	@Before
	public void setUp() {
		empty = new Bool();
		b1 = new Bool(true);
	}

	@Test
	public void testConstructor() {
		assertFalse(empty.getData());

		assertEquals("{\"data\":false}", empty.toString());

		assertEquals(1, empty.toJsonObject().size());
		assertFalse(empty.toJsonObject().getBoolean(Bool.FIELD_DATA));

		assertEquals(Bool.TYPE, empty.getMessageType());
	}

	@Test
	public void testBooleanConstructor() {
		assertTrue(b1.getData());

		assertEquals("{\"data\":true}", b1.toString());

		assertEquals(1, b1.toJsonObject().size());
		assertTrue(b1.toJsonObject().getBoolean(Bool.FIELD_DATA));

		assertEquals(Bool.TYPE, b1.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(b1.toString().hashCode(), b1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(b1));
		assertFalse(b1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(b1.equals(b1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Bool clone = b1.clone();
		assertEquals(b1.toString(), clone.toString());
		assertEquals(b1.toJsonObject(), clone.toJsonObject());
		assertEquals(b1.getMessageType(), clone.getMessageType());
		assertEquals(b1.getData(), clone.getData());
		assertNotSame(b1, clone);
		assertNotSame(b1.toString(), clone.toString());
		assertNotSame(b1.toJsonObject(), clone.toJsonObject());
	}
}
