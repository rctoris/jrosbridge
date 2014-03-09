package edu.wpi.rail.jrosbridge.messages;

import static org.junit.Assert.*;

import javax.json.Json;

import org.junit.Before;
import org.junit.Test;

public class TestMessage {

	private Message empty, m1, m2, m3, m4;

	@Before
	public void setUp() {
		empty = new Message();
		m1 = new Message("{\"test\" : 123, \"test2\" : \"abc\"}");
		m2 = new Message(Json.createObjectBuilder().add("test", 123)
				.add("test2", "abc").build());
		m3 = new Message("{\"test\" : 123, \"test2\" : \"abc\"}", "type");
		m4 = new Message(Json.createObjectBuilder().add("test", 123)
				.add("test2", "abc").build(), "type");
	}

	@Test
	public void testConstructor() {
		assertEquals(Message.EMPTY_MESSAGE, empty.toString());
		assertEquals(0, empty.toJsonObject().size());
		assertEquals("", empty.getMessageType());
	}

	@Test
	public void testStringConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", m1.toString());
		assertEquals(2, m1.toJsonObject().size());
		assertEquals(123, m1.toJsonObject().getInt("test"));
		assertEquals("abc", m1.toJsonObject().getString("test2"));
		assertEquals("", m1.getMessageType());
	}

	@Test
	public void testStringAndStringConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", m3.toString());
		assertEquals(2, m3.toJsonObject().size());
		assertEquals(123, m3.toJsonObject().getInt("test"));
		assertEquals("abc", m3.toJsonObject().getString("test2"));
		assertEquals("type", m3.getMessageType());
	}

	@Test
	public void testJsonObjectConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", m2.toString());
		assertEquals(2, m2.toJsonObject().size());
		assertEquals(123, m2.toJsonObject().getInt("test"));
		assertEquals("abc", m2.toJsonObject().getString("test2"));
		assertEquals("", m2.getMessageType());
	}

	@Test
	public void testJsonObjectAndStringConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", m4.toString());
		assertEquals(2, m4.toJsonObject().size());
		assertEquals(123, m4.toJsonObject().getInt("test"));
		assertEquals("abc", m4.toJsonObject().getString("test2"));
		assertEquals("type", m4.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(m1.toString().hashCode(), m1.hashCode());
		assertEquals(m2.toString().hashCode(), m2.hashCode());
		assertEquals(m3.toString().hashCode(), m3.hashCode());
		assertEquals(m4.toString().hashCode(), m4.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(m1));
		assertFalse(m1.equals(empty));
		assertFalse(empty.equals(m2));
		assertFalse(m2.equals(empty));
		assertFalse(empty.equals(m3));
		assertFalse(m3.equals(empty));
		assertFalse(empty.equals(m4));
		assertFalse(m4.equals(empty));

		assertTrue(m1.equals(m2));
		assertTrue(m1.equals(m3));
		assertTrue(m1.equals(m4));
		assertTrue(m2.equals(m1));
		assertTrue(m2.equals(m3));
		assertTrue(m2.equals(m4));
		assertTrue(m3.equals(m1));
		assertTrue(m3.equals(m2));
		assertTrue(m3.equals(m4));
		assertTrue(m4.equals(m1));
		assertTrue(m4.equals(m2));
		assertTrue(m4.equals(m3));

		assertTrue(empty.equals(empty));
		assertTrue(m1.equals(m1));
		assertTrue(m2.equals(m2));
		assertTrue(m3.equals(m3));
		assertTrue(m4.equals(m4));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Message clone = m3.clone();
		assertEquals(m3.toString(), clone.toString());
		assertEquals(m3.toJsonObject(), clone.toJsonObject());
		assertEquals(m3.getMessageType(), clone.getMessageType());
		assertNotSame(m3, clone);
		assertNotSame(m3.toString(), clone.toString());
	}
}
