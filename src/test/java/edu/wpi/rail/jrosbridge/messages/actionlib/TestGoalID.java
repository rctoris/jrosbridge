package edu.wpi.rail.jrosbridge.messages.actionlib;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Time;

public class TestGoalID {

	private GoalID empty, g1;

	@Before
	public void setUp() {
		empty = new GoalID();
		g1 = new GoalID(new Time(10, 20), "test");
	}

	@Test
	public void testConstructor() {
		assertEquals(new Time(), empty.getStamp());
		assertEquals("", empty.getID());

		assertEquals("{\"stamp\":{\"secs\":0,\"nsecs\":0},\"id\":\"\"}",
				empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Time(),
				Time.fromJsonObject(empty.toJsonObject().getJsonObject(
						GoalID.FIELD_STAMP)));
		assertEquals("", empty.toJsonObject().getString(GoalID.FIELD_ID));

		assertEquals(GoalID.TYPE, empty.getMessageType());
	}

	@Test
	public void testTimeAndStringConstructor() {
		assertEquals(new Time(10, 20), g1.getStamp());
		assertEquals("test", g1.getID());

		assertEquals("{\"stamp\":{\"secs\":10,\"nsecs\":20},\"id\":\"test\"}",
				g1.toString());

		assertEquals(2, g1.toJsonObject().size());
		assertEquals(
				new Time(10, 20),
				Time.fromJsonObject(g1.toJsonObject().getJsonObject(
						GoalID.FIELD_STAMP)));
		assertEquals("test", g1.toJsonObject().getString(GoalID.FIELD_ID));

		assertEquals(GoalID.TYPE, empty.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(g1.toString().hashCode(), g1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(g1));
		assertFalse(g1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(g1.equals(g1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		GoalID clone = g1.clone();
		assertEquals(g1.toString(), clone.toString());
		assertEquals(g1.toJsonObject(), clone.toJsonObject());
		assertEquals(g1.getMessageType(), clone.getMessageType());
		assertEquals(g1.getStamp(), clone.getStamp());
		assertEquals(g1.getID(), clone.getID());
		assertNotSame(g1, clone);
		assertNotSame(g1.toString(), clone.toString());
		assertNotSame(g1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		GoalID p = GoalID.fromJsonString(g1.toString());
		assertEquals(g1.toString(), p.toString());
		assertEquals(g1.toJsonObject(), p.toJsonObject());
		assertEquals(g1.getMessageType(), p.getMessageType());
		assertEquals(g1.getStamp(), p.getStamp());
		assertEquals(g1.getID(), p.getID());
		assertNotSame(g1, p);
		assertNotSame(g1.toString(), p.toString());
		assertNotSame(g1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(g1.toString());
		GoalID p = GoalID.fromMessage(m);
		assertEquals(g1.toString(), p.toString());
		assertEquals(g1.toJsonObject(), p.toJsonObject());
		assertEquals(g1.getMessageType(), p.getMessageType());
		assertEquals(g1.getStamp(), p.getStamp());
		assertEquals(g1.getID(), p.getID());
		assertNotSame(g1, p);
		assertNotSame(g1.toString(), p.toString());
		assertNotSame(g1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(GoalID.FIELD_STAMP, g1.getStamp().toJsonObject())
				.add(GoalID.FIELD_ID, g1.getID()).build();
		GoalID p = GoalID.fromJsonObject(jsonObject);
		assertEquals(g1.toString(), p.toString());
		assertEquals(g1.toJsonObject(), p.toJsonObject());
		assertEquals(g1.getMessageType(), p.getMessageType());
		assertEquals(g1.getStamp(), p.getStamp());
		assertEquals(g1.getID(), p.getID());
		assertNotSame(g1, p);
		assertNotSame(g1.toString(), p.toString());
		assertNotSame(g1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoStamp() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(GoalID.FIELD_ID, g1.getID()).build();
		GoalID p = GoalID.fromJsonObject(jsonObject);
		assertEquals(new Time(), p.getStamp());
		assertEquals(g1.getID(), p.getID());
	}

	@Test
	public void testFromJsonObjectNoID() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(GoalID.FIELD_STAMP, g1.getStamp().toJsonObject()).build();
		GoalID p = GoalID.fromJsonObject(jsonObject);
		assertEquals(g1.getStamp(), p.getStamp());
		assertEquals("", p.getID());
	}
}
