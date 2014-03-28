package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestFloat32MultiArray {

	private Float32MultiArray empty, a1;

	@Before
	public void setUp() {
		empty = new Float32MultiArray();
		a1 = new Float32MultiArray(new MultiArrayLayout(
				new MultiArrayDimension[] {
						new MultiArrayDimension("test", 10, 20),
						new MultiArrayDimension("test2", 30, 40) }, 50),
				new float[] { 5.5f, 10.5f });
	}

	@Test
	public void testConstructor() {
		assertEquals(new MultiArrayLayout(), empty.getLayout());
		assertTrue(Arrays.equals(new float[0], empty.getData()));
		assertEquals(0, empty.getData().length);
		assertEquals(0, empty.size());

		assertEquals("{\"layout\":{\"dim\":[],\"data_offset\":0},\"data\":[]}",
				empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(new MultiArrayLayout(),
				MultiArrayLayout.fromJsonObject(empty.toJsonObject()
						.getJsonObject(Float32MultiArray.FIELD_LAYOUT)));
		assertEquals(0,
				empty.toJsonObject().getJsonArray(Float32MultiArray.FIELD_DATA)
						.size());

		assertEquals(Float32MultiArray.TYPE, empty.getMessageType());
	}

	@Test
	public void testMultiArrayLayoutAndFloatArrayConstructor() {
		assertEquals(new MultiArrayLayout(new MultiArrayDimension[] {
				new MultiArrayDimension("test", 10, 20),
				new MultiArrayDimension("test2", 30, 40) }, 50), a1.getLayout());
		assertTrue(Arrays.equals(new float[] { 5.5f, 10.5f }, a1.getData()));
		assertEquals(2, a1.size());
		assertEquals((float) 5.5f, a1.get(0), 0);
		assertEquals((float) 10.5f, a1.get(1), 0);

		assertEquals("{\"layout\":{\"dim\":["
				+ "{\"label\":\"test\",\"size\":10,\"stride\":20},"
				+ "{\"label\":\"test2\",\"size\":30,\"stride\":40}"
				+ "],\"data_offset\":50},\"data\":[5.5,10.5]}", a1.toString());

		assertEquals(2, a1.toJsonObject().size());
		assertEquals(new MultiArrayLayout(new MultiArrayDimension[] {
				new MultiArrayDimension("test", 10, 20),
				new MultiArrayDimension("test2", 30, 40) }, 50),
				MultiArrayLayout.fromJsonObject(a1.toJsonObject()
						.getJsonObject(Float32MultiArray.FIELD_LAYOUT)));
		assertEquals(2,
				a1.toJsonObject().getJsonArray(Float32MultiArray.FIELD_DATA)
						.size());
		assertEquals(
				5.5f,
				(float) a1.toJsonObject()
						.getJsonArray(Float32MultiArray.FIELD_DATA)
						.getJsonNumber(0).doubleValue(), 0);
		assertEquals(
				10.5f,
				(float) a1.toJsonObject()
						.getJsonArray(Float32MultiArray.FIELD_DATA)
						.getJsonNumber(1).doubleValue(), 0);

		assertEquals(Float32MultiArray.TYPE, a1.getMessageType());
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
		Float32MultiArray clone = a1.clone();
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
		Float32MultiArray p = Float32MultiArray.fromJsonString(a1.toString());
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
		Float32MultiArray p = Float32MultiArray.fromMessage(m);
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
		for (float d : a1.getData()) {
			array.add(d);
		}
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(Float32MultiArray.FIELD_DATA, array.build())
				.add(Float32MultiArray.FIELD_LAYOUT,
						a1.getLayout().toJsonObject()).build();
		Float32MultiArray p = Float32MultiArray.fromJsonObject(jsonObject);
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
				.add(Float32MultiArray.FIELD_LAYOUT,
						a1.getLayout().toJsonObject()).build();
		Float32MultiArray p = Float32MultiArray.fromJsonObject(jsonObject);
		assertTrue(Arrays.equals(new float[0], p.getData()));
		assertEquals(0, p.size());
		assertEquals(a1.getLayout(), p.getLayout());
	}

	@Test
	public void testFromJsonObjectNoLayout() {
		JsonArrayBuilder array = Json.createArrayBuilder();
		for (float d : a1.getData()) {
			array.add(d);
		}
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Float32MultiArray.FIELD_DATA, array.build()).build();
		Float32MultiArray p = Float32MultiArray.fromJsonObject(jsonObject);
		assertTrue(Arrays.equals(a1.getData(), p.getData()));
		assertEquals(a1.size(), p.size());
		assertEquals(new MultiArrayLayout(), p.getLayout());
	}
}
