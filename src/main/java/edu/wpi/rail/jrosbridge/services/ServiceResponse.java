package edu.wpi.rail.jrosbridge.services;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * ServiceResponse objects are used for making a request to a service. These
 * service responses act as wrappers around JSON objects.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version Feb. 16, 2014
 */
public class ServiceResponse {

	private JsonObject jsonObject;

	/**
	 * Create a new, empty service response.
	 */
	public ServiceResponse() {
		this("");
	}

	/**
	 * Create a ServiceResponse based on the given String representation of a
	 * JSON object.
	 * 
	 * @param json
	 *            The JSON String to parse.
	 */
	public ServiceResponse(String json) {
		// parse and pass it to the JSON constructor
		this(Json.createReader(new StringReader(json)).readObject());
	}

	/**
	 * Create a ServiceResponse based on the given JSON object.
	 * 
	 * @param jsonObject
	 *            The JSON object containing the message data.
	 */
	public ServiceResponse(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	/**
	 * Get this service response as a JSON object.
	 * 
	 * @return The service response as a JSON object.
	 */
	public JsonObject toJsonObject() {
		return this.jsonObject;
	}

	/**
	 * Get the String representation of this service response in JSON format.
	 * 
	 * @return The String representation of this service response in JSON
	 *         format.
	 */
	@Override
	public String toString() {
		return this.jsonObject.toString();
	}
}
