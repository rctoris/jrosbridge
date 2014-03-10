package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;
import edu.wpi.rail.jrosbridge.primitives.Time;

public class TestVector3Stamped {

	private Vector3Stamped empty, v1;

	@Before
	public void setUp() {
		empty = new Vector3Stamped();
		v1 = new Vector3Stamped(new Header(123, new Time(10, 20), "test"),
				new Vector3(0.5, 1.5, 3.0));
	}

	@Test
	public void testConstructor() {
		assertEquals(new Header(), empty.getHeader());
		assertEquals(new Vector3(), empty.getVector3());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":0,\"stamp\":{\"secs\":0,\"nsecs\":0},\"frame_id\":\"\"}"
						+ ",\"vector\":{\"x\":0.0,\"y\":0.0,\"z\":0.0}}",
				empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Header(),
				Header.fromJsonObject(empty.toJsonObject().getJsonObject(
						Vector3Stamped.FIELD_HEADER)));
		assertEquals(
				new Vector3(),
				Vector3.fromJsonObject(empty.toJsonObject().getJsonObject(
						Vector3Stamped.FIELD_VECTOR)));

		assertEquals(Vector3Stamped.TYPE, empty.getMessageType());
	}

	@Test
	public void testHeaderAndVector3Constructor() {
		assertEquals(new Header(123, new Time(10, 20), "test"), v1.getHeader());
		assertEquals(new Vector3(0.5, 1.5, 3.0), v1.getVector3());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":123,\"stamp\":{\"secs\":10,\"nsecs\":20},\"frame_id\":\"test\"}"
						+ ",\"vector\":{\"x\":0.5,\"y\":1.5,\"z\":3.0}}",
				v1.toString());

		assertEquals(2, v1.toJsonObject().size());
		assertEquals(
				new Header(123, new Time(10, 20), "test"),
				Header.fromJsonObject(v1.toJsonObject().getJsonObject(
						Vector3Stamped.FIELD_HEADER)));
		assertEquals(
				new Vector3(0.5, 1.5, 3.0),
				Vector3.fromJsonObject(v1.toJsonObject().getJsonObject(
						Vector3Stamped.FIELD_VECTOR)));

		assertEquals(Vector3Stamped.TYPE, v1.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(v1.toString().hashCode(), v1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(v1));
		assertFalse(v1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(v1.equals(v1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Vector3Stamped clone = v1.clone();
		assertEquals(v1.toString(), clone.toString());
		assertEquals(v1.toJsonObject(), clone.toJsonObject());
		assertEquals(v1.getMessageType(), clone.getMessageType());
		assertEquals(v1.getHeader(), clone.getHeader());
		assertEquals(v1.getVector3(), clone.getVector3());
		assertNotSame(v1, clone);
		assertNotSame(v1.toString(), clone.toString());
		assertNotSame(v1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		Vector3Stamped p = Vector3Stamped.fromJsonString(v1.toString());
		assertEquals(v1.toString(), p.toString());
		assertEquals(v1.toJsonObject(), p.toJsonObject());
		assertEquals(v1.getMessageType(), p.getMessageType());
		assertEquals(v1.getHeader(), p.getHeader());
		assertEquals(v1.getVector3(), p.getVector3());
		assertNotSame(v1, p);
		assertNotSame(v1.toString(), p.toString());
		assertNotSame(v1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(v1.toString());
		Vector3Stamped p = Vector3Stamped.fromMessage(m);
		assertEquals(v1.toString(), p.toString());
		assertEquals(v1.toJsonObject(), p.toJsonObject());
		assertEquals(v1.getMessageType(), p.getMessageType());
		assertEquals(v1.getHeader(), p.getHeader());
		assertEquals(v1.getVector3(), p.getVector3());
		assertNotSame(v1, p);
		assertNotSame(v1.toString(), p.toString());
		assertNotSame(v1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(Vector3Stamped.FIELD_HEADER, v1.getHeader().toJsonObject())
				.add(Vector3Stamped.FIELD_VECTOR,
						v1.getVector3().toJsonObject()).build();
		Vector3Stamped p = Vector3Stamped.fromJsonObject(jsonObject);
		assertEquals(v1.toString(), p.toString());
		assertEquals(v1.toJsonObject(), p.toJsonObject());
		assertEquals(v1.getMessageType(), p.getMessageType());
		assertEquals(v1.getHeader(), p.getHeader());
		assertEquals(v1.getVector3(), p.getVector3());
		assertNotSame(v1, p);
		assertNotSame(v1.toString(), p.toString());
		assertNotSame(v1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoHeader() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(Vector3Stamped.FIELD_VECTOR,
						v1.getVector3().toJsonObject()).build();
		Vector3Stamped p = Vector3Stamped.fromJsonObject(jsonObject);
		assertEquals(new Header(), p.getHeader());
		assertEquals(v1.getVector3(), p.getVector3());
	}

	@Test
	public void testFromJsonObjectNoVector3() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(Vector3Stamped.FIELD_HEADER, v1.getHeader().toJsonObject())
				.build();
		Vector3Stamped p = Vector3Stamped.fromJsonObject(jsonObject);
		assertEquals(v1.getHeader(), p.getHeader());
		assertEquals(new Vector3(), p.getVector3());
	}
}
