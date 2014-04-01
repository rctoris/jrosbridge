package edu.wpi.rail.jrosbridge.services;

import static org.junit.Assert.*;

import javax.json.Json;

import org.junit.Before;
import org.junit.Test;

public class TestServiceResponse {

	private ServiceResponse empty, s1, s2, s3, s4;

	@Before
	public void setUp() {
		empty = new ServiceResponse();
		s1 = new ServiceResponse("{\"test\" : 123, \"test2\" : \"abc\"}", false);
		s2 = new ServiceResponse(Json.createObjectBuilder().add("test", 123)
				.add("test2", "abc").build(), false);
		s3 = new ServiceResponse("{\"test\" : 123, \"test2\" : \"abc\"}",
				"type", false);
		s4 = new ServiceResponse(Json.createObjectBuilder().add("test", 123)
				.add("test2", "abc").build(), "type", false);
	}

	@Test
	public void testConstructor() {
		assertEquals(ServiceResponse.EMPTY_MESSAGE, empty.toString());
		assertEquals(0, empty.toJsonObject().size());
		assertEquals("", empty.getServiceResponseType());
		assertTrue(empty.getResult());
	}

	@Test
	public void testStringConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", s1.toString());
		assertEquals(2, s1.toJsonObject().size());
		assertEquals(123, s1.toJsonObject().getInt("test"));
		assertEquals("abc", s1.toJsonObject().getString("test2"));
		assertEquals("", s1.getServiceResponseType());
		assertFalse(s1.getResult());
	}

	@Test
	public void testStringAndStringConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", s3.toString());
		assertEquals(2, s3.toJsonObject().size());
		assertEquals(123, s3.toJsonObject().getInt("test"));
		assertEquals("abc", s3.toJsonObject().getString("test2"));
		assertEquals("type", s3.getServiceResponseType());
		assertFalse(s3.getResult());
	}

	@Test
	public void testJsonObjectConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", s2.toString());
		assertEquals(2, s2.toJsonObject().size());
		assertEquals(123, s2.toJsonObject().getInt("test"));
		assertEquals("abc", s2.toJsonObject().getString("test2"));
		assertEquals("", s2.getServiceResponseType());
		assertFalse(s2.getResult());
	}

	@Test
	public void testJsonObjectAndStringConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", s4.toString());
		assertEquals(2, s4.toJsonObject().size());
		assertEquals(123, s4.toJsonObject().getInt("test"));
		assertEquals("abc", s4.toJsonObject().getString("test2"));
		assertEquals("type", s4.getServiceResponseType());
		assertFalse(s4.getResult());
	}

	@Test
	public void testSetServiceResponseType() {
		empty.setServiceResponseType("test");
		assertEquals("test", empty.getServiceResponseType());
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
		ServiceResponse clone = s3.clone();
		assertEquals(s3.toString(), clone.toString());
		assertEquals(s3.toJsonObject(), clone.toJsonObject());
		assertEquals(s3.getServiceResponseType(),
				clone.getServiceResponseType());
		assertEquals(s3.getResult(), clone.getResult());
		assertNotSame(s3, clone);
		assertNotSame(s3.toString(), clone.toString());
	}

}
