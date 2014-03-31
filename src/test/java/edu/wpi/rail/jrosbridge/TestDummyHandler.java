package edu.wpi.rail.jrosbridge;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDummyHandler {

	@Test
	public void testOnMessage() {
		DummyHandler h = new DummyHandler();
		assertNull(DummyHandler.lastMessage);
		assertEquals("test", h.onMessage("{\"echo\":\"test\"}"));
		assertNull(DummyHandler.lastMessage);
		assertEquals("", h.onMessage("{\"no-echo\":\"test\"}"));
		assertNotNull(DummyHandler.lastMessage);
		assertEquals(1, DummyHandler.lastMessage.size());
		assertTrue(DummyHandler.lastMessage.containsKey("no-echo"));
		assertEquals("test", DummyHandler.lastMessage.getString("no-echo"));
	}
}
