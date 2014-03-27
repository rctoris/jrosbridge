package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

public class TestUInt32 {

	private UInt32 empty, i1;

	@Before
	public void setUp() {
		empty = new UInt32();
		i1 = new UInt32(123);
	}

	@Test
	public void testConstructor() {
		assertEquals(0, empty.getData());

		assertEquals("{\"data\":0}", empty.toString());

		assertEquals(1, empty.toJsonObject().size());
		assertEquals(
				0,
				Primitive.toUInt32(empty.toJsonObject().getInt(
						UInt32.FIELD_DATA)));

		assertEquals(UInt32.TYPE, empty.getMessageType());
	}

	@Test
	public void testIntConstructor() {
		assertEquals(123, i1.getData());

		assertEquals("{\"data\":123}", i1.toString());

		assertEquals(1, i1.toJsonObject().size());
		assertEquals(123,
				Primitive.toUInt32(i1.toJsonObject().getInt(UInt32.FIELD_DATA)));

		assertEquals(UInt32.TYPE, i1.getMessageType());
	}

	@Test
	public void testIntConstructorNegative() {
		UInt32 c = new UInt32(-1);

		assertEquals(-1, c.getData());

		assertEquals("{\"data\":4294967295}", c.toString());

		assertEquals(1, c.toJsonObject().size());
		assertEquals(-1,
				Primitive.toUInt32(c.toJsonObject().getInt(UInt32.FIELD_DATA)));

		assertEquals(UInt32.TYPE, c.getMessageType());
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
		UInt32 clone = i1.clone();
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
		UInt32 data = UInt32.fromJsonString(i1.toString());
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
		UInt32 data = UInt32.fromMessage(m);
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
				.add(UInt32.FIELD_DATA, i1.getData()).build();
		UInt32 data = UInt32.fromJsonObject(jsonObject);
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
		UInt32 data = UInt32.fromJsonObject(jsonObject);
		assertEquals(0, data.getData());
	}
}
