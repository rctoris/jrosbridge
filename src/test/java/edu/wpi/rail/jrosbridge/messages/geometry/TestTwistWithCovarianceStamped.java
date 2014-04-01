package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.messages.std.Header;
import edu.wpi.rail.jrosbridge.primitives.Time;

public class TestTwistWithCovarianceStamped {

	private TwistWithCovarianceStamped empty, t1;

	@Before
	public void setUp() {
		empty = new TwistWithCovarianceStamped();
		t1 = new TwistWithCovarianceStamped(new Header(123, new Time(10, 20),
				"test"), new TwistWithCovariance(new Twist(new Vector3(0.5,
				1.5, 3.0), new Vector3(-0.5, -1.5, -3.0)), new double[] { 1, 2,
				3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35,
				36 }));
	}

	@Test
	public void testConstructor() {
		assertEquals(new Header(), empty.getHeader());
		assertEquals(new TwistWithCovariance(), empty.getTwist());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":0,\"stamp\":{\"secs\":0,\"nsecs\":0},\"frame_id\":\"\"}"
						+ ",\"twist\":{\"twist\":{\"linear\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
						+ "\"angular\":{\"x\":0.0,\"y\":0.0,\"z\":0.0}},"
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
						TwistWithCovarianceStamped.FIELD_HEADER)));
		assertEquals(new TwistWithCovariance(),
				TwistWithCovariance.fromJsonObject(empty.toJsonObject()
						.getJsonObject(TwistWithCovarianceStamped.FIELD_TWIST)));

		assertEquals(TwistWithCovarianceStamped.TYPE, empty.getMessageType());
	}

	@Test
	public void testHeaderAndTwistWithCovarianceConstructor() {
		assertEquals(new Header(123, new Time(10, 20), "test"), t1.getHeader());
		assertEquals(new TwistWithCovariance(new Twist(new Vector3(0.5, 1.5,
				3.0), new Vector3(-0.5, -1.5, -3.0)),
				new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
						15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
						29, 30, 31, 32, 33, 34, 35, 36 }), t1.getTwist());

		assertEquals(
				"{\"header\":"
						+ "{\"seq\":123,\"stamp\":{\"secs\":10,\"nsecs\":20},\"frame_id\":\"test\"}"
						+ ",\"twist\":{\"twist\":{\"linear\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
						+ "\"angular\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0}},"
						+ "\"covariance\":[1.0,2.0,3.0,4.0,5.0,6.0"
						+ ",7.0,8.0,9.0,10.0,11.0,12.0"
						+ ",13.0,14.0,15.0,16.0,17.0,18.0"
						+ ",19.0,20.0,21.0,22.0,23.0,24.0"
						+ ",25.0,26.0,27.0,28.0,29.0,30.0"
						+ ",31.0,32.0,33.0,34.0,35.0,36.0]}}", t1.toString());

		assertEquals(2, t1.toJsonObject().size());
		assertEquals(
				new Header(123, new Time(10, 20), "test"),
				Header.fromJsonObject(t1.toJsonObject().getJsonObject(
						TwistWithCovarianceStamped.FIELD_HEADER)));
		assertEquals(new TwistWithCovariance(new Twist(new Vector3(0.5, 1.5,
				3.0), new Vector3(-0.5, -1.5, -3.0)),
				new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
						15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
						29, 30, 31, 32, 33, 34, 35, 36 }),
				TwistWithCovariance.fromJsonObject(t1.toJsonObject()
						.getJsonObject(TwistWithCovarianceStamped.FIELD_TWIST)));

		assertEquals(TwistWithCovarianceStamped.TYPE, t1.getMessageType());
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
		TwistWithCovarianceStamped clone = t1.clone();
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
		TwistWithCovarianceStamped p = TwistWithCovarianceStamped
				.fromJsonString(t1.toString());
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
		TwistWithCovarianceStamped p = TwistWithCovarianceStamped
				.fromMessage(m);
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
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(TwistWithCovarianceStamped.FIELD_HEADER,
						t1.getHeader().toJsonObject())
				.add(TwistWithCovarianceStamped.FIELD_TWIST,
						t1.getTwist().toJsonObject()).build();
		TwistWithCovarianceStamped p = TwistWithCovarianceStamped
				.fromJsonObject(jsonObject);
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
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(TwistWithCovarianceStamped.FIELD_TWIST,
						t1.getTwist().toJsonObject()).build();
		TwistWithCovarianceStamped p = TwistWithCovarianceStamped
				.fromJsonObject(jsonObject);
		assertEquals(new Header(), p.getHeader());
		assertEquals(t1.getTwist(), p.getTwist());
	}

	@Test
	public void testFromJsonObjectNoTwistWithCovariance() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(TwistWithCovarianceStamped.FIELD_HEADER,
						t1.getHeader().toJsonObject()).build();
		TwistWithCovarianceStamped p = TwistWithCovarianceStamped
				.fromJsonObject(jsonObject);
		assertEquals(t1.getHeader(), p.getHeader());
		assertEquals(new TwistWithCovariance(), p.getTwist());
	}
}
