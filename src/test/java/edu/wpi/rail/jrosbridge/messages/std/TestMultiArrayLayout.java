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

public class TestMultiArrayLayout {

	private MultiArrayLayout empty, m1;

	@Before
	public void setUp() {
		empty = new MultiArrayLayout();
		m1 = new MultiArrayLayout(new MultiArrayDimension[] {
				new MultiArrayDimension("test", 10, 20),
				new MultiArrayDimension("test2", 30, 40) }, 50);
	}

	@Test
	public void testConstructor() {
		assertTrue(Arrays
				.deepEquals(new MultiArrayDimension[0], empty.getDim()));
		assertEquals(0, empty.size());
		assertEquals(0, empty.getDataOffset());

		assertEquals("{\"dim\":[],\"data_offset\":0}", empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(0,
				empty.toJsonObject().getJsonArray(MultiArrayLayout.FIELD_DIM)
						.size());
		assertEquals(
				0,
				Primitive.toUInt32(empty.toJsonObject().getInt(
						MultiArrayLayout.FIELD_DATA_OFFSET)));

		assertEquals(MultiArrayLayout.TYPE, empty.getMessageType());
	}

	@Test
	public void testMultiArrayDimensionArrayAndIntConstructor() {
		assertTrue(Arrays.deepEquals(new MultiArrayDimension[] {
				new MultiArrayDimension("test", 10, 20),
				new MultiArrayDimension("test2", 30, 40) }, m1.getDim()));
		assertEquals(2, m1.size());
		assertEquals(new MultiArrayDimension("test", 10, 20), m1.get(0));
		assertEquals(new MultiArrayDimension("test2", 30, 40), m1.get(1));
		assertEquals(50, m1.getDataOffset());

		assertEquals("{\"dim\":["
				+ "{\"label\":\"test\",\"size\":10,\"stride\":20},"
				+ "{\"label\":\"test2\",\"size\":30,\"stride\":40}"
				+ "],\"data_offset\":50}", m1.toString());

		assertEquals(2, m1.toJsonObject().size());
		assertEquals(2,
				m1.toJsonObject().getJsonArray(MultiArrayLayout.FIELD_DIM)
						.size());
		assertEquals(
				new MultiArrayDimension("test", 10, 20),
				MultiArrayDimension.fromJsonObject(m1.toJsonObject()
						.getJsonArray(MultiArrayLayout.FIELD_DIM)
						.getJsonObject(0)));
		assertEquals(
				new MultiArrayDimension("test2", 30, 40),
				MultiArrayDimension.fromJsonObject(m1.toJsonObject()
						.getJsonArray(MultiArrayLayout.FIELD_DIM)
						.getJsonObject(1)));
		assertEquals(
				50,
				Primitive.toUInt32(m1.toJsonObject().getInt(
						MultiArrayLayout.FIELD_DATA_OFFSET)));

		assertEquals(MultiArrayLayout.TYPE, m1.getMessageType());
	}

	@Test
	public void testMultiArrayDimensionArrayAndIntConstructorNegative() {
		MultiArrayLayout m = new MultiArrayLayout(new MultiArrayDimension[0],
				-1);

		assertTrue(Arrays.deepEquals(new MultiArrayDimension[0], m.getDim()));
		assertEquals(0, m.size());
		assertEquals(-1, m.getDataOffset());

		assertEquals("{\"dim\":[],\"data_offset\":4294967295}", m.toString());

		assertEquals(2, m.toJsonObject().size());
		assertEquals(0,
				m.toJsonObject().getJsonArray(MultiArrayLayout.FIELD_DIM)
						.size());
		assertEquals(
				-1,
				Primitive.toUInt32(m.toJsonObject().getInt(
						MultiArrayLayout.FIELD_DATA_OFFSET)));

		assertEquals(MultiArrayLayout.TYPE, m.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(m1.toString().hashCode(), m1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(m1));
		assertFalse(m1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(m1.equals(m1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		MultiArrayLayout clone = m1.clone();
		assertEquals(m1.toString(), clone.toString());
		assertEquals(m1.toJsonObject(), clone.toJsonObject());
		assertEquals(m1.getMessageType(), clone.getMessageType());
		assertTrue(Arrays.deepEquals(m1.getDim(), clone.getDim()));
		assertEquals(m1.getDataOffset(), clone.getDataOffset());
		assertEquals(m1.size(), clone.size());
		assertNotSame(m1, clone);
		assertNotSame(m1.toString(), clone.toString());
		assertNotSame(m1.toJsonObject(), clone.toJsonObject());
		assertNotSame(m1.getDim(), clone.getDim());
	}

	@Test
	public void testFromJsonString() {
		MultiArrayLayout p = MultiArrayLayout.fromJsonString(m1.toString());
		assertEquals(m1.toString(), p.toString());
		assertEquals(m1.toJsonObject(), p.toJsonObject());
		assertEquals(m1.getMessageType(), p.getMessageType());
		assertTrue(Arrays.deepEquals(m1.getDim(), p.getDim()));
		assertEquals(m1.getDataOffset(), p.getDataOffset());
		assertNotSame(m1, p);
		assertNotSame(m1.toString(), p.toString());
		assertNotSame(m1.toJsonObject(), p.toJsonObject());
		assertEquals(m1.size(), p.size());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(m1.toString());
		MultiArrayLayout p = MultiArrayLayout.fromMessage(m);
		assertEquals(m1.toString(), p.toString());
		assertEquals(m1.toJsonObject(), p.toJsonObject());
		assertEquals(m1.getMessageType(), p.getMessageType());
		assertEquals(m1.size(), p.size());
		assertTrue(Arrays.deepEquals(m1.getDim(), p.getDim()));
		assertEquals(m1.getDataOffset(), p.getDataOffset());
		assertNotSame(m1, p);
		assertNotSame(m1.toString(), p.toString());
		assertNotSame(m1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonArrayBuilder array = Json.createArrayBuilder();
		for (MultiArrayDimension p : m1.getDim()) {
			array.add(p.toJsonObject());
		}
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(MultiArrayLayout.FIELD_DIM, array.build())
				.add(MultiArrayLayout.FIELD_DATA_OFFSET, m1.getDataOffset())
				.build();
		MultiArrayLayout p = MultiArrayLayout.fromJsonObject(jsonObject);
		assertEquals(m1.toString(), p.toString());
		assertEquals(m1.toJsonObject(), p.toJsonObject());
		assertEquals(m1.getMessageType(), p.getMessageType());
		assertTrue(Arrays.deepEquals(m1.getDim(), p.getDim()));
		assertEquals(m1.getDataOffset(), p.getDataOffset());
		assertNotSame(m1, p);
		assertNotSame(m1.toString(), p.toString());
		assertNotSame(m1.toJsonObject(), p.toJsonObject());
		assertEquals(m1.size(), p.size());
	}

	@Test
	public void testFromJsonObjectNoDim() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(MultiArrayLayout.FIELD_DATA_OFFSET, m1.getDataOffset())
				.build();
		MultiArrayLayout p = MultiArrayLayout.fromJsonObject(jsonObject);
		assertTrue(Arrays.deepEquals(new MultiArrayDimension[] {}, p.getDim()));
		assertEquals(0, p.size());
		assertEquals(m1.getDataOffset(), p.getDataOffset());
	}

	@Test
	public void testFromJsonObjectNoDataOffset() {
		JsonArrayBuilder array = Json.createArrayBuilder();
		for (MultiArrayDimension p : m1.getDim()) {
			array.add(p.toJsonObject());
		}
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(MultiArrayLayout.FIELD_DIM, array.build()).build();
		MultiArrayLayout p = MultiArrayLayout.fromJsonObject(jsonObject);
		assertTrue(Arrays.deepEquals(m1.getDim(), p.getDim()));
		assertEquals(m1.size(), p.size());
		assertEquals(0, p.getDataOffset());
	}
}
