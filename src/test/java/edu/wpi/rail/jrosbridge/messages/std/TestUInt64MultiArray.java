package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

public class TestUInt64MultiArray {

	private UInt64MultiArray empty, a1;

	@Before
	public void setUp() {
		empty = new UInt64MultiArray();
		a1 = new UInt64MultiArray(new MultiArrayLayout(
				new MultiArrayDimension[] {
						new MultiArrayDimension("test", 10, 20),
						new MultiArrayDimension("test2", 30, 40) }, 50),
				new long[] { 5, 10 });
	}

	@Test
	public void testConstructor() {
		assertEquals(new MultiArrayLayout(), empty.getLayout());
		assertTrue(Arrays.equals(new long[0], empty.getData()));
		assertEquals(0, empty.getData().length);
		assertEquals(0, empty.size());

		assertEquals("{\"layout\":{\"dim\":[],\"data_offset\":0},\"data\":[]}",
				empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(new MultiArrayLayout(),
				MultiArrayLayout.fromJsonObject(empty.toJsonObject()
						.getJsonObject(UInt64MultiArray.FIELD_LAYOUT)));
		assertEquals(0,
				empty.toJsonObject().getJsonArray(UInt64MultiArray.FIELD_DATA)
						.size());

		assertEquals(UInt64MultiArray.TYPE, empty.getMessageType());
	}

	@Test
	public void testMultiArrayLayoutAndLongArrayConstructor() {
		assertEquals(new MultiArrayLayout(new MultiArrayDimension[] {
				new MultiArrayDimension("test", 10, 20),
				new MultiArrayDimension("test2", 30, 40) }, 50), a1.getLayout());
		assertTrue(Arrays.equals(new long[] { 5, 10 }, a1.getData()));
		assertEquals(2, a1.size());
		assertEquals((long) 5, a1.get(0));
		assertEquals((long) 10, a1.get(1));

		assertEquals("{\"layout\":{\"dim\":["
				+ "{\"label\":\"test\",\"size\":10,\"stride\":20},"
				+ "{\"label\":\"test2\",\"size\":30,\"stride\":40}"
				+ "],\"data_offset\":50},\"data\":[5,10]}", a1.toString());

		assertEquals(2, a1.toJsonObject().size());
		assertEquals(new MultiArrayLayout(new MultiArrayDimension[] {
				new MultiArrayDimension("test", 10, 20),
				new MultiArrayDimension("test2", 30, 40) }, 50),
				MultiArrayLayout.fromJsonObject(a1.toJsonObject()
						.getJsonObject(UInt64MultiArray.FIELD_LAYOUT)));
		assertEquals(2,
				a1.toJsonObject().getJsonArray(UInt64MultiArray.FIELD_DATA)
						.size());
		assertEquals(
				(long) 5,
				(long) a1.toJsonObject()
						.getJsonArray(UInt64MultiArray.FIELD_DATA).getInt(0));
		assertEquals(
				(long) 10,
				(long) a1.toJsonObject()
						.getJsonArray(UInt64MultiArray.FIELD_DATA).getInt(1));

		assertEquals(UInt64MultiArray.TYPE, a1.getMessageType());
	}

	@Test
	public void testMultiArrayLayoutAndIntArrayConstructorNegative() {
		UInt64MultiArray a = new UInt64MultiArray(new MultiArrayLayout(),
				new long[] { -1 });

		assertEquals(new MultiArrayLayout(), a.getLayout());
		assertTrue(Arrays.equals(new long[] { -1 }, a.getData()));
		assertEquals(1, a.size());
		assertEquals((long) -1, a.get(0));

		assertEquals(
				"{\"layout\":{\"dim\":[],\"data_offset\":0},\"data\":[18446744073709551615]}",
				a.toString());

		assertEquals(2, a.toJsonObject().size());
		assertEquals(
				new MultiArrayLayout(),
				MultiArrayLayout.fromJsonObject(a.toJsonObject().getJsonObject(
						UInt64MultiArray.FIELD_LAYOUT)));
		assertEquals(1,
				a.toJsonObject().getJsonArray(UInt64MultiArray.FIELD_DATA)
						.size());
		assertEquals(
				(long) -1,
				Primitive.toUInt64(a.toJsonObject()
						.getJsonArray(UInt64MultiArray.FIELD_DATA)
						.getJsonNumber(0).bigIntegerValue()));

		assertEquals(UInt64MultiArray.TYPE, a.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(a1.toString().hashCode(), a1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(a1));
		assertFalse(a1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(a1.equals(a1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		UInt64MultiArray clone = a1.clone();
		assertEquals(a1.toString(), clone.toString());
		assertEquals(a1.toJsonObject(), clone.toJsonObject());
		assertEquals(a1.getMessageType(), clone.getMessageType());
		assertTrue(Arrays.equals(a1.getData(), clone.getData()));
		assertEquals(a1.getLayout(), clone.getLayout());
		assertEquals(a1.size(), clone.size());
		assertNotSame(a1, clone);
		assertNotSame(a1.toString(), clone.toString());
		assertNotSame(a1.toJsonObject(), clone.toJsonObject());
		assertNotSame(a1.getData(), clone.getData());
	}

	@Test
	public void testFromJsonString() {
		UInt64MultiArray p = UInt64MultiArray.fromJsonString(a1.toString());
		assertEquals(a1.toString(), p.toString());
		assertEquals(a1.toJsonObject(), p.toJsonObject());
		assertEquals(a1.getMessageType(), p.getMessageType());
		assertTrue(Arrays.equals(a1.getData(), p.getData()));
		assertEquals(a1.getLayout(), p.getLayout());
		assertNotSame(a1, p);
		assertNotSame(a1.toString(), p.toString());
		assertNotSame(a1.toJsonObject(), p.toJsonObject());
		assertEquals(a1.size(), p.size());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(a1.toString());
		UInt64MultiArray p = UInt64MultiArray.fromMessage(m);
		assertEquals(a1.toString(), p.toString());
		assertEquals(a1.toJsonObject(), p.toJsonObject());
		assertEquals(a1.getMessageType(), p.getMessageType());
		assertEquals(a1.size(), p.size());
		assertTrue(Arrays.equals(a1.getData(), p.getData()));
		assertEquals(a1.getLayout(), p.getLayout());
		assertNotSame(a1, p);
		assertNotSame(a1.toString(), p.toString());
		assertNotSame(a1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonArrayBuilder array = Json.createArrayBuilder();
		for (long d : a1.getData()) {
			array.add(d);
		}
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(UInt64MultiArray.FIELD_DATA, array.build())
				.add(UInt64MultiArray.FIELD_LAYOUT,
						a1.getLayout().toJsonObject()).build();
		UInt64MultiArray p = UInt64MultiArray.fromJsonObject(jsonObject);
		assertEquals(a1.toString(), p.toString());
		assertEquals(a1.toJsonObject(), p.toJsonObject());
		assertEquals(a1.getMessageType(), p.getMessageType());
		assertTrue(Arrays.equals(a1.getData(), p.getData()));
		assertEquals(a1.getLayout(), p.getLayout());
		assertNotSame(a1, p);
		assertNotSame(a1.toString(), p.toString());
		assertNotSame(a1.toJsonObject(), p.toJsonObject());
		assertEquals(a1.size(), p.size());
	}

	@Test
	public void testFromJsonObjectNoData() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(UInt64MultiArray.FIELD_LAYOUT,
						a1.getLayout().toJsonObject()).build();
		UInt64MultiArray p = UInt64MultiArray.fromJsonObject(jsonObject);
		assertTrue(Arrays.equals(new long[0], p.getData()));
		assertEquals(0, p.size());
		assertEquals(a1.getLayout(), p.getLayout());
	}

	@Test
	public void testFromJsonObjectNoLayout() {
		JsonArrayBuilder array = Json.createArrayBuilder();
		for (long d : a1.getData()) {
			array.add(d);
		}
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(UInt64MultiArray.FIELD_DATA, array.build()).build();
		UInt64MultiArray p = UInt64MultiArray.fromJsonObject(jsonObject);
		assertTrue(Arrays.equals(a1.getData(), p.getData()));
		assertEquals(a1.size(), p.size());
		assertEquals(new MultiArrayLayout(), p.getLayout());
	}
}
