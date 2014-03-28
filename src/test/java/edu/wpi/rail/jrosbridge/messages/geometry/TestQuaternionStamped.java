package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;
import edu.wpi.rail.jrosbridge.primitives.Time;

public class TestQuaternionStamped {

	private QuaternionStamped empty, p1;

	@Before
	public void setUp() {
		empty = new QuaternionStamped();
		p1 = new QuaternionStamped(new Header(123, new Time(10, 20), "test"),
				new Quaternion(0.5, 1.5, 3.0, 4.5));
	}

	@Test
	public void testConstructor() {
		assertEquals(new Header(), empty.getHeader());
		assertEquals(new Quaternion(), empty.getQuaternion());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":0,\"stamp\":{\"secs\":0,\"nsecs\":0},\"frame_id\":\"\"}"
						+ ",\"quaternion\":{\"x\":0.0,\"y\":0.0,\"z\":0.0,\"w\":0.0}}",
				empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Header(),
				Header.fromJsonObject(empty.toJsonObject().getJsonObject(
						QuaternionStamped.FIELD_HEADER)));
		assertEquals(
				new Quaternion(),
				Quaternion.fromJsonObject(empty.toJsonObject().getJsonObject(
						QuaternionStamped.FIELD_QUATERNION)));

		assertEquals(QuaternionStamped.TYPE, empty.getMessageType());
	}

	@Test
	public void testHeaderAndQuaternionConstructor() {
		assertEquals(new Header(123, new Time(10, 20), "test"), p1.getHeader());
		assertEquals(new Quaternion(0.5, 1.5, 3.0, 4.5), p1.getQuaternion());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":123,\"stamp\":{\"secs\":10,\"nsecs\":20},\"frame_id\":\"test\"}"
						+ ",\"quaternion\":{\"x\":0.5,\"y\":1.5,\"z\":3.0,\"w\":4.5}}",
				p1.toString());

		assertEquals(2, p1.toJsonObject().size());
		assertEquals(
				new Header(123, new Time(10, 20), "test"),
				Header.fromJsonObject(p1.toJsonObject().getJsonObject(
						QuaternionStamped.FIELD_HEADER)));
		assertEquals(
				new Quaternion(0.5, 1.5, 3.0, 4.5),
				Quaternion.fromJsonObject(p1.toJsonObject().getJsonObject(
						QuaternionStamped.FIELD_QUATERNION)));

		assertEquals(QuaternionStamped.TYPE, p1.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(p1.toString().hashCode(), p1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(p1));
		assertFalse(p1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(p1.equals(p1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		QuaternionStamped clone = p1.clone();
		assertEquals(p1.toString(), clone.toString());
		assertEquals(p1.toJsonObject(), clone.toJsonObject());
		assertEquals(p1.getMessageType(), clone.getMessageType());
		assertEquals(p1.getHeader(), clone.getHeader());
		assertEquals(p1.getQuaternion(), clone.getQuaternion());
		assertNotSame(p1, clone);
		assertNotSame(p1.toString(), clone.toString());
		assertNotSame(p1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		QuaternionStamped p = QuaternionStamped.fromJsonString(p1.toString());
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getHeader(), p.getHeader());
		assertEquals(p1.getQuaternion(), p.getQuaternion());
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(p1.toString());
		QuaternionStamped p = QuaternionStamped.fromMessage(m);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getHeader(), p.getHeader());
		assertEquals(p1.getQuaternion(), p.getQuaternion());
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(QuaternionStamped.FIELD_HEADER,
						p1.getHeader().toJsonObject())
				.add(QuaternionStamped.FIELD_QUATERNION,
						p1.getQuaternion().toJsonObject()).build();
		QuaternionStamped p = QuaternionStamped.fromJsonObject(jsonObject);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getHeader(), p.getHeader());
		assertEquals(p1.getQuaternion(), p.getQuaternion());
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoHeader() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(QuaternionStamped.FIELD_QUATERNION,
						p1.getQuaternion().toJsonObject()).build();
		QuaternionStamped p = QuaternionStamped.fromJsonObject(jsonObject);
		assertEquals(new Header(), p.getHeader());
		assertEquals(p1.getQuaternion(), p.getQuaternion());
	}

	@Test
	public void testFromJsonObjectNoQuaternion() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(QuaternionStamped.FIELD_HEADER,
						p1.getHeader().toJsonObject()).build();
		QuaternionStamped p = QuaternionStamped.fromJsonObject(jsonObject);
		assertEquals(p1.getHeader(), p.getHeader());
		assertEquals(new Quaternion(), p.getQuaternion());
	}
}
