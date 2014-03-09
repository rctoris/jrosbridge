package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;
import edu.wpi.rail.jrosbridge.primitives.Time;

public class TestTwistStamped {

	private TwistStamped empty, t1;

	@Before
	public void setUp() {
		empty = new TwistStamped();
		t1 = new TwistStamped(new Header(123, new Time(10, 20), "test"),
				new Twist(new Vector3(0.5, 1.5, 3.0), new Vector3(-0.5, -1.5,
						-3.0)));
	}

	@Test
	public void testConstructor() {
		assertEquals(new Header(), empty.getHeader());
		assertEquals(new Twist(), empty.getTwist());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":0,\"stamp\":{\"secs\":0,\"nsecs\":0},\"frame_id\":\"\"}"
						+ ",\"twist\":{\"linear\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
						+ "\"angular\":{\"x\":0.0,\"y\":0.0,\"z\":0.0}}}",
				empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Header(),
				Header.fromJsonObject(empty.toJsonObject().getJsonObject(
						TwistStamped.FIELD_HEADER)));
		assertEquals(
				new Twist(),
				Twist.fromJsonObject(empty.toJsonObject().getJsonObject(
						TwistStamped.FIELD_TWIST)));

		assertEquals(TwistStamped.TYPE, empty.getMessageType());
	}

	@Test
	public void testHeaderAndTwistConstructor() {
		assertEquals(new Header(123, new Time(10, 20), "test"), t1.getHeader());
		assertEquals(new Twist(new Vector3(0.5, 1.5, 3.0), new Vector3(-0.5,
				-1.5, -3.0)), t1.getTwist());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":123,\"stamp\":{\"secs\":10,\"nsecs\":20},\"frame_id\":\"test\"}"
						+ ",\"twist\":{\"linear\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
						+ "\"angular\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0}}}",
				t1.toString());

		assertEquals(2, t1.toJsonObject().size());
		assertEquals(
				new Header(123, new Time(10, 20), "test"),
				Header.fromJsonObject(t1.toJsonObject().getJsonObject(
						TwistStamped.FIELD_HEADER)));
		assertEquals(new Twist(new Vector3(0.5, 1.5, 3.0), new Vector3(-0.5,
				-1.5, -3.0)), Twist.fromJsonObject(t1.toJsonObject()
				.getJsonObject(TwistStamped.FIELD_TWIST)));

		assertEquals(TwistStamped.TYPE, t1.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(t1.toString().hashCode(), t1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(t1));
		assertFalse(t1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(t1.equals(t1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		TwistStamped clone = t1.clone();
		assertEquals(t1.toString(), clone.toString());
		assertEquals(t1.toJsonObject(), clone.toJsonObject());
		assertEquals(t1.getMessageType(), clone.getMessageType());
		assertEquals(t1.getHeader(), clone.getHeader());
		assertEquals(t1.getTwist(), clone.getTwist());
		assertNotSame(t1, clone);
		assertNotSame(t1.toString(), clone.toString());
		assertNotSame(t1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		TwistStamped p = TwistStamped.fromJsonString(t1.toString());
		assertEquals(t1.toString(), p.toString());
		assertEquals(t1.toJsonObject(), p.toJsonObject());
		assertEquals(t1.getMessageType(), p.getMessageType());
		assertEquals(t1.getHeader(), p.getHeader());
		assertEquals(t1.getTwist(), p.getTwist());
		assertNotSame(t1, p);
		assertNotSame(t1.toString(), p.toString());
		assertNotSame(t1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(t1.toString());
		TwistStamped p = TwistStamped.fromMessage(m);
		assertEquals(t1.toString(), p.toString());
		assertEquals(t1.toJsonObject(), p.toJsonObject());
		assertEquals(t1.getMessageType(), p.getMessageType());
		assertEquals(t1.getHeader(), p.getHeader());
		assertEquals(t1.getTwist(), p.getTwist());
		assertNotSame(t1, p);
		assertNotSame(t1.toString(), p.toString());
		assertNotSame(t1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(TwistStamped.FIELD_HEADER, t1.getHeader().toJsonObject())
				.add(TwistStamped.FIELD_TWIST, t1.getTwist().toJsonObject())
				.build();
		TwistStamped p = TwistStamped.fromJsonObject(jsonObject);
		assertEquals(t1.toString(), p.toString());
		assertEquals(t1.toJsonObject(), p.toJsonObject());
		assertEquals(t1.getMessageType(), p.getMessageType());
		assertEquals(t1.getHeader(), p.getHeader());
		assertEquals(t1.getTwist(), p.getTwist());
		assertNotSame(t1, p);
		assertNotSame(t1.toString(), p.toString());
		assertNotSame(t1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoHeader() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(TwistStamped.FIELD_TWIST, t1.getTwist().toJsonObject())
				.build();
		TwistStamped p = TwistStamped.fromJsonObject(jsonObject);
		assertEquals(new Header(), p.getHeader());
		assertEquals(t1.getTwist(), p.getTwist());
	}

	@Test
	public void testFromJsonObjectNoTwist() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(TwistStamped.FIELD_HEADER, t1.getHeader().toJsonObject())
				.build();
		TwistStamped p = TwistStamped.fromJsonObject(jsonObject);
		assertEquals(t1.getHeader(), p.getHeader());
		assertEquals(new Twist(), p.getTwist());
	}
}
