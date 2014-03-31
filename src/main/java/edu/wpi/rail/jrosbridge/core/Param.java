package edu.wpi.rail.jrosbridge.core;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.core.callback.ParamCallback;
import edu.wpi.rail.jrosbridge.core.callback.ServiceCallback;
import edu.wpi.rail.jrosbridge.services.ServiceRequest;
import edu.wpi.rail.jrosbridge.services.ServiceResponse;

/**
 * The Param object can be used to make calls to rosapi which can get, set, and
 * delete parameters from the ROS parameter server. Calls are asynchronous.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version Feb. 18, 2014
 */
public class Param {

	/**
	 * The name of the delete parameter service.
	 */
	private static final String DELETE_PARAM_SERVICE = "/rosapi/delete_param";

	/**
	 * The type of the delete parameter service.
	 */
	private static final String DELETE_PARAM_SERVICE_TYPE = "/rosapi/DeleteParam";

	/**
	 * The name of the get parameter service.
	 */
	private static final String GET_PARAM_SERVICE = "/rosapi/get_param";

	/**
	 * The type of the get parameter service.
	 */
	private static final String GET_PARAM_SERVICE_TYPE = "/rosapi/GetParam";

	/**
	 * The name of the set parameter service.
	 */
	private static final String SET_PARAM_SERVICE = "/rosapi/set_param";

	/**
	 * The type of the set parameter service.
	 */
	private static final String SET_PARAM_SERVICE_TYPE = "/rosapi/SetParam";

	/**
	 * The name field for the rosapi service calls.
	 */
	private static final String FIELD_NAME = "name";

	/**
	 * The value field for the rosapi service calls.
	 */
	private static final String FIELD_VALUE = "value";

	private Ros ros;
	private String name;

	/**
	 * Create a new Param with the given information.
	 * 
	 * @param ros
	 *            A handle to the ROS connection.
	 * @param name
	 *            The name of the parameter.
	 */
	public Param(Ros ros, String name) {
		this.ros = ros;
		this.name = name;
	}

	/**
	 * Get the name of the parameter.
	 * 
	 * @return The name of the parameter.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get the value of the parameter from the ROS parameter server. This call
	 * is made asynchronously.
	 * 
	 * @param cb
	 *            The callback used when rosapi returns the value of the
	 *            parameter.
	 */
	public void get(final ParamCallback cb) {
		// create a service client for this call
		Service s = new Service(this.ros, Param.GET_PARAM_SERVICE,
				Param.GET_PARAM_SERVICE_TYPE);

		// create the request
		JsonObject json = Json.createObjectBuilder()
				.add(Param.FIELD_NAME, this.name).build();
		ServiceRequest req = new ServiceRequest(json);

		// call the service
		s.callService(req, new ServiceCallback() {
			/**
			 * Handle the response from rosapi.
			 * 
			 * @param response
			 *            The response from rosapi.
			 */
			@Override
			public void handleServiceResponse(ServiceResponse response) {
				// if (success) {
				// // extract the value
				// String value = response.toJsonObject().getString(
				// FIELD_VALUE);
				// cb.handleParameter(value);
				// } else {
				// // failed call
				// cb.handleParameter("");
				// }
			}
		});
	}

	/**
	 * Set the value of the parameter in the ROS parameter server. This call is
	 * made asynchronously.
	 * 
	 * @param value
	 *            The value to set the parameter to.
	 */
	public void set(String value) {
		// create a service client for this call
		Service s = new Service(this.ros, Param.SET_PARAM_SERVICE,
				Param.SET_PARAM_SERVICE_TYPE);

		// create the request
		JsonObject json = Json.createObjectBuilder()
				.add(Param.FIELD_NAME, this.name)
				.add(Param.FIELD_VALUE, "\"" + value + "\"").build();
		ServiceRequest req = new ServiceRequest(json);

		// call the service
		s.callService(req, new ServiceCallback() {
			/**
			 * The response from rosapi is empty and is thus ignored.
			 * 
			 * @param response
			 *            The response from rosapi which is empty and ignored.
			 */
			@Override
			public void handleServiceResponse(ServiceResponse response) {
				// do nothing
			}
		});
	}

	/**
	 * Delete the parameter from the ROS parameter server. This call is made
	 * asynchronously.
	 */
	public void delete() {
		// create a service client for this call
		Service s = new Service(this.ros, Param.DELETE_PARAM_SERVICE,
				Param.DELETE_PARAM_SERVICE_TYPE);

		// create the request
		JsonObject json = Json.createObjectBuilder()
				.add(Param.FIELD_NAME, this.name).build();
		ServiceRequest req = new ServiceRequest(json);

		// call the service
		s.callService(req, new ServiceCallback() {
			/**
			 * The response from rosapi is empty and is thus ignored.
			 * 
			 * @param response
			 *            The response from rosapi which is empty and ignored.
			 */
			@Override
			public void handleServiceResponse(ServiceResponse response) {
				// do nothing
			}
		});
	}
}
