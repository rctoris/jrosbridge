package edu.wpi.rail.jrosbridge;

import static org.junit.Assert.*;

import java.util.Timer;
import java.util.TimerTask;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.callback.CallServiceCallback;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.callback.ServiceCallback;
import edu.wpi.rail.jrosbridge.services.ServiceRequest;
import edu.wpi.rail.jrosbridge.services.ServiceResponse;

public class TestService {

	private Ros ros;
	private DummyServer server;
	private Service s1;

	@Before
	public void setUp() {
		ros = new Ros();
		server = new DummyServer(ros.getPort());
		server.start();
		ros.connect();

		s1 = new Service(ros, "myService", "myType");
	}

	@After
	public void tearDown() {
		ros.disconnect();
		server.stop();
		DummyHandler.latest = null;
	}

	@Test
	public void testRosStringAndStringConstructor() {
		assertEquals(ros, s1.getRos());
		assertEquals("myService", s1.getName());
		assertEquals("myType", s1.getType());
		assertFalse(s1.isAdvertised());
	}

	@Test
	public void testCallService() {
		ServiceRequest req = new ServiceRequest("{\"test1\":\"test2\"}");
		DummyServiceCallback cb = new DummyServiceCallback();

		s1.callService(req, cb);

		while (DummyHandler.latest == null) {
			Thread.yield();
		}

		assertNotNull(DummyHandler.latest);
		assertEquals(5, DummyHandler.latest.size());
		assertEquals(JRosbridge.OP_CODE_CALL_SERVICE,
				DummyHandler.latest.getString(JRosbridge.FIELD_OP));
		assertEquals("call_service:myService:0",
				DummyHandler.latest.getString(JRosbridge.FIELD_ID));
		assertEquals("myType",
				DummyHandler.latest.getString(JRosbridge.FIELD_TYPE));
		assertEquals("myService",
				DummyHandler.latest.getString(JRosbridge.FIELD_SERVICE));
		assertEquals("{\"test1\":\"test2\"}", DummyHandler.latest
				.getJsonObject(JRosbridge.FIELD_ARGS).toString());

		JsonObject toSend = Json
				.createObjectBuilder()
				.add("echo",
						Json.createObjectBuilder()
								.add(JRosbridge.FIELD_OP,
										JRosbridge.OP_CODE_SERVICE_RESPONSE)
								.add(JRosbridge.FIELD_ID,
										"call_service:myService:0")
								.add(JRosbridge.FIELD_RESULT, false)
								.add(JRosbridge.FIELD_VALUES,
										Json.createObjectBuilder()
												.add("test3", "test4").build())
								.build().toString()).build();
		ros.send(toSend);

		while (cb.latest == null) {
			Thread.yield();
		}

		assertNotNull(cb.latest);
		assertEquals("{\"test3\":\"test4\"}", cb.latest.toString());
	}

	@Test
	public void testCallServiceAndWait() {
		ServiceRequest req = new ServiceRequest("{\"test1\":\"test2\"}");
		Timer timer = new Timer();
		timer.schedule(new SendServiceResponse(ros), 1500);

		ServiceResponse resp = s1.callServiceAndWait(req);

		assertNotNull(resp);
		assertEquals("{\"test3\":\"test4\"}", resp.toString());
	}

	@Test
	public void testAdvertiseService() {
		DummyCallServiceCallback cb = new DummyCallServiceCallback();
		s1.advertiseService(cb);
		assertNull(cb.latest);

		while (DummyHandler.latest == null) {
			Thread.yield();
		}

		assertNotNull(DummyHandler.latest);
		assertEquals(
				"{\"op\":\"advertise_service\",\"type\":\"myType\"," + "\"service\":\"myService\"}",
				DummyHandler.latest.toString());
		assertTrue(s1.isAdvertised());

		ros.send(Json
				.createObjectBuilder()
				.add("echo",
						"{\"" + JRosbridge.FIELD_OP + "\":\""
								+ JRosbridge.OP_CODE_CALL_SERVICE + "\",\""
								+ JRosbridge.FIELD_ID + "\":\"myServiceId\",\""
								+ JRosbridge.FIELD_SERVICE + "\":\"myService\",\""
								+ JRosbridge.FIELD_ARGS
								+ "\":{\"test1\":\"test2\"}}").build());

		while (cb.latest == null) {
			Thread.yield();
		}

		assertNotNull(cb.latest);
		assertEquals("{\"test1\":\"test2\"}", cb.latest.toString());
		assertEquals("myServiceId", cb.latest.getId());
	}

	@Test
	public void testUnadvertiseService() {
		s1.unadvertiseService();

		while (DummyHandler.latest == null) {
			Thread.yield();
		}

		assertNotNull(DummyHandler.latest);
		assertEquals(
				"{\"op\":\"unadvertise_service\",\"service\":\"myService\"}",
				DummyHandler.latest.toString());
		assertFalse(s1.isAdvertised());
	}

	@Test
	public void testSendResponse() {
		ServiceResponse resp = new ServiceResponse("{\"test1\":\"test2\"}", true);
		s1.sendResponse(resp, "myServiceId");

		while (DummyHandler.latest == null) {
			Thread.yield();
		}

		assertNotNull(DummyHandler.latest);
		assertEquals(
				"{\"op\":\"service_response\",\"id\":\"myServiceId\"," +
						"\"service\":\"myService\",\"values\":{\"test1\":\"test2\"},\"result\":true}",
				DummyHandler.latest.toString());
	}

	private class DummyServiceCallback implements ServiceCallback {

		public ServiceResponse latest = null;

		public void handleServiceResponse(ServiceResponse resp) {
			this.latest = resp;
		}
	}

	private class SendServiceResponse extends TimerTask {

		private Ros ros;

		public SendServiceResponse(Ros ros) {
			this.ros = ros;
		}

		public void run() {
			JsonObject toSend = Json
					.createObjectBuilder()
					.add("echo",
							Json.createObjectBuilder()
									.add(JRosbridge.FIELD_OP,
											JRosbridge.OP_CODE_SERVICE_RESPONSE)
									.add(JRosbridge.FIELD_ID,
											"call_service:myService:0")
									.add(JRosbridge.FIELD_RESULT, false)
									.add(JRosbridge.FIELD_VALUES,
											Json.createObjectBuilder()
													.add("test3", "test4")
													.build()).build()
									.toString()).build();
			ros.send(toSend);
		}
	}

	private class DummyCallServiceCallback implements CallServiceCallback {

		public ServiceRequest latest = null;

		public void handleServiceCall(ServiceRequest request) {
			this.latest = request;
		}
	}
}
