package edu.wpi.rail.jrosbridge.services.std;

import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.services.ServiceRequest;
import edu.wpi.rail.jrosbridge.services.ServiceResponse;

/**
 * The std_srvs/Empty service.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Empty {

	/**
	 * The service type.
	 */
	public static final String TYPE = "std_srvs/Empty";

	/**
	 * The service request for the Empty service.
	 * 
	 * @author Russell Toris -- rctoris@wpi.edu
	 * @version April 1, 2014
	 */
	public static class Request extends ServiceRequest {

		/**
		 * Create a new Empty ServiceRequest.
		 */
		public Request() {
			super(ServiceRequest.EMPTY_MESSAGE, Empty.TYPE);
		}

		/**
		 * Create a clone of this Empty ServiceRequest.
		 */
		@Override
		public Request clone() {
			return new Empty.Request();
		}

		/**
		 * Create a new Empty ServiceRequest based on the given JSON string. Any
		 * missing values will be set to their defaults.
		 * 
		 * @param jsonString
		 *            The JSON string to parse.
		 * @return A Empty ServiceRequest based on the given JSON string.
		 */
		public static Request fromJsonString(String jsonString) {
			// convert to a ServiceRequest
			return Empty.Request.fromServiceRequest(new ServiceRequest(
					jsonString));
		}

		/**
		 * Create a new Empty ServiceRequest based on the given ServiceRequest.
		 * Any missing values will be set to their defaults.
		 * 
		 * @param m
		 *            The ServiceRequest to parse.
		 * @return A Empty ServiceRequest based on the given Message.
		 */
		public static Request fromServiceRequest(ServiceRequest req) {
			// get it from the JSON object
			return Empty.Request.fromJsonObject(req.toJsonObject());
		}

		/**
		 * Create a new Empty ServiceRequest based on the given JSON object. Any
		 * missing values will be set to their defaults.
		 * 
		 * @param jsonObject
		 *            The JSON object to parse.
		 * @return A Empty ServiceRequest based on the given JSON object.
		 */
		public static Request fromJsonObject(JsonObject jsonObject) {
			return new Empty.Request();
		}
	}

	/**
	 * The service response for the Empty service.
	 * 
	 * @author Russell Toris -- rctoris@wpi.edu
	 * @version April 1, 2014
	 */
	public static class Response extends ServiceResponse {

		/**
		 * Create a new Empty ServiceResponse.
		 */
		public Response() {
			this(true);
		}

		/**
		 * Create a new Empty ServiceResponse.
		 * 
		 * @param result
		 *            The result flag for the response (i.e., if the service
		 *            server returned a success).
		 */
		public Response(boolean result) {
			super(ServiceResponse.EMPTY_MESSAGE, Empty.TYPE, result);
		}

		/**
		 * Create a clone of this Empty ServiceResponse.
		 */
		@Override
		public Response clone() {
			return new Empty.Response();
		}

		/**
		 * Create a new Empty ServiceResponse based on the given JSON string.
		 * Any missing values will be set to their defaults.
		 * 
		 * @param jsonString
		 *            The JSON string to parse.
		 * @return A Empty ServiceResponse based on the given JSON string.
		 */
		public static Response fromJsonString(String jsonString) {
			// convert to a ServiceResponse
			return Empty.Response.fromServiceResponse(new ServiceResponse(
					jsonString, true));
		}

		/**
		 * Create a new Empty ServiceResponse based on the given
		 * ServiceResponse. Any missing values will be set to their defaults.
		 * 
		 * @param m
		 *            The ServiceResponse to parse.
		 * @return A Empty ServiceResponse based on the given Message.
		 */
		public static Response fromServiceResponse(ServiceResponse resp) {
			// get it from the JSON object
			return Empty.Response.fromJsonObject(resp.toJsonObject());
		}

		/**
		 * Create a new Empty ServiceResponse based on the given JSON object.
		 * Any missing values will be set to their defaults.
		 * 
		 * @param jsonObject
		 *            The JSON object to parse.
		 * @return A Empty ServiceResponse based on the given JSON object.
		 */
		public static Response fromJsonObject(JsonObject jsonObject) {
			return new Empty.Response();
		}
	}
}
