package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

public class TestChar {

	private Char empty, c1;

	@Before
	public void setUp() {
		empty = new Char();
		c1 = new Char((byte) 123);
	}

	@Test
	public void testConstructor() {
		assertEquals((byte) 0, empty.getData());

		assertEquals("{\"data\":0}", empty.toString());

		assertEquals(1, empty.toJsonObject().size());
		assertEquals(
				(byte) 0,
				Primitive.toUInt8((short) empty.toJsonObject().getInt(
						Char.FIELD_DATA)));

		assertEquals(Char.TYPE, empty.getMessageType());
	}

	@Test
	public void testByteConstructor() {
		assertEquals((byte) 123, c1.getData());

		assertEquals("{\"data\":123}", c1.toString());

		assertEquals(1, c1.toJsonObject().size());
		assertEquals(
				(byte) 123,
				Primitive.toUInt8((short) c1.toJsonObject().getInt(
						Char.FIELD_DATA)));

		assertEquals(Char.TYPE, c1.getMessageType());
	}

	@Test
	public void testByteConstructorNegative() {
		Char c = new Char((byte) -1);

		assertEquals((byte) -1, c.getData());

		assertEquals("{\"data\":255}", c.toString());

		assertEquals(1, c.toJsonObject().size());
		assertEquals(
				(byte) -1,
				Primitive.toUInt8((short) c.toJsonObject().getInt(
						Char.FIELD_DATA)));

		assertEquals(Char.TYPE, c.getMessageType());
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

	@Test
	public void testFromJsonString() {
		Char data = Char.fromJsonString(c1.toString());
		assertEquals(c1.toString(), data.toString());
		assertEquals(c1.toJsonObject(), data.toJsonObject());
		assertEquals(c1.getMessageType(), data.getMessageType());
		assertEquals(c1.getData(), data.getData());
		assertNotSame(c1, data);
		assertNotSame(c1.toString(), data.toString());
		assertNotSame(c1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(c1.toString());
		Char data = Char.fromMessage(m);
		assertEquals(c1.toString(), data.toString());
		assertEquals(c1.toJsonObject(), data.toJsonObject());
		assertEquals(c1.getMessageType(), data.getMessageType());
		assertEquals(c1.getData(), data.getData());
		assertNotSame(c1, data);
		assertNotSame(c1.toString(), data.toString());
		assertNotSame(c1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Char.FIELD_DATA, c1.getData()).build();
		Char data = Char.fromJsonObject(jsonObject);
		assertEquals(c1.toString(), data.toString());
		assertEquals(c1.toJsonObject(), data.toJsonObject());
		assertEquals(c1.getMessageType(), data.getMessageType());
		assertEquals(c1.getData(), data.getData());
		assertNotSame(c1, data);
		assertNotSame(c1.toString(), data.toString());
		assertNotSame(c1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoData() {
		JsonObject jsonObject = Json.createObjectBuilder().build();
		Char data = Char.fromJsonObject(jsonObject);
		assertEquals((byte) 0, data.getData());
	}
}
