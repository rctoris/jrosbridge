package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;
import edu.wpi.rail.jrosbridge.primitives.Time;

public class TestPoseWithCovarianceStamped {

	private PoseWithCovarianceStamped empty, p1;

	@Before
	public void setUp() {
		empty = new PoseWithCovarianceStamped();
		p1 = new PoseWithCovarianceStamped(new Header(123, new Time(10, 20),
				"test"), new PoseWithCovariance(new Pose(new Point(0.5, 1.5,
				3.0), new Quaternion(-0.5, -1.5, -3.0, -4.5)), new double[] {
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
				19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34,
				35, 36 }));
	}

	@Test
	public void testConstructor() {
		assertEquals(new Header(), empty.getHeader());
		assertEquals(new PoseWithCovariance(), empty.getPose());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":0,\"stamp\":{\"secs\":0,\"nsecs\":0},\"frame_id\":\"\"}"
						+ ",\"pose\":{\"pose\":{\"position\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
						+ "\"orientation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0,\"w\":0.0}},"
						+ "\"covariance\":[0.0,0.0,0.0,0.0,0.0,0.0"
						+ ",0.0,0.0,0.0,0.0,0.0,0.0"
						+ ",0.0,0.0,0.0,0.0,0.0,0.0"
						+ ",0.0,0.0,0.0,0.0,0.0,0.0"
						+ ",0.0,0.0,0.0,0.0,0.0,0.0"
						+ ",0.0,0.0,0.0,0.0,0.0,0.0]}}", empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Header(),
				Header.fromJsonObject(empty.toJsonObject().getJsonObject(
						PoseWithCovarianceStamped.FIELD_HEADER)));
		assertEquals(new PoseWithCovariance(),
				PoseWithCovariance.fromJsonObject(empty.toJsonObject()
						.getJsonObject(PoseWithCovarianceStamped.FIELD_POSE)));

		assertEquals(PoseWithCovarianceStamped.TYPE, empty.getMessageType());
	}

	@Test
	public void testHeaderAndPoseWithCovarianceConstructor() {
		assertEquals(new Header(123, new Time(10, 20), "test"), p1.getHeader());
		assertEquals(new PoseWithCovariance(new Pose(new Point(0.5, 1.5, 3.0),
				new Quaternion(-0.5, -1.5, -3.0, -4.5)), new double[] { 1, 2,
				3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35,
				36 }), p1.getPose());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":123,\"stamp\":{\"secs\":10,\"nsecs\":20},\"frame_id\":\"test\"}"
						+ ",\"pose\":{\"pose\":{\"position\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
						+ "\"orientation\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0,\"w\":-4.5}},"
						+ "\"covariance\":[1.0,2.0,3.0,4.0,5.0,6.0"
						+ ",7.0,8.0,9.0,10.0,11.0,12.0"
						+ ",13.0,14.0,15.0,16.0,17.0,18.0"
						+ ",19.0,20.0,21.0,22.0,23.0,24.0"
						+ ",25.0,26.0,27.0,28.0,29.0,30.0"
						+ ",31.0,32.0,33.0,34.0,35.0,36.0]}}", p1.toString());

		assertEquals(2, p1.toJsonObject().size());
		assertEquals(
				new Header(123, new Time(10, 20), "test"),
				Header.fromJsonObject(p1.toJsonObject().getJsonObject(
						PoseWithCovarianceStamped.FIELD_HEADER)));
		assertEquals(new PoseWithCovariance(new Pose(new Point(0.5, 1.5, 3.0),
				new Quaternion(-0.5, -1.5, -3.0, -4.5)), new double[] { 1, 2,
				3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35,
				36 }), PoseWithCovariance.fromJsonObject(p1.toJsonObject()
				.getJsonObject(PoseWithCovarianceStamped.FIELD_POSE)));

		assertEquals(PoseWithCovarianceStamped.TYPE, p1.getMessageType());
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
		PoseWithCovarianceStamped clone = p1.clone();
		assertEquals(p1.toString(), clone.toString());
		assertEquals(p1.toJsonObject(), clone.toJsonObject());
		assertEquals(p1.getMessageType(), clone.getMessageType());
		assertEquals(p1.getHeader(), clone.getHeader());
		assertEquals(p1.getPose(), clone.getPose());
		assertNotSame(p1, clone);
		assertNotSame(p1.toString(), clone.toString());
		assertNotSame(p1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromJsonString() {
		PoseWithCovarianceStamped p = PoseWithCovarianceStamped
				.fromJsonString(p1.toString());
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getHeader(), p.getHeader());
		assertEquals(p1.getPose(), p.getPose());
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(p1.toString());
		PoseWithCovarianceStamped p = PoseWithCovarianceStamped.fromMessage(m);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getHeader(), p.getHeader());
		assertEquals(p1.getPose(), p.getPose());
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(PoseWithCovarianceStamped.FIELD_HEADER,
						p1.getHeader().toJsonObject())
				.add(PoseWithCovarianceStamped.FIELD_POSE,
						p1.getPose().toJsonObject()).build();
		PoseWithCovarianceStamped p = PoseWithCovarianceStamped
				.fromJsonObject(jsonObject);
		assertEquals(p1.toString(), p.toString());
		assertEquals(p1.toJsonObject(), p.toJsonObject());
		assertEquals(p1.getMessageType(), p.getMessageType());
		assertEquals(p1.getHeader(), p.getHeader());
		assertEquals(p1.getPose(), p.getPose());
		assertNotSame(p1, p);
		assertNotSame(p1.toString(), p.toString());
		assertNotSame(p1.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoHeader() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(PoseWithCovarianceStamped.FIELD_POSE,
						p1.getPose().toJsonObject()).build();
		PoseWithCovarianceStamped p = PoseWithCovarianceStamped
				.fromJsonObject(jsonObject);
		assertEquals(new Header(), p.getHeader());
		assertEquals(p1.getPose(), p.getPose());
	}

	@Test
	public void testFromJsonObjectNoPoseWithCovariance() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(PoseWithCovarianceStamped.FIELD_HEADER,
						p1.getHeader().toJsonObject()).build();
		PoseWithCovarianceStamped p = PoseWithCovarianceStamped
				.fromJsonObject(jsonObject);
		assertEquals(p1.getHeader(), p.getHeader());
		assertEquals(new PoseWithCovariance(), p.getPose());
	}
}
