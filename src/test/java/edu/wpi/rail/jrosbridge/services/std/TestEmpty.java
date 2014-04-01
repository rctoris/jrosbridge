package edu.wpi.rail.jrosbridge.services.std;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.services.ServiceRequest;
import edu.wpi.rail.jrosbridge.services.ServiceResponse;

public class TestEmpty {

	private Empty empty;
	private Empty.Request emptyReq;
	private Empty.Response emptyResp;

	@Before
	public void setUp() {
		empty = new Empty();
		emptyReq = new Empty.Request();
		emptyResp = new Empty.Response();
	}

	@Test
	public void testEmpty() {
		assertNotNull(empty);
	}

	@Test
	public void testRequestConstructor() {
		assertEquals("{}", emptyReq.toString());

		assertEquals(0, emptyReq.toJsonObject().size());

		assertEquals(Empty.TYPE, emptyReq.getServiceRequestType());
	}

	@Test
	public void testRequestServiceRequestType() {
		emptyReq.setServiceRequestType("test");
		assertEquals("test", emptyReq.getServiceRequestType());
	}

	@Test
	public void testRequestHashCode() {
		assertEquals(emptyReq.toString().hashCode(), emptyReq.hashCode());
	}

	@Test
	public void testRequestEquals() {
		assertTrue(emptyReq.equals(emptyReq));
	}

	@Test
	public void testRequestEqualsWrongObject() {
		assertFalse(emptyReq.equals(new String(emptyReq.toString())));
	}

	@Test
	public void testRequestClone() {
		Empty.Request clone = emptyReq.clone();
		assertEquals(emptyReq.toString(), clone.toString());
		assertEquals(emptyReq.toJsonObject(), clone.toJsonObject());
		assertEquals(emptyReq.getServiceRequestType(),
				clone.getServiceRequestType());
		assertNotSame(emptyReq, clone);
		assertNotSame(emptyReq.toString(), clone.toString());
		assertNotSame(emptyReq.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testRequestFromJsonString() {
		Empty.Request req = Empty.Request.fromJsonString(emptyReq.toString());
		assertEquals(emptyReq.toString(), req.toString());
		assertEquals(emptyReq.toJsonObject(), req.toJsonObject());
		assertEquals(emptyReq.getServiceRequestType(),
				req.getServiceRequestType());
		assertNotSame(emptyReq, req);
		assertNotSame(emptyReq.toString(), req.toString());
		assertNotSame(emptyReq.toJsonObject(), req.toJsonObject());
	}

	@Test
	public void testRequestFromServiceRequest() {
		ServiceRequest sr = new ServiceRequest(emptyReq.toString());
		Empty.Request req = Empty.Request.fromServiceRequest(sr);
		assertEquals(emptyReq.toString(), req.toString());
		assertEquals(emptyReq.toJsonObject(), req.toJsonObject());
		assertEquals(emptyReq.getServiceRequestType(),
				req.getServiceRequestType());
		assertNotSame(emptyReq, req);
		assertNotSame(emptyReq.toString(), req.toString());
		assertNotSame(emptyReq.toJsonObject(), req.toJsonObject());
	}

	@Test
	public void testRequestFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder().build();
		Empty.Request req = Empty.Request.fromJsonObject(jsonObject);
		assertEquals(emptyReq.toString(), req.toString());
		assertEquals(emptyReq.toJsonObject(), req.toJsonObject());
		assertEquals(emptyReq.getServiceRequestType(),
				req.getServiceRequestType());
		assertNotSame(emptyReq, req);
		assertNotSame(emptyReq.toString(), req.toString());
		assertNotSame(emptyReq.toJsonObject(), req.toJsonObject());
	}

	@Test
	public void testResponseConstructor() {
		assertEquals("{}", emptyResp.toString());

		assertEquals(0, emptyResp.toJsonObject().size());

		assertEquals(Empty.TYPE, emptyResp.getServiceResponseType());
	}

	@Test
	public void testResponseServiceResponseType() {
		emptyResp.setServiceResponseType("test");
		assertEquals("test", emptyResp.getServiceResponseType());
	}

	@Test
	public void testResponseHashCode() {
		assertEquals(emptyResp.toString().hashCode(), emptyResp.hashCode());
	}

	@Test
	public void testResponseEquals() {
		assertTrue(emptyResp.equals(emptyResp));
	}

	@Test
	public void testResponseEqualsWrongObject() {
		assertFalse(emptyResp.equals(new String(emptyResp.toString())));
	}

	@Test
	public void testResponseClone() {
		Empty.Response clone = emptyResp.clone();
		assertEquals(emptyResp.toString(), clone.toString());
		assertEquals(emptyResp.toJsonObject(), clone.toJsonObject());
		assertEquals(emptyResp.getServiceResponseType(),
				clone.getServiceResponseType());
		assertNotSame(emptyResp, clone);
		assertNotSame(emptyResp.toString(), clone.toString());
		assertNotSame(emptyResp.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testResponseFromJsonString() {
		Empty.Response resp = Empty.Response.fromJsonString(emptyResp
				.toString());
		assertEquals(emptyResp.toString(), resp.toString());
		assertEquals(emptyResp.toJsonObject(), resp.toJsonObject());
		assertEquals(emptyResp.getServiceResponseType(),
				resp.getServiceResponseType());
		assertNotSame(emptyResp, resp);
		assertNotSame(emptyResp.toString(), resp.toString());
		assertNotSame(emptyResp.toJsonObject(), resp.toJsonObject());
	}

	@Test
	public void testResponseFromServiceResponse() {
		ServiceResponse sr = new ServiceResponse(emptyResp.toString(), false);
		Empty.Response resp = Empty.Response.fromServiceResponse(sr);
		assertEquals(emptyResp.toString(), resp.toString());
		assertEquals(emptyResp.toJsonObject(), resp.toJsonObject());
		assertEquals(emptyResp.getServiceResponseType(),
				resp.getServiceResponseType());
		assertNotSame(emptyResp, resp);
		assertNotSame(emptyResp.toString(), resp.toString());
		assertNotSame(emptyResp.toJsonObject(), resp.toJsonObject());
	}

	@Test
	public void testResponseFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder().build();
		Empty.Response resp = Empty.Response.fromJsonObject(jsonObject);
		assertEquals(emptyResp.toString(), resp.toString());
		assertEquals(emptyResp.toJsonObject(), resp.toJsonObject());
		assertEquals(emptyResp.getServiceResponseType(),
				resp.getServiceResponseType());
		assertNotSame(emptyResp, resp);
		assertNotSame(emptyResp.toString(), resp.toString());
		assertNotSame(emptyResp.toJsonObject(), resp.toJsonObject());
	}
}
