package edu.wpi.rail.jrosbridge.core;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * Message objects are used for publishing and subscribing to and from topics.
 * These messages act as wrappers around JSON objects.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version Feb. 4, 2014
 */
public class Message {

	private JsonObject jsonObject;

	/**
	 * Create a Message based on the given String representation of a JSON
	 * object.
	 * 
	 * @param json
	 *            The JSON String to parse.
	 */
	public Message(String json) {
		// parse and pass it to the JSON constructor
		this(Json.createReader(new StringReader(json)).readObject());
	}

	/**
	 * Create a Message based on the given JSON object.
	 * 
	 * @param jsonObject
	 *            The JSON object containing the message data.
	 */
	public Message(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	/**
	 * Get this message as a JSON object.
	 * 
	 * @return The message as a JSON object.
	 */
	public JsonObject toJsonObject() {
		return this.jsonObject;
	}

	/**
	 * Get the String representation of this message in JSON format.
	 * 
	 * @return The String representation of this message in JSON format.
	 */
	@Override
	public String toString() {
		return this.jsonObject.toString();
	}
}
