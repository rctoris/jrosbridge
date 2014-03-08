package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;
import edu.wpi.rail.jrosbridge.primitives.Time;

public class TestTransformStamped {

	private TransformStamped empty, t1;

	@Before
	public void setUp() {
		empty = new TransformStamped();
		t1 = new TransformStamped(new Header(123, new Time(10, 20), "test"),
				"test2", new Transform(new Vector3(0.5, 1.5, 3.0),
						new Quaternion(-0.5, -1.5, -3.0, -4.5)));
	}

	@Test
	public void testConstructor() {
		assertEquals(new Header(), empty.getHeader());
		assertEquals("", empty.getChildFrameID());
		assertEquals(new Transform(), empty.getTransform());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":0,\"stamp\":{\"secs\":0,\"nsecs\":0},\"frame_id\":\"\"}"
						+ ",\"child_frame_id\":\"\""
						+ ",\"transform\":{\"translation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
						+ "\"rotation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0,\"w\":0.0}}}",
				empty.toString());

		assertEquals(3, empty.toJsonObject().size());
		assertEquals(
				new Header(),
				Header.fromJsonObject(empty.toJsonObject().getJsonObject(
						TransformStamped.FIELD_HEADER)));
		assertEquals(
				"",
				empty.toJsonObject().getString(
						TransformStamped.FIELD_CHILD_FRAME_ID));
		assertEquals(
				new Transform(),
				Transform.fromJsonObject(empty.toJsonObject().getJsonObject(
						TransformStamped.FIELD_TRANSFORM)));

		assertEquals(TransformStamped.TYPE, empty.getMessageType());
	}

	@Test
	public void testHeaderStringAndTransformConstructor() {
		assertEquals(new Header(123, new Time(10, 20), "test"), t1.getHeader());
		assertEquals("test2", t1.getChildFrameID());
		assertEquals(new Transform(new Vector3(0.5, 1.5, 3.0), new Quaternion(
				-0.5, -1.5, -3.0, -4.5)), t1.getTransform());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":123,\"stamp\":{\"secs\":10,\"nsecs\":20},\"frame_id\":\"test\"}"
						+ ",\"child_frame_id\":\"test2\""
						+ ",\"transform\":{\"translation\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
						+ "\"rotation\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0,\"w\":-4.5}}}",
				t1.toString());

		assertEquals(3, t1.toJsonObject().size());
		assertEquals(
				new Header(123, new Time(10, 20), "test"),
				Header.fromJsonObject(t1.toJsonObject().getJsonObject(
						TransformStamped.FIELD_HEADER)));
		assertEquals(
				"test2",
				t1.toJsonObject().getString(
						TransformStamped.FIELD_CHILD_FRAME_ID));
		assertEquals(new Transform(new Vector3(0.5, 1.5, 3.0), new Quaternion(
				-0.5, -1.5, -3.0, -4.5)),
				Transform.fromJsonObject(t1.toJsonObject().getJsonObject(
						TransformStamped.FIELD_TRANSFORM)));

		assertEquals(TransformStamped.TYPE, t1.getMessageType());
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
		TransformStamped clone = t1.clone();
		assertEquals(t1.toString(), clone.toString());
		assertEquals(t1.toJsonObject(), clone.toJsonObject());
		assertEquals(t1.getMessageType(), clone.getMessageType());
		assertEquals(t1.getHeader(), clone.getHeader());
		assertEquals(t1.getChildFrameID(), clone.getChildFrameID());
		assertEquals(t1.getTransform(), clone.getTransform());
		assertNotSame(t1, clone);
		assertNotSame(t1.toString(), clone.toString());
		assertNotSame(t1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		TransformStamped p = TransformStamped.fromJsonString(t1.toString());
		assertEquals(t1.toString(), p.toString());
		assertEquals(t1.toJsonObject(), p.toJsonObject());
		assertEquals(t1.getMessageType(), p.getMessageType());
		assertEquals(t1.getHeader(), p.getHeader());
		assertEquals(t1.getChildFrameID(), p.getChildFrameID());
		assertEquals(t1.getTransform(), p.getTransform());
		assertNotSame(t1, p);
		assertNotSame(t1.toString(), p.toString());
		assertNotSame(t1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(t1.toString());
		TransformStamped p = TransformStamped.fromMessage(m);
		assertEquals(t1.toString(), p.toString());
		assertEquals(t1.toJsonObject(), p.toJsonObject());
		assertEquals(t1.getMessageType(), p.getMessageType());
		assertEquals(t1.getHeader(), p.getHeader());
		assertEquals(t1.getChildFrameID(), p.getChildFrameID());
		assertEquals(t1.getTransform(), p.getTransform());
		assertNotSame(t1, p);
		assertNotSame(t1.toString(), p.toString());
		assertNotSame(t1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(TransformStamped.FIELD_HEADER,
						t1.getHeader().toJsonObject())
				.add(TransformStamped.FIELD_CHILD_FRAME_ID,
						t1.getChildFrameID())
				.add(TransformStamped.FIELD_TRANSFORM,
						t1.getTransform().toJsonObject()).build();
		TransformStamped p = TransformStamped.fromJsonObject(jsonObject);
		assertEquals(t1.toString(), p.toString());
		assertEquals(t1.toJsonObject(), p.toJsonObject());
		assertEquals(t1.getMessageType(), p.getMessageType());
		assertEquals(t1.getHeader(), p.getHeader());
		assertEquals(t1.getChildFrameID(), p.getChildFrameID());
		assertEquals(t1.getTransform(), p.getTransform());
		assertNotSame(t1, p);
		assertNotSame(t1.toString(), p.toString());
		assertNotSame(t1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoHeader() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(TransformStamped.FIELD_CHILD_FRAME_ID,
						t1.getChildFrameID())
				.add(TransformStamped.FIELD_TRANSFORM,
						t1.getTransform().toJsonObject()).build();
		TransformStamped p = TransformStamped.fromJsonObject(jsonObject);
		assertEquals(new Header(), p.getHeader());
		assertEquals(t1.getChildFrameID(), p.getChildFrameID());
		assertEquals(t1.getTransform(), p.getTransform());
	}

	@Test
	public void testFromJsonObjectNoChildFrameID() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(TransformStamped.FIELD_HEADER,
						t1.getHeader().toJsonObject())
				.add(TransformStamped.FIELD_TRANSFORM,
						t1.getTransform().toJsonObject()).build();
		TransformStamped p = TransformStamped.fromJsonObject(jsonObject);
		assertEquals(t1.getHeader(), p.getHeader());
		assertEquals("", p.getChildFrameID());
		assertEquals(t1.getTransform(), p.getTransform());
	}

	@Test
	public void testFromJsonObjectNoTransform() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(TransformStamped.FIELD_HEADER,
						t1.getHeader().toJsonObject())
				.add(TransformStamped.FIELD_CHILD_FRAME_ID,
						t1.getChildFrameID()).build();
		TransformStamped p = TransformStamped.fromJsonObject(jsonObject);
		assertEquals(t1.getHeader(), p.getHeader());
		assertEquals(t1.getChildFrameID(), p.getChildFrameID());
		assertEquals(new Transform(), p.getTransform());
	}
}
