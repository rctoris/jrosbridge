package edu.wpi.rail.jrosbridge.messages.actionlib;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.primitives.Primitive;
import edu.wpi.rail.jrosbridge.primitives.Time;

public class TestGoalStatus {

	private GoalStatus empty, g1;

	@Before
	public void setUp() {
		empty = new GoalStatus();
		g1 = new GoalStatus(new GoalID(new Time(10, 20), "test"), (byte) 30,
				"test2");
	}

	@Test
	public void testConstructor() {
		assertEquals(new GoalID(), empty.getGoalID());
		assertEquals((byte) 0, empty.getStatus());
		assertEquals("", empty.getText());

		assertEquals(
				"{\"goal_id\":{\"stamp\":{\"secs\":0,\"nsecs\":0},\"id\":\"\"},"
						+ "\"status\":0,\"text\":\"\"}", empty.toString());

		assertEquals(3, empty.toJsonObject().size());
		assertEquals(
				new GoalID(),
				GoalID.fromJsonObject(empty.toJsonObject().getJsonObject(
						GoalStatus.FIELD_GOAL_ID)));
		assertEquals(
				(byte) 0,
				Primitive.toUInt8((short) empty.toJsonObject().getInt(
						GoalStatus.FIELD_STATUS)));
		assertEquals("", empty.toJsonObject().getString(GoalStatus.FIELD_TEXT));

		assertEquals(GoalStatus.TYPE, empty.getMessageType());
	}

	@Test
	public void testGoalIDByteAndStringConstructor() {
		assertEquals(new GoalID(new Time(10, 20), "test"), g1.getGoalID());
		assertEquals((byte) 30, g1.getStatus());
		assertEquals("test2", g1.getText());

		assertEquals(
				"{\"goal_id\":{\"stamp\":{\"secs\":10,\"nsecs\":20},\"id\":\"test\"},"
						+ "\"status\":30,\"text\":\"test2\"}", g1.toString());

		assertEquals(3, g1.toJsonObject().size());
		assertEquals(
				new GoalID(new Time(10, 20), "test"),
				GoalID.fromJsonObject(g1.toJsonObject().getJsonObject(
						GoalStatus.FIELD_GOAL_ID)));
		assertEquals(
				(byte) 30,
				Primitive.toUInt8((short) g1.toJsonObject().getInt(
						GoalStatus.FIELD_STATUS)));
		assertEquals("test2", g1.toJsonObject()
				.getString(GoalStatus.FIELD_TEXT));

		assertEquals(GoalStatus.TYPE, g1.getMessageType());
	}

	@Test
	public void testGoalIDByteAndStringConstructorNegative() {
		GoalStatus g = new GoalStatus(new GoalID(), (byte) -1, "");

		assertEquals(new GoalID(), g.getGoalID());
		assertEquals((byte) -1, g.getStatus());
		assertEquals("", g.getText());

		assertEquals(
				"{\"goal_id\":{\"stamp\":{\"secs\":0,\"nsecs\":0},\"id\":\"\"},"
						+ "\"status\":255,\"text\":\"\"}", g.toString());

		assertEquals(3, g.toJsonObject().size());
		assertEquals(
				new GoalID(),
				GoalID.fromJsonObject(g.toJsonObject().getJsonObject(
						GoalStatus.FIELD_GOAL_ID)));
		assertEquals(
				(byte) -1,
				Primitive.toUInt8((short) g.toJsonObject().getInt(
						GoalStatus.FIELD_STATUS)));
		assertEquals("", g.toJsonObject().getString(GoalStatus.FIELD_TEXT));

		assertEquals(GoalStatus.TYPE, g.getMessageType());
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
		GoalStatus clone = g1.clone();
		assertEquals(g1.toString(), clone.toString());
		assertEquals(g1.toJsonObject(), clone.toJsonObject());
		assertEquals(g1.getMessageType(), clone.getMessageType());
		assertEquals(g1.getGoalID(), clone.getGoalID());
		assertEquals(g1.getStatus(), clone.getStatus());
		assertEquals(g1.getText(), clone.getText());
		assertNotSame(g1, clone);
		assertNotSame(g1.toString(), clone.toString());
		assertNotSame(g1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		GoalStatus p = GoalStatus.fromJsonString(g1.toString());
		assertEquals(g1.toString(), p.toString());
		assertEquals(g1.toJsonObject(), p.toJsonObject());
		assertEquals(g1.getMessageType(), p.getMessageType());
		assertEquals(g1.getGoalID(), p.getGoalID());
		assertEquals(g1.getStatus(), p.getStatus());
		assertEquals(g1.getText(), p.getText());
		assertNotSame(g1, p);
		assertNotSame(g1.toString(), p.toString());
		assertNotSame(g1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(g1.toString());
		GoalStatus p = GoalStatus.fromMessage(m);
		assertEquals(g1.toString(), p.toString());
		assertEquals(g1.toJsonObject(), p.toJsonObject());
		assertEquals(g1.getMessageType(), p.getMessageType());
		assertEquals(g1.getGoalID(), p.getGoalID());
		assertEquals(g1.getStatus(), p.getStatus());
		assertEquals(g1.getText(), p.getText());
		assertNotSame(g1, p);
		assertNotSame(g1.toString(), p.toString());
		assertNotSame(g1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(GoalStatus.FIELD_GOAL_ID, g1.getGoalID().toJsonObject())
				.add(GoalStatus.FIELD_STATUS, g1.getStatus())
				.add(GoalStatus.FIELD_TEXT, g1.getText()).build();
		GoalStatus p = GoalStatus.fromJsonObject(jsonObject);
		assertEquals(g1.toString(), p.toString());
		assertEquals(g1.toJsonObject(), p.toJsonObject());
		assertEquals(g1.getMessageType(), p.getMessageType());
		assertEquals(g1.getGoalID(), p.getGoalID());
		assertEquals(g1.getStatus(), p.getStatus());
		assertEquals(g1.getText(), p.getText());
		assertNotSame(g1, p);
		assertNotSame(g1.toString(), p.toString());
		assertNotSame(g1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoGoalID() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(GoalStatus.FIELD_STATUS, g1.getStatus())
				.add(GoalStatus.FIELD_TEXT, g1.getText()).build();
		GoalStatus p = GoalStatus.fromJsonObject(jsonObject);
		assertEquals(new GoalID(), p.getGoalID());
		assertEquals(g1.getStatus(), p.getStatus());
		assertEquals(g1.getText(), p.getText());
	}

	@Test
	public void testFromJsonObjectNoStatus() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(GoalStatus.FIELD_GOAL_ID, g1.getGoalID().toJsonObject())
				.add(GoalStatus.FIELD_TEXT, g1.getText()).build();
		GoalStatus p = GoalStatus.fromJsonObject(jsonObject);
		assertEquals(g1.getGoalID(), p.getGoalID());
		assertEquals((byte) 0, p.getStatus());
		assertEquals(g1.getText(), p.getText());
	}

	@Test
	public void testFromJsonObjectNoText() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(GoalStatus.FIELD_GOAL_ID, g1.getGoalID().toJsonObject())
				.add(GoalStatus.FIELD_STATUS, g1.getStatus()).build();
		GoalStatus p = GoalStatus.fromJsonObject(jsonObject);
		assertEquals(g1.getGoalID(), p.getGoalID());
		assertEquals(g1.getStatus(), p.getStatus());
		assertEquals("", p.getText());
	}
}
