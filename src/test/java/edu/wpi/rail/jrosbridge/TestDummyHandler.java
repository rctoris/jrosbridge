package edu.wpi.rail.jrosbridge;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class TestDummyHandler {
	
	@After
	public void tearDown() {
		DummyHandler.latest = null;
	}

	@Test
	public void testOnMessage() {
		DummyHandler h = new DummyHandler();
		assertNull(DummyHandler.latest);
		assertEquals("test", h.onMessage("{\"echo\":\"test\"}"));
		assertNull(DummyHandler.latest);
		assertEquals("", h.onMessage("{\"no-echo\":\"test\"}"));
		assertNotNull(DummyHandler.latest);
		assertEquals(1, DummyHandler.latest.size());
		assertTrue(DummyHandler.latest.containsKey("no-echo"));
		assertEquals("test", DummyHandler.latest.getString("no-echo"));
	}
}
