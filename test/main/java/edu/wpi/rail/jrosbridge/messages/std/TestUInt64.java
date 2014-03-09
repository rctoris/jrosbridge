package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

public class TestUInt64 {

	private UInt64 empty, i1;

	@Before
	public void setUp() {
		empty = new UInt64();
		i1 = new UInt64(123L);
	}

	@Test
	public void testConstructor() {
		assertEquals(0L, empty.getData());

		assertEquals("{\"data\":0}", empty.toString());

		assertEquals(1, empty.toJsonObject().size());
		assertEquals(
				0L,
				Primitive.toUInt64(empty.toJsonObject()
						.getJsonNumber(UInt64.FIELD_DATA).bigIntegerValue()));

		assertEquals(UInt64.TYPE, empty.getMessageType());
	}

	@Test
	public void testByteConstructor() {
		assertEquals(123L, i1.getData());

		assertEquals("{\"data\":123}", i1.toString());

		assertEquals(1, i1.toJsonObject().size());
		assertEquals(
				123L,
				Primitive.toUInt64(i1.toJsonObject()
						.getJsonNumber(UInt64.FIELD_DATA).bigIntegerValue()));

		assertEquals(UInt64.TYPE, i1.getMessageType());
	}

	@Test
	public void testByteConstructorNegative() {
		UInt64 c = new UInt64(-1L);

		assertEquals(-1L, c.getData());

		assertEquals("{\"data\":18446744073709551615}", c.toString());

		assertEquals(1, c.toJsonObject().size());
		assertEquals(
				-1L,
				Primitive.toUInt64(c.toJsonObject()
						.getJsonNumber(UInt64.FIELD_DATA).bigIntegerValue()));

		assertEquals(UInt64.TYPE, c.getMessageType());
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
		UInt64 clone = i1.clone();
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
		UInt64 data = UInt64.fromJsonString(i1.toString());
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
		UInt64 data = UInt64.fromMessage(m);
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
				.add(UInt64.FIELD_DATA, i1.getData()).build();
		UInt64 data = UInt64.fromJsonObject(jsonObject);
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
		UInt64 data = UInt64.fromJsonObject(jsonObject);
		assertEquals(0L, data.getData());
	}
}
