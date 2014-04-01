package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestEmpty {

	private Empty empty;

	@Before
	public void setUp() {
		empty = new Empty();
	}

	@Test
	public void testConstructor() {
		assertEquals("{}", empty.toString());

		assertEquals(0, empty.toJsonObject().size());

		assertEquals(Empty.TYPE, empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
	}

	@Test
	public void testEquals() {
		assertTrue(empty.equals(empty));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Empty clone = empty.clone();
		assertEquals(empty.toString(), clone.toString());
		assertEquals(empty.toJsonObject(), clone.toJsonObject());
		assertEquals(empty.getMessageType(), clone.getMessageType());
		assertNotSame(empty, clone);
		assertNotSame(empty.toString(), clone.toString());
		assertNotSame(empty.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		Empty data = Empty.fromJsonString(empty.toString());
		assertEquals(empty.toString(), data.toString());
		assertEquals(empty.toJsonObject(), data.toJsonObject());
		assertEquals(empty.getMessageType(), data.getMessageType());
		assertNotSame(empty, data);
		assertNotSame(empty.toString(), data.toString());
		assertNotSame(empty.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(empty.toString());
		Empty data = Empty.fromMessage(m);
		assertEquals(empty.toString(), data.toString());
		assertEquals(empty.toJsonObject(), data.toJsonObject());
		assertEquals(empty.getMessageType(), data.getMessageType());
		assertNotSame(empty, data);
		assertNotSame(empty.toString(), data.toString());
		assertNotSame(empty.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder().build();
		Empty data = Empty.fromJsonObject(jsonObject);
		assertEquals(empty.toString(), data.toString());
		assertEquals(empty.toJsonObject(), data.toJsonObject());
		assertEquals(empty.getMessageType(), data.getMessageType());
		assertNotSame(empty, data);
		assertNotSame(empty.toString(), data.toString());
		assertNotSame(empty.toJsonObject(), data.toJsonObject());
	}
}
