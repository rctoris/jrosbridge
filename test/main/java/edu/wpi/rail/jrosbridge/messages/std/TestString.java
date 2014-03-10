package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestString {

	private String empty, s1;

	@Before
	public void setUp() {
		empty = new String();
		s1 = new String("test");
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
		assertEquals("test", s1.getData());

		assertEquals("{\"data\":\"test\"}", s1.toString());

		assertEquals(1, s1.toJsonObject().size());
		assertEquals("test", s1.toJsonObject().getString(String.FIELD_DATA));

		assertEquals(String.TYPE, s1.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(s1.toString().hashCode(), s1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(s1));
		assertFalse(s1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(s1.equals(s1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		String clone = s1.clone();
		assertEquals(s1.toString(), clone.toString());
		assertEquals(s1.toJsonObject(), clone.toJsonObject());
		assertEquals(s1.getMessageType(), clone.getMessageType());
		assertEquals(s1.getData(), clone.getData());
		assertNotSame(s1, clone);
		assertNotSame(s1.toString(), clone.toString());
		assertNotSame(s1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		String data = String.fromJsonString(s1.toString());
		assertEquals(s1.toString(), data.toString());
		assertEquals(s1.toJsonObject(), data.toJsonObject());
		assertEquals(s1.getMessageType(), data.getMessageType());
		assertEquals(s1.getData(), data.getData());
		assertNotSame(s1, data);
		assertNotSame(s1.toString(), data.toString());
		assertNotSame(s1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(s1.toString());
		String data = String.fromMessage(m);
		assertEquals(s1.toString(), data.toString());
		assertEquals(s1.toJsonObject(), data.toJsonObject());
		assertEquals(s1.getMessageType(), data.getMessageType());
		assertEquals(s1.getData(), data.getData());
		assertNotSame(s1, data);
		assertNotSame(s1.toString(), data.toString());
		assertNotSame(s1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(String.FIELD_DATA, s1.getData()).build();
		String data = String.fromJsonObject(jsonObject);
		assertEquals(s1.toString(), data.toString());
		assertEquals(s1.toJsonObject(), data.toJsonObject());
		assertEquals(s1.getMessageType(), data.getMessageType());
		assertEquals(s1.getData(), data.getData());
		assertNotSame(s1, data);
		assertNotSame(s1.toString(), data.toString());
		assertNotSame(s1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoData() {
		JsonObject jsonObject = Json.createObjectBuilder().build();
		String data = String.fromJsonObject(jsonObject);
		assertEquals("", data.getData());
	}
}
