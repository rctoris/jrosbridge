package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestString {

	private String empty, c1;

	@Before
	public void setUp() {
		empty = new String();
		c1 = new String("test");
	}

	@Test
	public void testConstructor() {
		assertEquals("", empty.getData());

		assertEquals("{\"data\":\"\"}", empty.toString());

		assertEquals(1, empty.toJsonObject().size());
		assertEquals("", empty.toJsonObject().getString(String.FIELD_DATA));

		assertEquals(String.TYPE, empty.getMessageType());
	}

	@Test
	public void testStringConstructor() {
		assertEquals("test", c1.getData());

		assertEquals("{\"data\":\"test\"}", c1.toString());

		assertEquals(1, c1.toJsonObject().size());
		assertEquals("test", c1.toJsonObject().getString(String.FIELD_DATA));

		assertEquals(String.TYPE, c1.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(c1.toString().hashCode(), c1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(c1));
		assertFalse(c1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(c1.equals(c1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		String clone = c1.clone();
		assertEquals(c1.toString(), clone.toString());
		assertEquals(c1.toJsonObject(), clone.toJsonObject());
		assertEquals(c1.getMessageType(), clone.getMessageType());
		assertEquals(c1.getData(), clone.getData());
		assertNotSame(c1, clone);
		assertNotSame(c1.toString(), clone.toString());
		assertNotSame(c1.toJsonObject(), clone.toJsonObject());
	}
}
