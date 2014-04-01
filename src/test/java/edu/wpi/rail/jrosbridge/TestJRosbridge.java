package edu.wpi.rail.jrosbridge;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestJRosbridge {

	@Test
	public void testEnums() {
		new JRosbridge();
		assertEquals(2, JRosbridge.CompressionType.values().length);
		assertEquals(JRosbridge.CompressionType.png,
				JRosbridge.CompressionType.valueOf("png"));
		assertEquals(JRosbridge.CompressionType.none,
				JRosbridge.CompressionType.valueOf("none"));
		assertEquals(2, JRosbridge.WebSocketType.values().length);
		assertEquals(JRosbridge.WebSocketType.ws,
				JRosbridge.WebSocketType.valueOf("ws"));
		assertEquals(JRosbridge.WebSocketType.wss,
				JRosbridge.WebSocketType.valueOf("wss"));
	}

}
