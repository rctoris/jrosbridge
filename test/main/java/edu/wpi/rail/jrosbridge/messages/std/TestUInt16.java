package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

public class TestUInt16 {

	private UInt16 empty, i1;

	@Before
	public void setUp() {
		empty = new UInt16();
		i1 = new UInt16((short) 123);
	}

	@Test
	public void testConstructor() {
		assertEquals((short) 0, empty.getData());

		assertEquals("{\"data\":0}", empty.toString());

		assertEquals(1, empty.toJsonObject().size());
		assertEquals(
				(short) 0,
				Primitive.toUInt16((short) empty.toJsonObject().getInt(
						UInt16.FIELD_DATA)));

		assertEquals(UInt16.TYPE, empty.getMessageType());
	}

	@Test
	public void testByteConstructor() {
		assertEquals((short) 123, i1.getData());

		assertEquals("{\"data\":123}", i1.toString());

		assertEquals(1, i1.toJsonObject().size());
		assertEquals(
				(short) 123,
				Primitive.toUInt16((short) i1.toJsonObject().getInt(
						UInt16.FIELD_DATA)));

		assertEquals(UInt16.TYPE, i1.getMessageType());
	}

	@Test
	public void testByteConstructorNegative() {
		UInt16 c = new UInt16((short) -1);

		assertEquals((short) -1, c.getData());

		assertEquals("{\"data\":65535}", c.toString());

		assertEquals(1, c.toJsonObject().size());
		assertEquals(
				(short) -1,
				Primitive.toUInt16((short) c.toJsonObject().getInt(
						UInt16.FIELD_DATA)));

		assertEquals(UInt16.TYPE, c.getMessageType());
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
		UInt16 clone = i1.clone();
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
		UInt16 data = UInt16.fromJsonString(i1.toString());
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
		UInt16 data = UInt16.fromMessage(m);
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
				.add(UInt16.FIELD_DATA, i1.getData()).build();
		UInt16 data = UInt16.fromJsonObject(jsonObject);
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
		UInt16 data = UInt16.fromJsonObject(jsonObject);
		assertEquals((short) 0, data.getData());
	}
}
