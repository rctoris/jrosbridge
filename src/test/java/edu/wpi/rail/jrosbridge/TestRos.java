package edu.wpi.rail.jrosbridge;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.websocket.Session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.JRosbridge;
import edu.wpi.rail.jrosbridge.Ros;
import edu.wpi.rail.jrosbridge.callback.ServiceCallback;
import edu.wpi.rail.jrosbridge.callback.TopicCallback;
import edu.wpi.rail.jrosbridge.handler.RosHandler;
import edu.wpi.rail.jrosbridge.messages.Message;
import edu.wpi.rail.jrosbridge.services.ServiceResponse;

public class TestRos {

	private Ros r1, r2, r3, r4;
	private DummyServer server;

	@Before
	public void setUp() throws InterruptedException {
		r1 = new Ros();
		r2 = new Ros("test");
		r3 = new Ros("test2", -123);
		r4 = new Ros("test3", 1234, JRosbridge.WebSocketType.wss);
		server = new DummyServer(r1.getPort());
		server.start();
	}

	@After
	public void tearDown() {
		r1.disconnect();
		r2.disconnect();
		r3.disconnect();
		r4.disconnect();
		server.stop();
		DummyHandler.latest = null;
	}

	@Test
	public void testConstructor() {
		assertEquals(Ros.DEFAULT_HOSTNAME, r1.getHostname());
		assertEquals(Ros.DEFAULT_PORT, r1.getPort());
		assertEquals(JRosbridge.WebSocketType.ws, r1.getProtocol());
		assertEquals("ws://localhost:9090", r1.getURL());
		assertFalse(r1.isConnected());
	}

	@Test
	public void testStringConstructor() {
		assertEquals("test", r2.getHostname());
		assertEquals(Ros.DEFAULT_PORT, r2.getPort());
		assertEquals(JRosbridge.WebSocketType.ws, r2.getProtocol());
		assertEquals("ws://test:9090", r2.getURL());
		assertFalse(r2.isConnected());
	}

	@Test
	public void testStringAndIntConstructor() {
		assertEquals("test2", r3.getHostname());
		assertEquals(-123, r3.getPort());
		assertEquals(JRosbridge.WebSocketType.ws, r3.getProtocol());
		assertEquals("ws://test2:-123", r3.getURL());
		assertFalse(r3.isConnected());
	}

	@Test
	public void testStringIntAndProtocolConstructor() {
		assertEquals("test3", r4.getHostname());
		assertEquals(1234, r4.getPort());
		assertEquals(JRosbridge.WebSocketType.wss, r4.getProtocol());
		assertEquals("wss://test3:1234", r4.getURL());
		assertFalse(r4.isConnected());
	}

	@Test
	public void testNextID() {
		for (int i = 0; i < 20; i++) {
			assertEquals((long) i, r1.nextId());
			assertEquals((long) i, r2.nextId());
			assertEquals((long) i, r3.nextId());
			assertEquals((long) i, r4.nextId());
		}
	}

	@Test
	public void testConnect() throws InterruptedException {
		assertTrue(r1.connect());
		assertTrue(r1.isConnected());
	}

	@Test
	public void testDisconnect() {
		assertTrue(r1.connect());
		assertTrue(r1.disconnect());
		assertFalse(r1.isConnected());
	}

	@Test
	public void testConnectFailed() {
		assertFalse(r3.connect());
		assertFalse(r3.isConnected());
	}

	@Test
	public void testDisconnectNoConnection() {
		assertFalse(r1.disconnect());
		assertFalse(r1.isConnected());
		assertFalse(r2.disconnect());
		assertFalse(r2.isConnected());
		assertFalse(r3.disconnect());
		assertFalse(r3.isConnected());
		assertFalse(r4.disconnect());
		assertFalse(r4.isConnected());
	}

	@Test
	public void testAddRosHandler() throws InterruptedException {
		DummyRosHandler h = new DummyRosHandler();

		r1.addRosHandler(h);
		assertFalse(h.connection);
		assertFalse(h.disconnection);
		assertFalse(h.error);

		assertTrue(r1.connect());
		Thread.sleep(500);
		assertTrue(r1.isConnected());
		assertTrue(h.connection);
		assertFalse(h.disconnection);
		assertFalse(h.error);

		assertTrue(r1.disconnect());
		Thread.sleep(500);
		assertFalse(r1.isConnected());
		assertTrue(h.connection);
		assertTrue(h.disconnection);
		assertFalse(h.error);

		r1.onError(null, null);
		assertTrue(h.connection);
		assertTrue(h.disconnection);
		assertTrue(h.error);
	}

