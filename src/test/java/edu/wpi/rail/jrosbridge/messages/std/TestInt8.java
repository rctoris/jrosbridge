package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestInt8 {

	private Int8 empty, i1;

	@Before
	public void setUp() {
		empty = new Int8();
		i1 = new Int8((byte) 127);
	}

	@Test
	public void testConstructor() {
		assertEquals((byte) 0, empty.getData());

		assertEquals("{\"data\":0}", empty.toString());

		assertEquals(1, empty.toJsonObject().size());
		assertEquals(0, empty.toJsonObject().getInt(Int8.FIELD_DATA));

		assertEquals(Int8.TYPE, empty.getMessageType());
	}

	@Test
	public void testByteConstructor() {
		assertEquals((byte) 127, i1.getData());

		assertEquals("{\"data\":127}", i1.toString());

		assertEquals(1, i1.toJsonObject().size());
		assertEquals(127, i1.toJsonObject().getInt(Int8.FIELD_DATA));

		assertEquals(Int8.TYPE, i1.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(i1.toString().hashCode(), i1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(i1));
		assertFalse(i1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(i1.equals(i1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Int8 clone = i1.clone();
		assertEquals(i1.toString(), clone.toString());
		assertEquals(i1.toJsonObject(), clone.toJsonObject());
		assertEquals(i1.getMessageType(), clone.getMessageType());
		assertEquals(i1.getData(), clone.getData());
		assertNotSame(i1, clone);
		assertNotSame(i1.toString(), clone.toString());
		assertNotSame(i1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		Int8 data = Int8.fromJsonString(i1.toString());
		assertEquals(i1.toString(), data.toString());
		assertEquals(i1.toJsonObject(), data.toJsonObject());
		assertEquals(i1.getMessageType(), data.getMessageType());
		assertEquals(i1.getData(), data.getData());
		assertNotSame(i1, data);
		assertNotSame(i1.toString(), data.toString());
		assertNotSame(i1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(i1.toString());
		Int8 data = Int8.fromMessage(m);
		assertEquals(i1.toString(), data.toString());
		assertEquals(i1.toJsonObject(), data.toJsonObject());
		assertEquals(i1.getMessageType(), data.getMessageType());
		assertEquals(i1.getData(), data.getData());
		assertNotSame(i1, data);
		assertNotSame(i1.toString(), data.toString());
		assertNotSame(i1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Int8.FIELD_DATA, i1.getData()).build();
		Int8 data = Int8.fromJsonObject(jsonObject);
		assertEquals(i1.toString(), data.toString());
		assertEquals(i1.toJsonObject(), data.toJsonObject());
		assertEquals(i1.getMessageType(), data.getMessageType());
		assertEquals(i1.getData(), data.getData());
		assertNotSame(i1, data);
		assertNotSame(i1.toString(), data.toString());
		assertNotSame(i1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoData() {
		JsonObject jsonObject = Json.createObjectBuilder().build();
		Int8 data = Int8.fromJsonObject(jsonObject);
		assertEquals((byte) 0, data.getData());
	}
}
