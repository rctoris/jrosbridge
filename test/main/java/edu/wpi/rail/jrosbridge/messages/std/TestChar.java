package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestChar {

	private Char empty, c1;

	@Before
	public void setUp() {
		empty = new Char();
		c1 = new Char('f');
	}

	@Test
	public void testConstructor() {
		assertEquals((char) 0, empty.getData());

		assertEquals("{\"data\":0}", empty.toString());

		assertEquals(1, empty.toJsonObject().size());
		assertEquals(0, empty.toJsonObject().getInt(Char.FIELD_DATA));

		assertEquals(Char.TYPE, empty.getMessageType());
	}

	@Test
	public void testCharConstructor() {
		assertEquals('f', c1.getData());

		assertEquals("{\"data\":102}", c1.toString());

		assertEquals(1, c1.toJsonObject().size());
		assertEquals(102, c1.toJsonObject().getInt(Char.FIELD_DATA));

		assertEquals(Char.TYPE, c1.getMessageType());
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
		Char clone = c1.clone();
		assertEquals(c1.toString(), clone.toString());
		assertEquals(c1.toJsonObject(), clone.toJsonObject());
		assertEquals(c1.getMessageType(), clone.getMessageType());
		assertEquals(c1.getData(), clone.getData());
		assertNotSame(c1, clone);
		assertNotSame(c1.toString(), clone.toString());
		assertNotSame(c1.toJsonObject(), clone.toJsonObject());
	}
}
