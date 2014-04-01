package edu.wpi.rail.jrosbridge;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.JsonWrapper;

public class TestJsonWrapper {

	private JsonWrapper empty, m1, m2;

	@Before
	public void setUp() {
		empty = new DummyJsonWrapper();
		m1 = new DummyJsonWrapper("{\"test\" : 123, \"test2\" : \"abc\"}");
		m2 = new DummyJsonWrapper(Json.createObjectBuilder().add("test", 123)
				.add("test2", "abc").build());
	}

	@Test
	public void testConstructor() {
		assertEquals(JsonWrapper.EMPTY_JSON, empty.toString());
		assertEquals(0, empty.toJsonObject().size());
		assertNull(empty.clone());
	}

	@Test
	public void testStringConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", m1.toString());
		assertEquals(2, m1.toJsonObject().size());
		assertEquals(123, m1.toJsonObject().getInt("test"));
		assertEquals("abc", m1.toJsonObject().getString("test2"));
		assertNull(m1.clone());
		assertNull(m2.clone());
	}

	@Test
	public void testJsonObjectConstructor() {
		assertEquals("{\"test\":123,\"test2\":\"abc\"}", m2.toString());
		assertEquals(2, m2.toJsonObject().size());
		assertEquals(123, m2.toJsonObject().getInt("test"));
		assertEquals("abc", m2.toJsonObject().getString("test2"));
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(m1.toString().hashCode(), m1.hashCode());
		assertEquals(m2.toString().hashCode(), m2.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(m1));
		assertFalse(m1.equals(empty));
		assertFalse(empty.equals(m2));
		assertFalse(m2.equals(empty));

		assertTrue(m1.equals(m2));
		assertTrue(m2.equals(m1));

		assertTrue(empty.equals(empty));
		assertTrue(m1.equals(m1));
		assertTrue(m2.equals(m2));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(JsonWrapper.EMPTY_JSON)));
	}

	private class DummyJsonWrapper extends JsonWrapper {
		public DummyJsonWrapper() {
			super();
		}

		public DummyJsonWrapper(String jsonString) {
			super(jsonString);
		}

		public DummyJsonWrapper(JsonObject jsonObject) {
			super(jsonObject);
		}

		public JsonWrapper clone() {
			return null;
		}
	}
}
