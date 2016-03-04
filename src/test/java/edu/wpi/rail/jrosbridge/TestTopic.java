package edu.wpi.rail.jrosbridge;

import static org.junit.Assert.*;

import javax.json.Json;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.JRosbridge;
import edu.wpi.rail.jrosbridge.Ros;
import edu.wpi.rail.jrosbridge.Topic;
import edu.wpi.rail.jrosbridge.callback.TopicCallback;
import edu.wpi.rail.jrosbridge.messages.Message;

public class TestTopic {

	private Ros ros;
	private DummyServer server;
	private Topic t1, t2, t3, t4;

	@Before
	public void setUp() {
		ros = new Ros();
		server = new DummyServer(ros.getPort());
		server.start();
		ros.connect();

		t1 = new Topic(ros, "myTopic1", "myType1");
		t2 = new Topic(ros, "myTopic2", "myType2",
				JRosbridge.CompressionType.png);
		t3 = new Topic(ros, "myTopic3", "myType3", 10);
		t4 = new Topic(ros, "myTopic4", "myType4",
				JRosbridge.CompressionType.png, 20);
	}

	@After
	public void tearDown() {
		ros.disconnect();
		server.stop();
		DummyHandler.latest = null;
	}

	@Test
	public void testRosStringAndStringConstructor() {
		assertEquals(ros, t1.getRos());
		assertEquals("myTopic1", t1.getName());
		assertEquals("myType1", t1.getType());
		assertFalse(t1.isAdvertised());
		assertFalse(t1.isSubscribed());
		assertEquals(JRosbridge.CompressionType.none, t1.getCompression());
		assertEquals(0, t1.getThrottleRate());
	}

	@Test
	public void testRosStringStringAndCompressionTypeConstructor() {
		assertEquals(ros, t2.getRos());
		assertEquals("myTopic2", t2.getName());
		assertEquals("myType2", t2.getType());
		assertFalse(t2.isAdvertised());
		assertFalse(t2.isSubscribed());
		assertEquals(JRosbridge.CompressionType.png, t2.getCompression());
		assertEquals(0, t2.getThrottleRate());
	}

	@Test
	public void testRosStringStringAndIntConstructor() {
		assertEquals(ros, t3.getRos());
		assertEquals("myTopic3", t3.getName());
		assertEquals("myType3", t3.getType());
		assertFalse(t3.isAdvertised());
		assertFalse(t3.isSubscribed());
		assertEquals(JRosbridge.CompressionType.none, t3.getCompression());
		assertEquals(10, t3.getThrottleRate());
	}

	@Test
	public void testRosStringStringCompressionTypeAndIntConstructor() {
		assertEquals(ros, t4.getRos());
		assertEquals("myTopic4", t4.getName());
		assertEquals("myType4", t4.getType());
		assertFalse(t4.isAdvertised());
		assertFalse(t4.isSubscribed());
		assertEquals(JRosbridge.CompressionType.png, t4.getCompression());
		assertEquals(20, t4.getThrottleRate());
	}

	@Test
	public void testSubscribe() {
		DummyTopicCallback cb = new DummyTopicCallback();
		t1.subscribe(cb);
		assertNull(cb.latest);

		ros.send(Json
				.createObjectBuilder()
				.add("echo",
						"{\"" + JRosbridge.FIELD_OP + "\":\""
								+ JRosbridge.OP_CODE_PUBLISH + "\",\""
								+ JRosbridge.FIELD_TOPIC + "\":\"myTopic1\",\""
								+ JRosbridge.FIELD_MESSAGE
								+ "\":{\"test1\":\"test2\"}}").build());

		while (cb.latest == null) {
			Thread.yield();
		}

		assertNotNull(DummyHandler.latest);
		assertEquals(
				"{\"op\":\"subscribe\",\"id\":\"subscribe:myTopic1:0\",\"type\":\"myType1\","
						+ "\"topic\":\"myTopic1\",\"compression\":\"none\",\"throttle_rate\":0}",
				DummyHandler.latest.toString());

		assertNotNull(cb.latest);
		assertEquals("{\"test1\":\"test2\"}", cb.latest.toString());
		assertFalse(t1.isAdvertised());
		assertTrue(t1.isSubscribed());
	}

	@Test
	public void testUnsubscribe() {
		DummyTopicCallback cb = new DummyTopicCallback();
		t1.subscribe(cb);
		assertFalse(t1.isAdvertised());
		assertTrue(t1.isSubscribed());
		assertNull(cb.latest);
		t1.unsubscribe();

		ros.send(Json
				.createObjectBuilder()
				.add("echo",
						"{\"" + JRosbridge.FIELD_OP + "\":\""
								+ JRosbridge.OP_CODE_PUBLISH + "\",\""
								+ JRosbridge.FIELD_TOPIC + "\":\"myTopic1\",\""
								+ JRosbridge.FIELD_MESSAGE
								+ "\":{\"test1\":\"test2\"}}").build());
		Thread.yield();

		assertNotNull(DummyHandler.latest);
		assertEquals(
				"{\"op\":\"subscribe\",\"id\":\"subscribe:myTopic1:0\",\"type\":\"myType1\","
						+ "\"topic\":\"myTopic1\",\"compression\":\"none\",\"throttle_rate\":0}",
				DummyHandler.latest.toString());

		assertNull(cb.latest);
		assertFalse(t1.isAdvertised());
		assertFalse(t1.isSubscribed());
	}

	@Test
	public void testUnsubscribeNoSubscribe() {
		DummyTopicCallback cb = new DummyTopicCallback();
		assertNull(cb.latest);
		t1.unsubscribe();

		assertNull(DummyHandler.latest);
		assertNull(cb.latest);
		assertFalse(t1.isAdvertised());
		assertFalse(t1.isSubscribed());
	}

	@Test
	public void testAdvertise() {
		t1.advertise();

		while (DummyHandler.latest == null) {
			Thread.yield();
		}

		assertNotNull(DummyHandler.latest);
		assertEquals(
				"{\"op\":\"advertise\",\"id\":\"advertise:myTopic1:0\",\"type\":\"myType1\","
						+ "\"topic\":\"myTopic1\"}",
				DummyHandler.latest.toString());
		assertTrue(t1.isAdvertised());
		assertFalse(t1.isSubscribed());
	}

	@Test
	public void testUnadvertise() {
		t1.unadvertise();

		while (DummyHandler.latest == null) {
			Thread.yield();
		}

		assertNotNull(DummyHandler.latest);
		assertEquals(
				"{\"op\":\"unadvertise\",\"id\":\"unadvertise:myTopic1:0\",\"topic\":\"myTopic1\"}",
				DummyHandler.latest.toString());
		assertFalse(t1.isAdvertised());
		assertFalse(t1.isSubscribed());
	}

	@Test
	public void testPublish() {
		t1.advertise();

		while (DummyHandler.latest == null) {
			Thread.yield();
		}
		DummyHandler.latest = null;

		t1.publish(new Message("{\"test1\":\"test2\"}"));

		while (DummyHandler.latest == null) {
			Thread.yield();
		}

		assertNotNull(DummyHandler.latest);
		assertEquals(
				"{\"op\":\"publish\",\"id\":\"publish:myTopic1:1\",\"topic\":\"myTopic1\""
						+ ",\"msg\":{\"test1\":\"test2\"}}",
				DummyHandler.latest.toString());
		assertTrue(t1.isAdvertised());
		assertFalse(t1.isSubscribed());
	}

	private class DummyTopicCallback implements TopicCallback {

		public Message latest = null;

		public void handleMessage(Message message) {
			this.latest = message;
		}
	}
}
