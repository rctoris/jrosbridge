package edu.wpi.rail.jrosbridge.primitives;

import static org.junit.Assert.*;

import java.math.BigInteger;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.JsonWrapper;

public class TestPrimitive {

	private Primitive p1, p2;

	@Before
	public void setUp() {
		p1 = new DummyPrimitive("{\"test\" : 123, \"test2\" : \"abc\"}", "type");
		p2 = new DummyPrimitive(Json.createObjectBuilder().add("test", 123)
				.add("test2", "abc").build(), "type");
	}

	@Test
	public void testStringAndStringConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", p1.toString());
		assertEquals(2, p1.toJsonObject().size());
		assertEquals(123, p1.toJsonObject().getInt("test"));
		assertEquals("abc", p1.toJsonObject().getString("test2"));
		assertEquals("type", p1.getPrimitiveType());
		assertNull(p1.clone());
	}

	@Test
	public void testJsonObjectAndStringConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", p2.toString());
		assertEquals(2, p2.toJsonObject().size());
		assertEquals(123, p2.toJsonObject().getInt("test"));
		assertEquals("abc", p2.toJsonObject().getString("test2"));
		assertEquals("type", p2.getPrimitiveType());
		assertNull(p2.clone());
	}

	@Test
	public void testSetPrimitiveType() {
		p1.setPrimitiveType("test");
		assertEquals("test", p1.getPrimitiveType());
	}

	@Test
	public void testHashCode() {
		assertEquals(p1.toString().hashCode(), p1.hashCode());
		assertEquals(p2.toString().hashCode(), p2.hashCode());
	}

	@Test
	public void testEquals() {
		assertTrue(p1.equals(p2));
		assertTrue(p2.equals(p1));
		assertTrue(p1.equals(p1));
		assertTrue(p2.equals(p2));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(p1.equals(new String(p1.toString())));
	}

	@Test
	public void testToUInt8() {
		assertEquals((byte) 0, Primitive.toUInt8((short) 0));
		assertEquals((byte) 5, Primitive.toUInt8((short) 5));
		assertEquals((byte) 10, Primitive.toUInt8((short) 10));
		assertEquals((byte) 15, Primitive.toUInt8((short) 15));
		assertEquals((byte) -1, Primitive.toUInt8(Short.MAX_VALUE));
	}

	@Test
	public void testToUInt8Array() {
		byte[] values = Primitive.toUInt8(new short[] { 0, 5, 10, 15,
				Short.MAX_VALUE });
		assertEquals((byte) 0, values[0]);
		assertEquals((byte) 5, values[1]);
		assertEquals((byte) 10, values[2]);
		assertEquals((byte) 15, values[3]);
		assertEquals((byte) -1, values[4]);
	}

	@Test
	public void testFromUInt8() {
		assertEquals((short) 0, Primitive.fromUInt8((byte) 0));
		assertEquals((short) 5, Primitive.fromUInt8((byte) 5));
		assertEquals((short) 10, Primitive.fromUInt8((byte) 10));
		assertEquals((short) 15, Primitive.fromUInt8((byte) 15));
		assertEquals((short) 255, Primitive.fromUInt8((byte) -1));
	}

	@Test
	public void testFromUInt8Array() {
		short[] values = Primitive.fromUInt8(new byte[] { 0, 5, 10, 15, -1 });
		assertEquals((short) 0, values[0]);
		assertEquals((short) 5, values[1]);
		assertEquals((short) 10, values[2]);
		assertEquals((short) 15, values[3]);
		assertEquals((short) 255, values[4]);
	}

	@Test
	public void testToUInt16() {
		assertEquals((short) 0, Primitive.toUInt16(0));
		assertEquals((short) 5, Primitive.toUInt16(5));
		assertEquals((short) 10, Primitive.toUInt16(10));
		assertEquals((short) 15, Primitive.toUInt16(15));
		assertEquals((short) -1, Primitive.toUInt16(Integer.MAX_VALUE));
	}

	@Test
	public void testToUInt16Array() {
		short[] values = Primitive.toUInt16(new int[] { 0, 5, 10, 15,
				Integer.MAX_VALUE });
		assertEquals((short) 0, values[0]);
		assertEquals((short) 5, values[1]);
		assertEquals((short) 10, values[2]);
		assertEquals((short) 15, values[3]);
		assertEquals((short) -1, values[4]);
	}

	@Test
	public void testFromUInt16() {
		assertEquals(0, Primitive.fromUInt16((short) 0));
		assertEquals(5, Primitive.fromUInt16((short) 5));
		assertEquals(10, Primitive.fromUInt16((short) 10));
		assertEquals(15, Primitive.fromUInt16((short) 15));
		assertEquals(65535, Primitive.fromUInt16((short) -1));
	}

	@Test
	public void testFromUInt16Array() {
		int[] values = Primitive.fromUInt16(new short[] { 0, 5, 10, 15, -1 });
		assertEquals(0, values[0]);
		assertEquals(5, values[1]);
		assertEquals(10, values[2]);
		assertEquals(15, values[3]);
		assertEquals(65535, values[4]);
	}

	@Test
	public void testToUInt32() {
		assertEquals(0, Primitive.toUInt32(0l));
		assertEquals(5, Primitive.toUInt32(5l));
		assertEquals(10, Primitive.toUInt32(10l));
		assertEquals(15, Primitive.toUInt32(15l));
		assertEquals(-1, Primitive.toUInt32(Long.MAX_VALUE));
	}

	@Test
	public void testToUInt32Array() {
		int[] values = Primitive.toUInt32(new long[] { 0, 5, 10, 15,
				Long.MAX_VALUE });
		assertEquals(0, values[0]);
		assertEquals(5, values[1]);
		assertEquals(10, values[2]);
		assertEquals(15, values[3]);
		assertEquals(-1, values[4]);
	}

	@Test
	public void testFromUInt32() {
		assertEquals(0l, Primitive.fromUInt32(0));
		assertEquals(5l, Primitive.fromUInt32(5));
		assertEquals(10l, Primitive.fromUInt32(10));
		assertEquals(15l, Primitive.fromUInt32(15));
		assertEquals(4294967295l, Primitive.fromUInt32(-1));
	}

	@Test
	public void testFromUInt32Array() {
		long[] values = Primitive.fromUInt32(new int[] { 0, 5, 10, 15, -1 });
		assertEquals(0l, values[0]);
		assertEquals(5l, values[1]);
		assertEquals(10l, values[2]);
		assertEquals(15l, values[3]);
		assertEquals(4294967295l, values[4]);
	}

	@Test
	public void testToUInt64() {
		assertEquals(0l, Primitive.toUInt64(BigInteger.valueOf(0l)));
		assertEquals(5l, Primitive.toUInt64(BigInteger.valueOf(5l)));
		assertEquals(10l, Primitive.toUInt64(BigInteger.valueOf(10l)));
		assertEquals(15l, Primitive.toUInt64(BigInteger.valueOf(15l)));
		assertEquals(-1l,
				Primitive.toUInt64(new BigInteger("18446744073709551615")));
	}

	@Test
	public void testToUInt64Array() {
		long[] values = Primitive.toUInt64(new BigInteger[] {
				BigInteger.valueOf(0l), BigInteger.valueOf(5l),
				BigInteger.valueOf(10l), BigInteger.valueOf(15l),
				new BigInteger("18446744073709551615") });
		assertEquals(0l, values[0]);
		assertEquals(5l, values[1]);
		assertEquals(10l, values[2]);
		assertEquals(15l, values[3]);
		assertEquals(-1l, values[4]);
	}

	@Test
	public void testFromUInt64() {
		assertEquals(BigInteger.valueOf(0l), Primitive.fromUInt64(0l));
		assertEquals(BigInteger.valueOf(5l), Primitive.fromUInt64(5l));
		assertEquals(BigInteger.valueOf(10l), Primitive.fromUInt64(10l));
		assertEquals(BigInteger.valueOf(15l), Primitive.fromUInt64(15l));
		assertEquals(new BigInteger("18446744073709551615"),
				Primitive.fromUInt64(-1l));
	}

	@Test
	public void testFromUInt64Array() {
		BigInteger[] values = Primitive.fromUInt64(new long[] { 0l, 5l, 10l,
				15l, -1l });
		assertEquals(BigInteger.valueOf(0l), values[0]);
		assertEquals(BigInteger.valueOf(5l), values[1]);
		assertEquals(BigInteger.valueOf(10l), values[2]);
		assertEquals(BigInteger.valueOf(15l), values[3]);
		assertEquals(new BigInteger("18446744073709551615"), values[4]);
	}

	private class DummyPrimitive extends Primitive {

		public DummyPrimitive(String jsonString, String primitiveType) {
			super(jsonString, primitiveType);
		}

		public DummyPrimitive(JsonObject jsonObject, String primitiveType) {
			super(jsonObject, primitiveType);
		}

		public JsonWrapper clone() {
			return null;
		}

	}
}
