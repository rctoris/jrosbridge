package edu.wpi.rail.jrosbridge.messages.actionlib;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;
import edu.wpi.rail.jrosbridge.primitives.Time;

public class TestGoalStatusArray {

	private GoalStatusArray empty, g1;

	@Before
	public void setUp() {
		empty = new GoalStatusArray();
		g1 = new GoalStatusArray(new Header(123, new Time(10, 20), "test"),
				new GoalStatus[] {
						new GoalStatus(new GoalID(new Time(10, 20), "test"),
								(byte) 30, "test2"),
						new GoalStatus(new GoalID(new Time(40, 50), "test3"),
								(byte) 60, "test4") });
	}

	@Test
	public void testConstructor() {
		assertEquals(new Header(), empty.getHeader());
		assertTrue(Arrays
				.deepEquals(new GoalStatus[] {}, empty.getStatusList()));
		assertEquals(0, empty.size());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":0,\"stamp\":{\"secs\":0,\"nsecs\":0},\"frame_id\":\"\"}"
						+ ",\"status_list\":[]}", empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Header(),
				Header.fromJsonObject(empty.toJsonObject().getJsonObject(
						GoalStatusArray.FIELD_HEADER)));
		assertEquals(
				0,
				empty.toJsonObject()
						.getJsonArray(GoalStatusArray.FIELD_STATUS_LIST).size());

