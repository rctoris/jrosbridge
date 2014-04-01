package edu.wpi.rail.jrosbridge.services;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.JsonWrapper;

/**
 * ServiceResponse objects are used for making a response to a service. These
 * service responses act as wrappers around JSON objects. Service response data
 * is immutable.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version April 1, 2014
 */
public class ServiceResponse extends JsonWrapper {

	/**
	 * The String representation of an empty service response in JSON.
	 */
	public static final String EMPTY_MESSAGE = JsonWrapper.EMPTY_JSON;

	private String serviceResponseType;
	private final boolean result;

	/**
	 * Create a new, empty service response. The type will be set to the empty
	 * string.
	 */
	public ServiceResponse() {
		this(ServiceResponse.EMPTY_MESSAGE, "", true);
	}

	/**
	 * Create a ServiceResponse based on the given String representation of a
	 * JSON object. The type will be set to the empty string.
	 * 
	 * @param jsonString
	 *            The JSON String to parse.
	 * @param result
	 *            The result flag for the response (i.e., if the service server
	 *            returned a success).
	 */
	public ServiceResponse(String jsonString, boolean result) {
		this(jsonString, "", result);
	}

	/**
	 * Create a ServiceResponse based on the given String representation of a
	 * JSON object.
	 * 
	 * @param jsonString
	 *            The JSON String to parse.
	 * @param serviceResponseType
	 *            The type of the service response (e.g., "std_srvs/Empty").
	 * @param result
	 *            The result flag for the response (i.e., if the service server
	 *            returned a success).
	 */
	public ServiceResponse(String jsonString, String serviceResponseType,
			boolean result) {
		// parse and pass it to the JSON constructor
		this(Json.createReader(new StringReader(jsonString)).readObject(),
				serviceResponseType, result);
	}

	/**
	 * Create a ServiceResponse based on the given JSON object. The type will be
	 * set to the empty string.
	 * 
	 * @param jsonObject
	 *            The JSON object containing the service response data.
	 * @param result
	 *            The result flag for the response (i.e., if the service server
	 *            returned a success).
	 */
	public ServiceResponse(JsonObject jsonObject, boolean result) {
		// setup the JSON information
		this(jsonObject, "", result);
	}

	/**
	 * Create a ServiceResponse based on the given JSON object.
	 * 
	 * @param jsonObject
	 *            The JSON object containing the service response data.
	 * @param serviceResponseType
	 *            The type of the service response (e.g., "std_srvs/Empty").
	 * @param result
	 *            The result flag for the response (i.e., if the service server
	 *            returned a success).
	 */
	public ServiceResponse(JsonObject jsonObject, String serviceResponseType,
			boolean result) {
		// setup the JSON information
		super(jsonObject);
		// set the type
		this.serviceResponseType = serviceResponseType;
		this.result = result;
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
	 * Get the result flag of this response (i.e., if the service server
	 * returned a success).
	 * 
	 * @return The result flag for the response.
	 */
	public boolean getResult() {
		return this.result;
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
	 * Create a clone of this ServiceResponse.
	 */
	@Override
	public ServiceResponse clone() {
		return new ServiceResponse(this.toJsonObject(),
				this.serviceResponseType, this.result);
	}
}
