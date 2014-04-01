package edu.wpi.rail.jrosbridge;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/")
public class DummyHandler {

	public static JsonObject latest = null;

	@OnMessage
	public String onMessage(String message) {
		JsonObject json = Json.createReader(new StringReader(message))
				.readObject();
		if (json.containsKey("echo")) {
			return json.getString("echo");
		} else {
			DummyHandler.latest = json;
			return "";
		}
	}
}
