package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;
import edu.wpi.rail.jrosbridge.primitives.Time;

public class TestWrenchStamped {

	private WrenchStamped empty, w1;

	@Before
	public void setUp() {
		empty = new WrenchStamped();
		w1 = new WrenchStamped(new Header(123, new Time(10, 20), "test"),
				new Wrench(new Vector3(0.5, 1.5, 3.0), new Vector3(-0.5, -1.5,
						-3.0)));
	}

	@Test
	public void testConstructor() {
		assertEquals(new Header(), empty.getHeader());
		assertEquals(new Wrench(), empty.getWrench());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":0,\"stamp\":{\"secs\":0,\"nsecs\":0},\"frame_id\":\"\"}"
						+ ",\"wrench\":{\"force\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
						+ "\"torque\":{\"x\":0.0,\"y\":0.0,\"z\":0.0}}}",
				empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Header(),
				Header.fromJsonObject(empty.toJsonObject().getJsonObject(
						WrenchStamped.FIELD_HEADER)));
		assertEquals(
				new Wrench(),
				Wrench.fromJsonObject(empty.toJsonObject().getJsonObject(
						WrenchStamped.FIELD_WRENCH)));

		assertEquals(WrenchStamped.TYPE, empty.getMessageType());
	}

	@Test
	public void testHeaderAndWrenchConstructor() {
		assertEquals(new Header(123, new Time(10, 20), "test"), w1.getHeader());
		assertEquals(new Wrench(new Vector3(0.5, 1.5, 3.0), new Vector3(-0.5,
				-1.5, -3.0)), w1.getWrench());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":123,\"stamp\":{\"secs\":10,\"nsecs\":20},\"frame_id\":\"test\"}"
						+ ",\"wrench\":{\"force\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
						+ "\"torque\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0}}}",
				w1.toString());

		assertEquals(2, w1.toJsonObject().size());
		assertEquals(
				new Header(123, new Time(10, 20), "test"),
				Header.fromJsonObject(w1.toJsonObject().getJsonObject(
						WrenchStamped.FIELD_HEADER)));
		assertEquals(new Wrench(new Vector3(0.5, 1.5, 3.0), new Vector3(-0.5,
				-1.5, -3.0)), Wrench.fromJsonObject(w1.toJsonObject()
				.getJsonObject(WrenchStamped.FIELD_WRENCH)));

		assertEquals(WrenchStamped.TYPE, w1.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(w1.toString().hashCode(), w1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(w1));
		assertFalse(w1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(w1.equals(w1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		WrenchStamped clone = w1.clone();
		assertEquals(w1.toString(), clone.toString());
		assertEquals(w1.toJsonObject(), clone.toJsonObject());
		assertEquals(w1.getMessageType(), clone.getMessageType());
		assertEquals(w1.getHeader(), clone.getHeader());
		assertEquals(w1.getWrench(), clone.getWrench());
		assertNotSame(w1, clone);
		assertNotSame(w1.toString(), clone.toString());
		assertNotSame(w1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		WrenchStamped p = WrenchStamped.fromJsonString(w1.toString());
		assertEquals(w1.toString(), p.toString());
		assertEquals(w1.toJsonObject(), p.toJsonObject());
		assertEquals(w1.getMessageType(), p.getMessageType());
		assertEquals(w1.getHeader(), p.getHeader());
		assertEquals(w1.getWrench(), p.getWrench());
		assertNotSame(w1, p);
		assertNotSame(w1.toString(), p.toString());
		assertNotSame(w1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(w1.toString());
		WrenchStamped p = WrenchStamped.fromMessage(m);
		assertEquals(w1.toString(), p.toString());
		assertEquals(w1.toJsonObject(), p.toJsonObject());
		assertEquals(w1.getMessageType(), p.getMessageType());
		assertEquals(w1.getHeader(), p.getHeader());
		assertEquals(w1.getWrench(), p.getWrench());
		assertNotSame(w1, p);
		assertNotSame(w1.toString(), p.toString());
		assertNotSame(w1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(WrenchStamped.FIELD_HEADER, w1.getHeader().toJsonObject())
				.add(WrenchStamped.FIELD_WRENCH, w1.getWrench().toJsonObject())
				.build();
		WrenchStamped p = WrenchStamped.fromJsonObject(jsonObject);
		assertEquals(w1.toString(), p.toString());
		assertEquals(w1.toJsonObject(), p.toJsonObject());
		assertEquals(w1.getMessageType(), p.getMessageType());
		assertEquals(w1.getHeader(), p.getHeader());
		assertEquals(w1.getWrench(), p.getWrench());
		assertNotSame(w1, p);
		assertNotSame(w1.toString(), p.toString());
		assertNotSame(w1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoHeader() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(WrenchStamped.FIELD_WRENCH, w1.getWrench().toJsonObject())
				.build();
		WrenchStamped p = WrenchStamped.fromJsonObject(jsonObject);
		assertEquals(new Header(), p.getHeader());
		assertEquals(w1.getWrench(), p.getWrench());
	}

	@Test
	public void testFromJsonObjectNoWrench() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(WrenchStamped.FIELD_HEADER, w1.getHeader().toJsonObject())
				.build();
		WrenchStamped p = WrenchStamped.fromJsonObject(jsonObject);
		assertEquals(w1.getHeader(), p.getHeader());
		assertEquals(new Wrench(), p.getWrench());
	}
}
