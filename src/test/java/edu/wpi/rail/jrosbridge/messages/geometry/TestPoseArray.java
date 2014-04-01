package edu.wpi.rail.jrosbridge.messages.geometry;

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

public class TestPoseArray {

	private PoseArray empty, p1;

	@Before
	public void setUp() {
		empty = new PoseArray();
		p1 = new PoseArray(new Header(123, new Time(10, 20), "test"),
				new Pose[] {
						new Pose(new Point(0.5, 1.5, 3.0), new Quaternion(-0.5,
								-1.5, -3.0, -4.5)),
						new Pose(new Point(-0.5, -1.5, -3.0), new Quaternion(
								0.5, 1.5, 3.0, 4.5)) });
	}

	@Test
	public void testConstructor() {
		assertEquals(new Header(), empty.getHeader());
		assertTrue(Arrays.deepEquals(new Pose[] {}, empty.getPoses()));
		assertEquals(0, empty.size());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":0,\"stamp\":{\"secs\":0,\"nsecs\":0},\"frame_id\":\"\"}"
						+ ",\"poses\":[]}", empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Header(),
				Header.fromJsonObject(empty.toJsonObject().getJsonObject(
						PoseArray.FIELD_HEADER)));
		assertEquals(0, empty.toJsonObject()
				.getJsonArray(PoseArray.FIELD_POSES).size());

		assertEquals(PoseArray.TYPE, empty.getMessageType());
	}

	@Test
	public void testHeaderAndPointConstructor() {
		assertEquals(new Header(123, new Time(10, 20), "test"), p1.getHeader());
		assertTrue(Arrays.deepEquals(new Pose[] {
				new Pose(new Point(0.5, 1.5, 3.0), new Quaternion(-0.5, -1.5,
						-3.0, -4.5)),
				new Pose(new Point(-0.5, -1.5, -3.0), new Quaternion(0.5, 1.5,
						3.0, 4.5)) }, p1.getPoses()));

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":123,\"stamp\":{\"secs\":10,\"nsecs\":20},\"frame_id\":\"test\"}"
						+ ",\"poses\":[{\"position\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
						+ "\"orientation\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0,\"w\":-4.5}}"
						+ ",{\"position\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0},"
						+ "\"orientation\":{\"x\":0.5,\"y\":1.5,\"z\":3.0,\"w\":4.5}}]}",
				p1.toString());

		assertEquals(2, p1.toJsonObject().size());
		assertEquals(
				new Header(123, new Time(10, 20), "test"),
				Header.fromJsonObject(p1.toJsonObject().getJsonObject(
						PoseArray.FIELD_HEADER)));
		assertEquals(2, p1.toJsonObject().getJsonArray(PoseArray.FIELD_POSES)
				.size());
		assertEquals(new Pose(new Point(0.5, 1.5, 3.0), new Quaternion(-0.5,
				-1.5, -3.0, -4.5)), Pose.fromJsonObject(p1.toJsonObject()
				.getJsonArray(PoseArray.FIELD_POSES).getJsonObject(0)));
		assertEquals(new Pose(new Point(-0.5, -1.5, -3.0), new Quaternion(0.5,
				1.5, 3.0, 4.5)), Pose.fromJsonObject(p1.toJsonObject()
				.getJsonArray(PoseArray.FIELD_POSES).getJsonObject(1)));

		assertEquals(PoseArray.TYPE, p1.getMessageType());
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
		PoseArray clone = p1.clone();
		assertEquals(p1.toString(), clone.toString());
		assertEquals(p1.toJsonObject(), clone.toJsonObject());
		assertEquals(p1.getMessageType(), clone.getMessageType());
		assertEquals(p1.getHeader(), clone.getHeader());
		assertTrue(Arrays.deepEquals(p1.getPoses(), clone.getPoses()));
		assertEquals(p1.size(), clone.size());
		assertNotSame(p1, clone);
		assertNotSame(p1.toString(), clone.toString());
		assertNotSame(p1.toJsonObject(), clone.toJsonObject());
		assertNotSame(p1.getPoses(), clone.getPoses());
	}

	@Test
	public void testFromJsonString() {
		PoseArray p = PoseArray.fromJsonString(p1.toString());
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getHeader(), p.getHeader());
		assertTrue(Arrays.deepEquals(p1.getPoses(), p.getPoses()));
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.size(), p.size());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(p1.toString());
		PoseArray p = PoseArray.fromMessage(m);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getHeader(), p.getHeader());
		assertTrue(Arrays.deepEquals(p1.getPoses(), p.getPoses()));
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.size(), p.size());
		assertEquals(p1.get(0), p.get(0));
		assertEquals(p1.get(1), p.get(1));
	}

	@Test
	public void testFromJsonObject() {
		JsonArrayBuilder array = Json.createArrayBuilder();
		for (Pose p : p1.getPoses()) {
			array.add(p.toJsonObject());
		}
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(PoseArray.FIELD_HEADER, p1.getHeader().toJsonObject())
				.add(PoseArray.FIELD_POSES, array.build()).build();
		PoseArray p = PoseArray.fromJsonObject(jsonObject);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.size(), p.size());
		assertEquals(p1.getHeader(), p.getHeader());
		assertTrue(Arrays.deepEquals(p1.getPoses(), p.getPoses()));
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoHeader() {
		JsonArrayBuilder array = Json.createArrayBuilder();
		for (Pose p : p1.getPoses()) {
			array.add(p.toJsonObject());
		}
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(PoseArray.FIELD_POSES, array.build()).build();
		PoseArray p = PoseArray.fromJsonObject(jsonObject);
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.size(), p.size());
		assertEquals(new Header(), p.getHeader());
		assertTrue(Arrays.deepEquals(p1.getPoses(), p.getPoses()));
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoPoints() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(PoseArray.FIELD_HEADER, p1.getHeader().toJsonObject())
				.build();
		PoseArray p = PoseArray.fromJsonObject(jsonObject);
		assertEquals(p1.getHeader(), p.getHeader());
		assertTrue(Arrays.deepEquals(new Pose[] {}, p.getPoses()));
		assertEquals(0, p.size());
	}
}
