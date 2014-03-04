package edu.wpi.rail.jrosbridge.services;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.core.JsonWrapper;

/**
 * ServiceResponse objects are used for making a response to a service. These
 * service responses act as wrappers around JSON objects. Service response data
 * is immutable.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version Feb. 26, 2014
 */
public class ServiceResponse extends JsonWrapper {

	/**
	 * The String representation of an empty service response in JSON.
	 */
	public static final String EMPTY_MESSAGE = JsonWrapper.EMPTY_JSON;

	private String serviceResponseType;

	/**
	 * Create a new, empty service response. The type will be set to the empty
	 * string.
	 */
	public ServiceResponse() {
		this(ServiceResponse.EMPTY_MESSAGE, "");
	}

	/**
	 * Create a ServiceResponse based on the given String representation of a
	 * JSON object. The type will be set to the empty string.
	 * 
	 * @param jsonString
	 *            The JSON String to parse.
	 */
	public ServiceResponse(String jsonString) {
		this(jsonString, "");
	}

	/**
	 * Create a ServiceResponse based on the given String representation of a
	 * JSON object.
	 * 
	 * @param jsonString
	 *            The JSON String to parse.
	 * @param serviceResponseType
	 *            The type of the service response (e.g., "std_srvs/Empty").
	 */
	public ServiceResponse(String jsonString, String serviceResponseType) {
		// parse and pass it to the JSON constructor
		this(Json.createReader(new StringReader(jsonString)).readObject(),
				serviceResponseType);
	}

	/**
	 * Create a ServiceResponse based on the given JSON object. The type will be
	 * set to the empty string.
	 * 
	 * @param jsonObject
	 *            The JSON object containing the service response data.
	 */
	public ServiceResponse(JsonObject jsonObject) {
		// setup the JSON information
		this(jsonObject, "");
	}

	/**
	 * Create a ServiceResponse based on the given JSON object.
	 * 
	 * @param jsonObject
	 *            The JSON object containing the service response data.
	 * @param serviceResponseType
	 *            The type of the service response (e.g., "std_srvs/Empty").
	 */
	public ServiceResponse(JsonObject jsonObject, String serviceResponseType) {
		// setup the JSON information
		super(jsonObject);
		// set the type
		this.serviceResponseType = serviceResponseType;
	}

	/**
	 * Get the type of the service response if one was set.
	 * 
	 * @return The type of the service response.
	 */
	public String getServiceResponseType() {
		return this.serviceResponseType;
	}

	/**
	 * Set the type of the service response.
	 * 
	 * @param serviceResponseType
	 *            The type of the service response (e.g., "std_srvs/Empty").
	 */
	public void setServiceResponseType(String serviceResponseType) {
		this.serviceResponseType = serviceResponseType;
	}

	/**
	 * Create a deep clone of this ServiceResponse.
	 */
	@Override
	public ServiceResponse clone() {
		// use the string to make sure we get an entirely new JSON object
		return new ServiceResponse(this.toString(), this.serviceResponseType);
	}
}
