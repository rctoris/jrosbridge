package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestByte {

	private Byte empty, b1;

	@Before
	public void setUp() {
		empty = new Byte();
		b1 = new Byte((byte) 127);
	}

	@Test
	public void testConstructor() {
		assertEquals((byte) 0, empty.getData());

		assertEquals("{\"data\":0}", empty.toString());

		assertEquals(1, empty.toJsonObject().size());
		assertEquals(0, empty.toJsonObject().getInt(Byte.FIELD_DATA));

		assertEquals(Byte.TYPE, empty.getMessageType());
	}

	@Test
	public void testByteConstructor() {
		assertEquals((byte) 127, b1.getData());

		assertEquals("{\"data\":127}", b1.toString());

		assertEquals(1, b1.toJsonObject().size());
		assertEquals(127, b1.toJsonObject().getInt(Byte.FIELD_DATA));

		assertEquals(Byte.TYPE, b1.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(b1.toString().hashCode(), b1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(b1));
		assertFalse(b1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(b1.equals(b1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Byte clone = b1.clone();
		assertEquals(b1.toString(), clone.toString());
		assertEquals(b1.toJsonObject(), clone.toJsonObject());
		assertEquals(b1.getMessageType(), clone.getMessageType());
		assertEquals(b1.getData(), clone.getData());
		assertNotSame(b1, clone);
		assertNotSame(b1.toString(), clone.toString());
		assertNotSame(b1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		Byte data = Byte.fromJsonString(b1.toString());
		assertEquals(b1.toString(), data.toString());
		assertEquals(b1.toJsonObject(), data.toJsonObject());
		assertEquals(b1.getMessageType(), data.getMessageType());
		assertEquals(b1.getData(), data.getData());
		assertNotSame(b1, data);
		assertNotSame(b1.toString(), data.toString());
		assertNotSame(b1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(b1.toString());
		Byte data = Byte.fromMessage(m);
		assertEquals(b1.toString(), data.toString());
		assertEquals(b1.toJsonObject(), data.toJsonObject());
		assertEquals(b1.getMessageType(), data.getMessageType());
		assertEquals(b1.getData(), data.getData());
		assertNotSame(b1, data);
		assertNotSame(b1.toString(), data.toString());
		assertNotSame(b1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Byte.FIELD_DATA, b1.getData()).build();
		Byte data = Byte.fromJsonObject(jsonObject);
		assertEquals(b1.toString(), data.toString());
		assertEquals(b1.toJsonObject(), data.toJsonObject());
		assertEquals(b1.getMessageType(), data.getMessageType());
		assertEquals(b1.getData(), data.getData());
		assertNotSame(b1, data);
		assertNotSame(b1.toString(), data.toString());
		assertNotSame(b1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoData() {
		JsonObject jsonObject = Json.createObjectBuilder().build();
		Byte data = Byte.fromJsonObject(jsonObject);
		assertEquals((byte) 0, data.getData());
	}
}
