package edu.wpi.rail.jrosbridge;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDummyServer {

	@Test
	public void testConnect() {
		DummyServer server = new DummyServer(9091);
		assertTrue(server.start());
	}

	@Test
	public void testConnectInvalid() {
		DummyServer server = new DummyServer(-9091);
		assertFalse(server.start());
	}
}