		assertEquals(GoalStatusArray.TYPE, empty.getMessageType());
	}

	@Test
	public void testHeaderAndGoalStatusConstructor() {
		assertEquals(new Header(123, new Time(10, 20), "test"), g1.getHeader());
		assertTrue(Arrays.deepEquals(new GoalStatus[] {
				new GoalStatus(new GoalID(new Time(10, 20), "test"), (byte) 30,
						"test2"),
				new GoalStatus(new GoalID(new Time(40, 50), "test3"),
						(byte) 60, "test4") }, g1.getStatusList()));

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":123,\"stamp\":{\"secs\":10,\"nsecs\":20},\"frame_id\":\"test\"}"
						+ ",\"status_list\":[{\"goal_id\":{\"stamp\":{\"secs\":10,\"nsecs\":20},\"id\":\"test\"},\"status\":30,\"text\":\"test2\"},"
						+ "{\"goal_id\":{\"stamp\":{\"secs\":40,\"nsecs\":50},\"id\":\"test3\"},\"status\":60,\"text\":\"test4\"}]}",
				g1.toString());

		assertEquals(2, g1.toJsonObject().size());
		assertEquals(
				new Header(123, new Time(10, 20), "test"),
				Header.fromJsonObject(g1.toJsonObject().getJsonObject(
						GoalStatusArray.FIELD_HEADER)));
		assertEquals(
				2,
				g1.toJsonObject()
						.getJsonArray(GoalStatusArray.FIELD_STATUS_LIST).size());
		assertEquals(new GoalStatus(new GoalID(new Time(10, 20), "test"),
				(byte) 30, "test2"), GoalStatus.fromJsonObject(g1
				.toJsonObject().getJsonArray(GoalStatusArray.FIELD_STATUS_LIST)
				.getJsonObject(0)));
		assertEquals(new GoalStatus(new GoalID(new Time(40, 50), "test3"),
				(byte) 60, "test4"), GoalStatus.fromJsonObject(g1
				.toJsonObject().getJsonArray(GoalStatusArray.FIELD_STATUS_LIST)
				.getJsonObject(1)));

		assertEquals(GoalStatusArray.TYPE, g1.getMessageType());
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
		GoalStatusArray clone = g1.clone();
		assertEquals(g1.toString(), clone.toString());
		assertEquals(g1.toJsonObject(), clone.toJsonObject());
		assertEquals(g1.getMessageType(), clone.getMessageType());
		assertEquals(g1.getHeader(), clone.getHeader());
		assertTrue(Arrays.deepEquals(g1.getStatusList(), clone.getStatusList()));
		assertEquals(g1.size(), clone.size());
		assertNotSame(g1, clone);
		assertNotSame(g1.toString(), clone.toString());
		assertNotSame(g1.toJsonObject(), clone.toJsonObject());
		assertNotSame(g1.getStatusList(), clone.getStatusList());
	}

	@Test
	public void testFromJsonString() {
		GoalStatusArray p = GoalStatusArray.fromJsonString(g1.toString());
		assertEquals(g1.toString(), p.toString());
		assertEquals(g1.toJsonObject(), p.toJsonObject());
		assertEquals(g1.getMessageType(), p.getMessageType());
		assertEquals(g1.getHeader(), p.getHeader());
		assertTrue(Arrays.deepEquals(g1.getStatusList(), p.getStatusList()));
		assertNotSame(g1, p);
		assertNotSame(g1.toString(), p.toString());
		assertNotSame(g1.toJsonObject(), p.toJsonObject());
		assertEquals(g1.size(), p.size());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(g1.toString());
		GoalStatusArray p = GoalStatusArray.fromMessage(m);
		assertEquals(g1.toString(), p.toString());
		assertEquals(g1.toJsonObject(), p.toJsonObject());
		assertEquals(g1.getMessageType(), p.getMessageType());
		assertEquals(g1.getHeader(), p.getHeader());
		assertTrue(Arrays.deepEquals(g1.getStatusList(), p.getStatusList()));
		assertNotSame(g1, p);
		assertNotSame(g1.toString(), p.toString());
		assertNotSame(g1.toJsonObject(), p.toJsonObject());
		assertEquals(g1.size(), p.size());
		assertEquals(g1.get(0), p.get(0));
		assertEquals(g1.get(1), p.get(1));
	}

	@Test
	public void testFromJsonObject() {
		JsonArrayBuilder array = Json.createArrayBuilder();
		for (GoalStatus p : g1.getStatusList()) {
			array.add(p.toJsonObject());
		}
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(GoalStatusArray.FIELD_HEADER,
						g1.getHeader().toJsonObject())
				.add(GoalStatusArray.FIELD_STATUS_LIST, array.build()).build();
		GoalStatusArray p = GoalStatusArray.fromJsonObject(jsonObject);
		assertEquals(g1.toString(), p.toString());
		assertEquals(g1.toJsonObject(), p.toJsonObject());
		assertEquals(g1.getMessageType(), p.getMessageType());
		assertEquals(g1.size(), p.size());
		assertEquals(g1.getHeader(), p.getHeader());
		assertTrue(Arrays.deepEquals(g1.getStatusList(), p.getStatusList()));
		assertNotSame(g1, p);
		assertNotSame(g1.toString(), p.toString());
		assertNotSame(g1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoHeader() {
		JsonArrayBuilder array = Json.createArrayBuilder();
		for (GoalStatus p : g1.getStatusList()) {
			array.add(p.toJsonObject());
		}
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(GoalStatusArray.FIELD_STATUS_LIST, array.build()).build();
		GoalStatusArray p = GoalStatusArray.fromJsonObject(jsonObject);
		assertEquals(g1.getMessageType(), p.getMessageType());
		assertEquals(g1.size(), p.size());
		assertEquals(new Header(), p.getHeader());
		assertTrue(Arrays.deepEquals(g1.getStatusList(), p.getStatusList()));
		assertNotSame(g1, p);
		assertNotSame(g1.toString(), p.toString());
		assertNotSame(g1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoStatusList() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(GoalStatusArray.FIELD_HEADER,
						g1.getHeader().toJsonObject()).build();
		GoalStatusArray p = GoalStatusArray.fromJsonObject(jsonObject);
		assertEquals(g1.getHeader(), p.getHeader());
		assertTrue(Arrays.deepEquals(new GoalStatus[] {}, p.getStatusList()));
		assertEquals(0, p.size());
	}
}
