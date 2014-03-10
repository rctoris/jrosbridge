package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;

public class TestHeader {

	private Header empty, h1;

	@Before
	public void setUp() {
		empty = new Header();
		h1 = new Header(5, new edu.wpi.rail.jrosbridge.primitives.Time(10, 20),
				"test");
	}

	@Test
	public void testConstructor() {
		assertEquals(0, empty.getSeq());
		assertEquals(new edu.wpi.rail.jrosbridge.primitives.Time(),
				empty.getStamp());
		assertEquals("", empty.getFrameID());

		assertEquals(
				"{\"seq\":0,\"stamp\":{\"secs\":0,\"nsecs\":0},\"frame_id\":\"\"}",
				empty.toString());

		assertEquals(3, empty.toJsonObject().size());
		assertEquals(
				0,
				Primitive.toUInt32(empty.toJsonObject()
						.getJsonNumber(Header.FIELD_SEQ).longValue()));
		assertEquals(new edu.wpi.rail.jrosbridge.primitives.Time(),
				edu.wpi.rail.jrosbridge.primitives.Time.fromJsonObject(empty
						.toJsonObject().getJsonObject(Header.FIELD_STAMP)));
		assertEquals("", empty.toJsonObject().getString(Header.FIELD_FRAME_ID));

		assertEquals(Header.TYPE, empty.getMessageType());
	}

	@Test
	public void testIntTimeAndStringConstructor() {
		assertEquals(5, h1.getSeq());
		assertEquals(new edu.wpi.rail.jrosbridge.primitives.Time(10, 20),
				h1.getStamp());
		assertEquals("test", h1.getFrameID());

		assertEquals(
				"{\"seq\":5,\"stamp\":{\"secs\":10,\"nsecs\":20},\"frame_id\":\"test\"}",
				h1.toString());

		assertEquals(3, h1.toJsonObject().size());
		assertEquals(
				5,
				Primitive.toUInt32(h1.toJsonObject()
						.getJsonNumber(Header.FIELD_SEQ).longValue()));
		assertEquals(new edu.wpi.rail.jrosbridge.primitives.Time(10, 20),
				edu.wpi.rail.jrosbridge.primitives.Time.fromJsonObject(h1
						.toJsonObject().getJsonObject(Header.FIELD_STAMP)));
		assertEquals("test", h1.toJsonObject().getString(Header.FIELD_FRAME_ID));

		assertEquals(Header.TYPE, h1.getMessageType());
	}

	@Test
	public void testIntTimeAndStringConstructorNegative() {
		Header h = new Header(-1, new edu.wpi.rail.jrosbridge.primitives.Time(
				10, 20), "test");

		assertEquals(-1, h.getSeq());
		assertEquals(new edu.wpi.rail.jrosbridge.primitives.Time(10, 20),
				h.getStamp());
		assertEquals("test", h.getFrameID());

		assertEquals(
				"{\"seq\":4294967295,\"stamp\":{\"secs\":10,\"nsecs\":20},\"frame_id\":\"test\"}",
				h.toString());

		assertEquals(3, h.toJsonObject().size());
		assertEquals(
				-1,
				Primitive.toUInt32(h.toJsonObject()
						.getJsonNumber(Header.FIELD_SEQ).longValue()));
		assertEquals(new edu.wpi.rail.jrosbridge.primitives.Time(10, 20),
				edu.wpi.rail.jrosbridge.primitives.Time.fromJsonObject(h
						.toJsonObject().getJsonObject(Header.FIELD_STAMP)));
		assertEquals("test", h.toJsonObject().getString(Header.FIELD_FRAME_ID));

		assertEquals(Header.TYPE, h.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(h1.toString().hashCode(), h1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(h1));
		assertFalse(h1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(h1.equals(h1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Header clone = h1.clone();
		assertEquals(h1.toString(), clone.toString());
		assertEquals(h1.toJsonObject(), clone.toJsonObject());
		assertEquals(h1.getMessageType(), clone.getMessageType());
		assertEquals(h1.getSeq(), clone.getSeq());
		assertEquals(h1.getStamp(), clone.getStamp());
		assertEquals(h1.getFrameID(), clone.getFrameID());
		assertNotSame(h1, clone);
		assertNotSame(h1.toString(), clone.toString());
		assertNotSame(h1.toJsonObject(), clone.toJsonObject());
		assertNotSame(h1.getStamp(), clone.getStamp());
	}

	@Test
	public void testFromJsonString() {
		Header p = Header.fromJsonString(h1.toString());
		assertEquals(h1.toString(), p.toString());
		assertEquals(h1.toJsonObject(), p.toJsonObject());
		assertEquals(h1.getMessageType(), p.getMessageType());
		assertEquals(h1.getSeq(), p.getSeq());
		assertEquals(h1.getStamp(), p.getStamp());
		assertEquals(h1.getFrameID(), p.getFrameID());
		assertNotSame(h1, p);
		assertNotSame(h1.toString(), p.toString());
		assertNotSame(h1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(h1.toString());
		Header p = Header.fromMessage(m);
		assertEquals(h1.toString(), p.toString());
		assertEquals(h1.toJsonObject(), p.toJsonObject());
		assertEquals(h1.getMessageType(), p.getMessageType());
		assertEquals(h1.getSeq(), p.getSeq());
		assertEquals(h1.getStamp(), p.getStamp());
		assertEquals(h1.getFrameID(), p.getFrameID());
		assertNotSame(h1, p);
		assertNotSame(h1.toString(), p.toString());
		assertNotSame(h1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Header.FIELD_SEQ, h1.getSeq())
				.add(Header.FIELD_STAMP, h1.getStamp().toJsonObject())
				.add(Header.FIELD_FRAME_ID, h1.getFrameID()).build();
		Header p = Header.fromJsonObject(jsonObject);
		assertEquals(h1.toString(), p.toString());
		assertEquals(h1.toJsonObject(), p.toJsonObject());
		assertEquals(h1.getMessageType(), p.getMessageType());
		assertEquals(h1.getSeq(), p.getSeq());
		assertEquals(h1.getStamp(), p.getStamp());
		assertEquals(h1.getFrameID(), p.getFrameID());
		assertNotSame(h1, p);
		assertNotSame(h1.toString(), p.toString());
		assertNotSame(h1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoSeq() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Header.FIELD_STAMP, h1.getStamp().toJsonObject())
				.add(Header.FIELD_FRAME_ID, h1.getFrameID()).build();
		Header p = Header.fromJsonObject(jsonObject);
		assertEquals(0, p.getSeq());
		assertEquals(h1.getStamp(), p.getStamp());
		assertEquals(h1.getFrameID(), p.getFrameID());
	}

	@Test
	public void testFromJsonObjectNoStamp() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Header.FIELD_SEQ, h1.getSeq())
				.add(Header.FIELD_FRAME_ID, h1.getFrameID()).build();
		Header p = Header.fromJsonObject(jsonObject);
		assertEquals(h1.getSeq(), p.getSeq());
		assertEquals(new edu.wpi.rail.jrosbridge.primitives.Time(),
				p.getStamp());
		assertEquals(h1.getFrameID(), p.getFrameID());
	}

	@Test
	public void testFromJsonObjectNoFrameID() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Header.FIELD_SEQ, h1.getSeq())
				.add(Header.FIELD_STAMP, h1.getStamp().toJsonObject()).build();
		Header p = Header.fromJsonObject(jsonObject);
		assertEquals(h1.getSeq(), p.getSeq());
		assertEquals(h1.getStamp(), p.getStamp());
		assertEquals("", p.getFrameID());
	}
}