	@Test
	public void testSend() {
		assertTrue(r1.connect());
		assertTrue(r1.send(Json.createObjectBuilder().add("test", "value")
				.build()));

		while (DummyHandler.latest == null) {
			Thread.yield();
		}

		assertNotNull(DummyHandler.latest);
		assertEquals(1, DummyHandler.latest.size());
		assertTrue(DummyHandler.latest.containsKey("test"));
		assertEquals("value", DummyHandler.latest.getString("test"));
	}

	@Test
	public void testSendNoConnection() {
		assertFalse(r1.send(Json.createObjectBuilder().build()));
		assertFalse(r2.send(Json.createObjectBuilder().build()));
		assertFalse(r3.send(Json.createObjectBuilder().build()));
		assertFalse(r4.send(Json.createObjectBuilder().build()));
		assertNull(DummyHandler.latest);
	}

	@Test
	public void testAuthenticate() {
		assertTrue(r1.connect());
		r1.authenticate("test1", "test2", "test3", "test4", 5, "test5", 10);

		while (DummyHandler.latest == null) {
			Thread.yield();
		}

		assertNotNull(DummyHandler.latest);
		assertEquals(8, DummyHandler.latest.size());
		assertEquals(JRosbridge.OP_CODE_AUTH,
				DummyHandler.latest.getString(JRosbridge.FIELD_OP));
		assertEquals("test1",
				DummyHandler.latest.getString(JRosbridge.FIELD_MAC));
		assertEquals("test2",
				DummyHandler.latest.getString(JRosbridge.FIELD_CLIENT));
		assertEquals("test3",
				DummyHandler.latest
						.getString(JRosbridge.FIELD_DESTINATION));
		assertEquals("test4",
				DummyHandler.latest.getString(JRosbridge.FIELD_RAND));
		assertEquals(5, DummyHandler.latest.getInt(JRosbridge.FIELD_TIME));
		assertEquals("test5",
				DummyHandler.latest.getString(JRosbridge.FIELD_LEVEL));
		assertEquals(10,
				DummyHandler.latest.getInt(JRosbridge.FIELD_END_TIME));
	}

