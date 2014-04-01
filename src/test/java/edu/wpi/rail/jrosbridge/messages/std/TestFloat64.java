package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestFloat64 {

	private Float64 empty, f1;

	@Before
	public void setUp() {
		empty = new Float64();
		f1 = new Float64(1.5);
	}

	@Test
	public void testConstructor() {
		assertEquals(0.0, empty.getData(), 0);

		assertEquals("{\"data\":0.0}", empty.toString());

		assertEquals(1, empty.toJsonObject().size());
		assertEquals(0.0, empty.toJsonObject()
				.getJsonNumber(Float64.FIELD_DATA).doubleValue(), 0);

		assertEquals(Float64.TYPE, empty.getMessageType());
	}

	@Test
	public void testDoubleConstructor() {
		assertEquals(1.5, f1.getData(), 0);

		assertEquals("{\"data\":1.5}", f1.toString());

		assertEquals(1, f1.toJsonObject().size());
		assertEquals(1.5, f1.toJsonObject().getJsonNumber(Float64.FIELD_DATA)
				.doubleValue(), 0);

		assertEquals(Float64.TYPE, f1.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(f1.toString().hashCode(), f1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(f1));
		assertFalse(f1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(f1.equals(f1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Float64 clone = f1.clone();
		assertEquals(f1.toString(), clone.toString());
		assertEquals(f1.toJsonObject(), clone.toJsonObject());
		assertEquals(f1.getMessageType(), clone.getMessageType());
		assertEquals(f1.getData(), clone.getData(), 0);
		assertNotSame(f1, clone);
		assertNotSame(f1.toString(), clone.toString());
		assertNotSame(f1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		Float64 data = Float64.fromJsonString(f1.toString());
		assertEquals(f1.toString(), data.toString());
		assertEquals(f1.toJsonObject(), data.toJsonObject());
		assertEquals(f1.getMessageType(), data.getMessageType());
		assertEquals(f1.getData(), data.getData(), 0);
		assertNotSame(f1, data);
		assertNotSame(f1.toString(), data.toString());
		assertNotSame(f1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(f1.toString());
		Float64 data = Float64.fromMessage(m);
		assertEquals(f1.toString(), data.toString());
		assertEquals(f1.toJsonObject(), data.toJsonObject());
		assertEquals(f1.getMessageType(), data.getMessageType());
		assertEquals(f1.getData(), data.getData(), 0);
		assertNotSame(f1, data);
		assertNotSame(f1.toString(), data.toString());
		assertNotSame(f1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Float64.FIELD_DATA, f1.getData()).build();
		Float64 data = Float64.fromJsonObject(jsonObject);
		assertEquals(f1.toString(), data.toString());
		assertEquals(f1.toJsonObject(), data.toJsonObject());
		assertEquals(f1.getMessageType(), data.getMessageType());
		assertEquals(f1.getData(), data.getData(), 0);
		assertNotSame(f1, data);
		assertNotSame(f1.toString(), data.toString());
		assertNotSame(f1.toJsonObject(), data.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoData() {
		JsonObject jsonObject = Json.createObjectBuilder().build();
		Float64 data = Float64.fromJsonObject(jsonObject);
		assertEquals(0.0, data.getData(), 0);
	}
}
