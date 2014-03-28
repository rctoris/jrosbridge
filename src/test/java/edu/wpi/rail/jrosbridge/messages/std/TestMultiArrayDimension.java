package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

public class TestMultiArrayDimension {

	private MultiArrayDimension empty, mad1;

	@Before
	public void setUp() {
		empty = new MultiArrayDimension();
		mad1 = new MultiArrayDimension("test", 10, 20);
	}

	@Test
	public void testConstructor() {
		assertEquals("", empty.getLabel());
		assertEquals(0, empty.getSize());
		assertEquals(0, empty.getStride());

		assertEquals("{\"label\":\"\",\"size\":0,\"stride\":0}",
				empty.toString());

		assertEquals(3, empty.toJsonObject().size());
		assertEquals("",
				empty.toJsonObject().getString(MultiArrayDimension.FIELD_LABEL));
		assertEquals(
				0,
				Primitive.toUInt32(empty.toJsonObject()
						.getJsonNumber(MultiArrayDimension.FIELD_SIZE)
						.longValue()));
		assertEquals(
				0,
				Primitive.toUInt32(empty.toJsonObject()
						.getJsonNumber(MultiArrayDimension.FIELD_STRIDE)
						.longValue()));

		assertEquals(MultiArrayDimension.TYPE, empty.getMessageType());
	}

	@Test
	public void testStringIntAndIntConstructor() {
		assertEquals("test", mad1.getLabel());
		assertEquals(10, mad1.getSize());
		assertEquals(20, mad1.getStride());

		assertEquals("{\"label\":\"test\",\"size\":10,\"stride\":20}",
				mad1.toString());

		assertEquals(3, mad1.toJsonObject().size());
		assertEquals("test",
				mad1.toJsonObject().getString(MultiArrayDimension.FIELD_LABEL));
		assertEquals(
				10,
				Primitive.toUInt32(mad1.toJsonObject()
						.getJsonNumber(MultiArrayDimension.FIELD_SIZE)
						.longValue()));
		assertEquals(
				20,
				Primitive.toUInt32(mad1.toJsonObject()
						.getJsonNumber(MultiArrayDimension.FIELD_STRIDE)
						.longValue()));

		assertEquals(MultiArrayDimension.TYPE, mad1.getMessageType());
	}

	@Test
	public void testStringIntAndIntConstructorNegative() {
		MultiArrayDimension mad = new MultiArrayDimension("test123", -1, -2);

		assertEquals("test123", mad.getLabel());
		assertEquals(-1, mad.getSize());
		assertEquals(-2, mad.getStride());

		assertEquals(
				"{\"label\":\"test123\",\"size\":4294967295,\"stride\":4294967294}",
				mad.toString());

		assertEquals(3, mad.toJsonObject().size());
		assertEquals("test123",
				mad.toJsonObject().getString(MultiArrayDimension.FIELD_LABEL));
		assertEquals(
				-1,
				Primitive.toUInt32(mad.toJsonObject()
						.getJsonNumber(MultiArrayDimension.FIELD_SIZE)
						.longValue()));
		assertEquals(
				-2,
				Primitive.toUInt32(mad.toJsonObject()
						.getJsonNumber(MultiArrayDimension.FIELD_STRIDE)
						.longValue()));

		assertEquals(MultiArrayDimension.TYPE, mad.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(mad1.toString().hashCode(), mad1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(mad1));
		assertFalse(mad1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(mad1.equals(mad1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		MultiArrayDimension clone = mad1.clone();
		assertEquals(mad1.toString(), clone.toString());
		assertEquals(mad1.toJsonObject(), clone.toJsonObject());
		assertEquals(mad1.getMessageType(), clone.getMessageType());
		assertEquals(mad1.getLabel(), clone.getLabel());
		assertEquals(mad1.getSize(), clone.getSize());
		assertEquals(mad1.getStride(), clone.getStride());
		assertNotSame(mad1, clone);
		assertNotSame(mad1.toString(), clone.toString());
		assertNotSame(mad1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		MultiArrayDimension p = MultiArrayDimension.fromJsonString(mad1
				.toString());
		assertEquals(mad1.toString(), p.toString());
		assertEquals(mad1.toJsonObject(), p.toJsonObject());
		assertEquals(mad1.getMessageType(), p.getMessageType());
		assertEquals(mad1.getLabel(), p.getLabel());
		assertEquals(mad1.getSize(), p.getSize());
		assertEquals(mad1.getStride(), p.getStride());
		assertNotSame(mad1, p);
		assertNotSame(mad1.toString(), p.toString());
		assertNotSame(mad1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(mad1.toString());
		MultiArrayDimension p = MultiArrayDimension.fromMessage(m);
		assertEquals(mad1.toString(), p.toString());
		assertEquals(mad1.toJsonObject(), p.toJsonObject());
		assertEquals(mad1.getMessageType(), p.getMessageType());
		assertEquals(mad1.getLabel(), p.getLabel());
		assertEquals(mad1.getSize(), p.getSize());
		assertEquals(mad1.getStride(), p.getStride());
		assertNotSame(mad1, p);
		assertNotSame(mad1.toString(), p.toString());
		assertNotSame(mad1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(MultiArrayDimension.FIELD_LABEL, mad1.getLabel())
				.add(MultiArrayDimension.FIELD_SIZE, mad1.getSize())
				.add(MultiArrayDimension.FIELD_STRIDE, mad1.getStride())
				.build();
		MultiArrayDimension p = MultiArrayDimension.fromJsonObject(jsonObject);
		assertEquals(mad1.toString(), p.toString());
		assertEquals(mad1.toJsonObject(), p.toJsonObject());
		assertEquals(mad1.getMessageType(), p.getMessageType());
		assertEquals(mad1.getLabel(), p.getLabel());
		assertEquals(mad1.getSize(), p.getSize());
		assertEquals(mad1.getStride(), p.getStride());
		assertNotSame(mad1, p);
		assertNotSame(mad1.toString(), p.toString());
		assertNotSame(mad1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoLabel() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(MultiArrayDimension.FIELD_SIZE, mad1.getSize())
				.add(MultiArrayDimension.FIELD_STRIDE, mad1.getStride())
				.build();
		MultiArrayDimension p = MultiArrayDimension.fromJsonObject(jsonObject);
		assertEquals("", p.getLabel());
		assertEquals(mad1.getSize(), p.getSize());
		assertEquals(mad1.getStride(), p.getStride());
	}

	@Test
	public void testFromJsonObjectNoSize() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(MultiArrayDimension.FIELD_LABEL, mad1.getLabel())
				.add(MultiArrayDimension.FIELD_STRIDE, mad1.getStride())
				.build();
		MultiArrayDimension p = MultiArrayDimension.fromJsonObject(jsonObject);
		assertEquals(mad1.getLabel(), p.getLabel());
		assertEquals(0, p.getSize());
		assertEquals(mad1.getStride(), p.getStride());
	}

	@Test
	public void testFromJsonObjectNoStride() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(MultiArrayDimension.FIELD_LABEL, mad1.getLabel())
				.add(MultiArrayDimension.FIELD_SIZE, mad1.getSize()).build();
		MultiArrayDimension p = MultiArrayDimension.fromJsonObject(jsonObject);
		assertEquals(mad1.getLabel(), p.getLabel());
		assertEquals(mad1.getSize(), p.getSize());
		assertEquals(0, p.getStride());
	}
}
