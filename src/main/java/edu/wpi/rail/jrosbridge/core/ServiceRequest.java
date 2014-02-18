package edu.wpi.rail.jrosbridge.core;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * ServiceRequest objects are used for making a request to a service. These
 * service requests act as wrappers around JSON objects.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version Feb. 16, 2014
 */
public class ServiceRequest {

	private JsonObject jsonObject;
	
	/**
	 * Create a new, empty service request.
	 */
	public ServiceRequest() {
		this("");
	}

	/**
	 * Create a ServiceRequest based on the given String representation of a
	 * JSON object.
	 * 
	 * @param json
	 *            The JSON String to parse.
	 */
	public ServiceRequest(String json) {
		// parse and pass it to the JSON constructor
		this(Json.createReader(new StringReader(json)).readObject());
	}

	/**
	 * Create a ServiceRequest based on the given JSON object.
	 * 
	 * @param jsonObject
	 *            The JSON object containing the message data.
	 */
	public ServiceRequest(JsonObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	/**
	 * Get this service request as a JSON object.
	 * 
	 * @return The service request as a JSON object.
	 */
	public JsonObject toJsonObject() {
		return this.jsonObject;
	}

	/**
	 * Get the String representation of this service request in JSON format.
	 * 
	 * @return The String representation of this service request in JSON format.
	 */
	@Override
	public String toString() {
		return this.jsonObject.toString();
	}
}