	@Test
	public void testOnMessageInvalidOpCode() {
		assertTrue(r1.connect());
		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\"invalid\"}");
		Thread.yield();
		assertNull(DummyHandler.latest);
	}

	@Test
	public void testOnMessageInvalidPngData() {
		assertTrue(r1.connect());
		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\""
				+ JRosbridge.OP_CODE_PNG + "\"}");
		Thread.yield();
		assertNull(DummyHandler.latest);

		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\""
				+ JRosbridge.OP_CODE_PNG + "\",\"" + JRosbridge.FIELD_DATA
				+ "\":\"iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKCAMAAAC67D+PAAAAGXR"
				+ "FWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAGBQTFRF///"
				+ "/AGb/AGbMmcz/M5nMZpnM////AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
				+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
				+ "AAAAAAAAAAAAA7feQVwAAAAd0Uk5T////////ABpLA0YAAAA6SURBVHj"
				+ "aJMtBDgBABARBs4P/P3kbfZCKEE3aAmUFLVu5fCQfGQ7nciTV0GW9zp4"
				+ "Ds+B5SMcLfgEGADSKAPVZzedhAAAAAElFTkSuQmCC\"}");
		Thread.yield();
		assertNull(DummyHandler.latest);
	}

	@Test
	public void testOnMessagePngData() {
		assertTrue(r1.connect());

		DummyTopicCallback cb = new DummyTopicCallback();
		r1.registerTopicCallback("myTopic", cb);
		assertNull(cb.latest);

		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\""
				+ JRosbridge.OP_CODE_PNG + "\",\"" + JRosbridge.FIELD_DATA
				+ "\":\"iVBORw0KGgoAAAANSUhEUgAAAAQAAAAFCAIAAADtz9qMAAAATEl"
				+ "EQVR4nAFBAL7/AXsib/UAy7JOO0D89AL4RrO8ADpNAPQBttECrwVXKE3"
				+ "8+vO5yQAzBFH6qccUACD2UUgPrwLHu1Ir+FK+vQoJ2ejGjx3lsrwJjwA"
				+ "AAABJRU5ErkJggg==\"}");
		Thread.yield();
		assertNull(DummyHandler.latest);
		assertNotNull(cb.latest);
		assertEquals("{\"test1\":\"test2\"}", cb.latest.toString());
	}

	@Test
	public void testOnMessageNoTopicCallbacks() {
		assertTrue(r1.connect());
		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\""
				+ JRosbridge.OP_CODE_PUBLISH + "\",\"" + JRosbridge.FIELD_TOPIC
				+ "\":\"myTopic\",\"" + JRosbridge.FIELD_MESSAGE
				+ "\":{\"test1\":\"test2\"}}");
		Thread.yield();
		assertNull(DummyHandler.latest);
	}

	@Test
	public void testOnMessageMultiTopicCallbacks() {
		assertTrue(r1.connect());

		DummyTopicCallback cb1 = new DummyTopicCallback();
		DummyTopicCallback cb2 = new DummyTopicCallback();
		r1.registerTopicCallback("myTopic", cb1);
		r1.registerTopicCallback("myTopic", cb2);
		assertNull(cb1.latest);
		assertNull(cb2.latest);

		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\""
				+ JRosbridge.OP_CODE_PUBLISH + "\",\"" + JRosbridge.FIELD_TOPIC
				+ "\":\"myTopic\",\"" + JRosbridge.FIELD_MESSAGE
				+ "\":{\"test1\":\"test2\"}}");
		Thread.yield();
		assertNull(DummyHandler.latest);
		assertNotNull(cb1.latest);
		assertNotNull(cb2.latest);
		assertEquals("{\"test1\":\"test2\"}", cb1.latest.toString());
		assertEquals("{\"test1\":\"test2\"}", cb2.latest.toString());
	}

	@Test
	public void testDeregisterTopicCallback() {
		assertTrue(r1.connect());

		DummyTopicCallback cb1 = new DummyTopicCallback();
		DummyTopicCallback cb2 = new DummyTopicCallback();
		r1.registerTopicCallback("myTopic", cb1);
		r1.registerTopicCallback("myTopic", cb2);
		assertNull(cb1.latest);
		assertNull(cb2.latest);
		r1.deregisterTopicCallback("myTopic", cb1);

		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\""
				+ JRosbridge.OP_CODE_PUBLISH + "\",\"" + JRosbridge.FIELD_TOPIC
				+ "\":\"myTopic\",\"" + JRosbridge.FIELD_MESSAGE
				+ "\":{\"test1\":\"test2\"}}");
		Thread.yield();
		assertNull(DummyHandler.latest);
		assertNull(cb1.latest);
		assertNotNull(cb2.latest);
		assertEquals("{\"test1\":\"test2\"}", cb2.latest.toString());
	}

	@Test
	public void testDeregisterTopicCallbackAll() {
		assertTrue(r1.connect());

		DummyTopicCallback cb1 = new DummyTopicCallback();
		DummyTopicCallback cb2 = new DummyTopicCallback();
		r1.registerTopicCallback("myTopic", cb1);
		r1.registerTopicCallback("myTopic", cb2);
		assertNull(cb1.latest);
		assertNull(cb2.latest);
		r1.deregisterTopicCallback("myTopic", cb1);
		r1.deregisterTopicCallback("myTopic", cb2);

		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\""
				+ JRosbridge.OP_CODE_PUBLISH + "\",\"" + JRosbridge.FIELD_TOPIC
				+ "\":\"myTopic\",\"" + JRosbridge.FIELD_MESSAGE
				+ "\":{\"test1\":\"test2\"}}");
		Thread.yield();
		assertNull(DummyHandler.latest);
		assertNull(cb1.latest);
		assertNull(cb2.latest);
	}

	@Test
	public void testDeregisterTopicCallbackInvalidTopic() {
		assertTrue(r1.connect());

		DummyTopicCallback cb1 = new DummyTopicCallback();
		DummyTopicCallback cb2 = new DummyTopicCallback();
		r1.registerTopicCallback("myTopic", cb1);
		r1.registerTopicCallback("myTopic", cb2);
		assertNull(cb1.latest);
		assertNull(cb2.latest);
		r1.deregisterTopicCallback("myTopic2", cb1);
		r1.deregisterTopicCallback("myTopic2", cb2);

		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\""
				+ JRosbridge.OP_CODE_PUBLISH + "\",\"" + JRosbridge.FIELD_TOPIC
				+ "\":\"myTopic\",\"" + JRosbridge.FIELD_MESSAGE
				+ "\":{\"test1\":\"test2\"}}");
		Thread.yield();
		assertNull(DummyHandler.latest);
		assertNotNull(cb1.latest);
		assertNotNull(cb2.latest);
		assertEquals("{\"test1\":\"test2\"}", cb1.latest.toString());
		assertEquals("{\"test1\":\"test2\"}", cb2.latest.toString());
	}

	@Test
	public void testDeregisterTopicCallbackInvalidCallback() {
		assertTrue(r1.connect());

		DummyTopicCallback cb1 = new DummyTopicCallback();
		DummyTopicCallback cb2 = new DummyTopicCallback();
		r1.registerTopicCallback("myTopic", cb1);
		r1.registerTopicCallback("myTopic", cb2);
		assertNull(cb1.latest);
		assertNull(cb2.latest);
		r1.deregisterTopicCallback("myTopic", new DummyTopicCallback());

		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\""
				+ JRosbridge.OP_CODE_PUBLISH + "\",\"" + JRosbridge.FIELD_TOPIC
				+ "\":\"myTopic\",\"" + JRosbridge.FIELD_MESSAGE
				+ "\":{\"test1\":\"test2\"}}");
		Thread.yield();
		assertNull(DummyHandler.latest);
		assertNotNull(cb1.latest);
		assertNotNull(cb2.latest);
		assertEquals("{\"test1\":\"test2\"}", cb1.latest.toString());
		assertEquals("{\"test1\":\"test2\"}", cb2.latest.toString());
	}

	@Test
	public void testOnMessageNoServiceCallbacks() {
		assertTrue(r1.connect());
		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\""
				+ JRosbridge.OP_CODE_SERVICE_RESPONSE + "\",\""
				+ JRosbridge.FIELD_ID + "\":\"id123\",\""
				+ JRosbridge.FIELD_RESULT + "\":true,\""
				+ JRosbridge.FIELD_MESSAGE + "\":{\"test1\":\"test2\"}}");
		Thread.yield();
		assertNull(DummyHandler.latest);
	}

	@Test
	public void testOnMessageServiceCallback() {
		assertTrue(r1.connect());

		DummyServiceCallback cb1 = new DummyServiceCallback();
		r1.registerServiceCallback("id123", cb1);
		assertNull(cb1.latest);

		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\""
				+ JRosbridge.OP_CODE_SERVICE_RESPONSE + "\",\""
				+ JRosbridge.FIELD_ID + "\":\"id123\",\""
				+ JRosbridge.FIELD_RESULT + "\":false,\""
				+ JRosbridge.FIELD_VALUES + "\":{\"test1\":\"test2\"}}");
		Thread.yield();
		assertNull(DummyHandler.latest);
		assertNotNull(cb1.latest);
		assertEquals("{\"test1\":\"test2\"}", cb1.latest.toString());
		assertFalse(cb1.latest.getResult());
	}

	@Test
	public void testOnMessageServiceCallbackNoResult() {
		assertTrue(r1.connect());

		DummyServiceCallback cb1 = new DummyServiceCallback();
		r1.registerServiceCallback("id123", cb1);
		assertNull(cb1.latest);

		r1.onMessage("{\"" + JRosbridge.FIELD_OP + "\":\""
				+ JRosbridge.OP_CODE_SERVICE_RESPONSE + "\",\""
				+ JRosbridge.FIELD_ID + "\":\"id123\",\""
				+ JRosbridge.FIELD_VALUES + "\":{\"test1\":\"test2\"}}");
		Thread.yield();
		assertNull(DummyHandler.latest);
		assertNotNull(cb1.latest);
		assertEquals("{\"test1\":\"test2\"}", cb1.latest.toString());
		assertTrue(cb1.latest.getResult());
	}

	private class DummyRosHandler implements RosHandler {

		public boolean connection, disconnection, error;

		public void handleConnection(Session session) {
			this.connection = true;
		}

		public void handleDisconnection(Session session) {
			this.disconnection = true;
		}

		public void handleError(Session session, Throwable t) {
			this.error = true;
		}
	}

	private class DummyTopicCallback implements TopicCallback {

		public Message latest = null;

		public void handleMessage(Message message) {
			latest = message;
		}
	}

	private class DummyServiceCallback implements ServiceCallback {

		public ServiceResponse latest = null;

		public void handleServiceResponse(ServiceResponse response) {
			this.latest = response;
		}
	}
}
