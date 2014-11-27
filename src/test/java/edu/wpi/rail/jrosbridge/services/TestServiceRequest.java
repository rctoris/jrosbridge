package edu.wpi.rail.jrosbridge.services;

import static org.junit.Assert.*;

import javax.json.Json;

import org.junit.Before;
import org.junit.Test;

public class TestServiceRequest {

	private ServiceRequest empty, s1, s2, s3, s4;

	@Before
	public void setUp() {
		empty = new ServiceRequest();
		s1 = new ServiceRequest("{\"test\" : 123, \"test2\" : \"abc\"}");
		s2 = new ServiceRequest(Json.createObjectBuilder().add("test", 123)
				.add("test2", "abc").build());
		s3 = new ServiceRequest("{\"test\" : 123, \"test2\" : \"abc\"}", "type");
		s4 = new ServiceRequest(Json.createObjectBuilder().add("test", 123)
				.add("test2", "abc").build(), "type");
	}

	@Test
	public void testConstructor() {
		assertEquals(ServiceRequest.EMPTY_MESSAGE, empty.toString());
		assertEquals(0, empty.toJsonObject().size());
		assertEquals("", empty.getServiceRequestType());
		assertEquals("", empty.getId());
	}

	@Test
	public void testStringConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", s1.toString());
		assertEquals(2, s1.toJsonObject().size());
		assertEquals(123, s1.toJsonObject().getInt("test"));
		assertEquals("abc", s1.toJsonObject().getString("test2"));
		assertEquals("", s1.getServiceRequestType());
		assertEquals("", s1.getId());
	}

	@Test
	public void testStringAndStringConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", s3.toString());
		assertEquals(2, s3.toJsonObject().size());
		assertEquals(123, s3.toJsonObject().getInt("test"));
		assertEquals("abc", s3.toJsonObject().getString("test2"));
		assertEquals("type", s3.getServiceRequestType());
		assertEquals("", s3.getId());
	}

	@Test
	public void testJsonObjectConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", s2.toString());
		assertEquals(2, s2.toJsonObject().size());
		assertEquals(123, s2.toJsonObject().getInt("test"));
		assertEquals("abc", s2.toJsonObject().getString("test2"));
		assertEquals("", s2.getServiceRequestType());
		assertEquals("", s2.getId());
	}

	@Test
	public void testJsonObjectAndStringConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", s4.toString());
		assertEquals(2, s4.toJsonObject().size());
		assertEquals(123, s4.toJsonObject().getInt("test"));
		assertEquals("abc", s4.toJsonObject().getString("test2"));
		assertEquals("type", s4.getServiceRequestType());
		assertEquals("", s4.getId());
	}

	@Test
	public void testSetId() {
		empty.setId("test");
		assertEquals("test", empty.getId());
	}

	@Test
	public void testSetServiceRequestType() {
		empty.setServiceRequestType("test");
		assertEquals("test", empty.getServiceRequestType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(s1.toString().hashCode(), s1.hashCode());
		assertEquals(s2.toString().hashCode(), s2.hashCode());
		assertEquals(s3.toString().hashCode(), s3.hashCode());
		assertEquals(s4.toString().hashCode(), s4.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(s1));
		assertFalse(s1.equals(empty));
		assertFalse(empty.equals(s2));
		assertFalse(s2.equals(empty));
		assertFalse(empty.equals(s3));
		assertFalse(s3.equals(empty));
		assertFalse(empty.equals(s4));
		assertFalse(s4.equals(empty));

		assertTrue(s1.equals(s2));
		assertTrue(s1.equals(s3));
		assertTrue(s1.equals(s4));
		assertTrue(s2.equals(s1));
		assertTrue(s2.equals(s3));
		assertTrue(s2.equals(s4));
		assertTrue(s3.equals(s1));
		assertTrue(s3.equals(s2));
		assertTrue(s3.equals(s4));
		assertTrue(s4.equals(s1));
		assertTrue(s4.equals(s2));
		assertTrue(s4.equals(s3));

		assertTrue(empty.equals(empty));
		assertTrue(s1.equals(s1));
		assertTrue(s2.equals(s2));
		assertTrue(s3.equals(s3));
		assertTrue(s4.equals(s4));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		ServiceRequest clone = s3.clone();
		assertEquals(s3.toString(), clone.toString());
		assertEquals(s3.toJsonObject(), clone.toJsonObject());
		assertEquals(s3.getServiceRequestType(), clone.getServiceRequestType());
		assertNotSame(s3, clone);
		assertNotSame(s3.toString(), clone.toString());
	}

}
