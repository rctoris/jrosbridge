package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

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

	@Test
	public void testFromJsonString() {
		Bool data = Bool.fromJsonString(b1.toString());
		assertEquals(b1.toString(), data.toString());
		assertEquals(b1.toJsonObject(), data.toJsonObject());
		assertEquals(b1.getMessageType(), data.getMessageType());
		assertEquals(b1.getData(), data.getData());
		assertNotSame(b1, data);
		assertNotSame(b1.toString(), data.toString());
		assertNotSame(b1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(b1.toString());
		Bool data = Bool.fromMessage(m);
		assertEquals(b1.toString(), data.toString());
		assertEquals(b1.toJsonObject(), data.toJsonObject());
		assertEquals(b1.getMessageType(), data.getMessageType());
		assertEquals(b1.getData(), data.getData());
		assertNotSame(b1, data);
		assertNotSame(b1.toString(), data.toString());
		assertNotSame(b1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Bool.FIELD_DATA, b1.getData()).build();
		Bool data = Bool.fromJsonObject(jsonObject);
		assertEquals(b1.toString(), data.toString());
		assertEquals(b1.toJsonObject(), data.toJsonObject());
		assertEquals(b1.getMessageType(), data.getMessageType());
		assertEquals(b1.getData(), data.getData());
		assertNotSame(b1, data);
		assertNotSame(b1.toString(), data.toString());
		assertNotSame(b1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoData() {
		JsonObject jsonObject = Json.createObjectBuilder().build();
		Bool data = Bool.fromJsonObject(jsonObject);
		assertFalse(data.getData());
	}
}
