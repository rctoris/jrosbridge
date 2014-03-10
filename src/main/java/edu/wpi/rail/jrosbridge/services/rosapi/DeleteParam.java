package edu.wpi.rail.jrosbridge.services.rosapi;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.services.ServiceRequest;
import edu.wpi.rail.jrosbridge.services.ServiceResponse;

/**
 * The rosapi/DeleteParam service.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 4, 2014
 */
public class DeleteParam {

	/**
	 * The service type.
	 */
	public static final String TYPE = "rosapi/DeleteParam";

	/**
	 * The service request for the DeleteParam service.
	 * 
	 * @author Russell Toris -- rctoris@wpi.edu
	 * @version March 4, 2014
	 */
	public static class Request extends ServiceRequest {

		/**
		 * The name of the name field for the request.
		 */
		public static final String FIELD_NAME = "name";

		private String name;

		/**
		 * Create a new DeleteParam ServiceRequest with empty fields.
		 */
		public Request() {
			this("");
		}

		public Request(String name) {
			// build the JSON object
			super(Json.createObjectBuilder()
					.add(DeleteParam.Request.FIELD_NAME, name).build(),
					DeleteParam.TYPE);
			this.name = name;
		}

		/**
		 * Get the name value of this resquest.
		 * 
		 * @return The name value of this resquest.
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * Create a clone of this DeleteParam ServiceRequest.
		 */
		@Override
		public Request clone() {
			return new DeleteParam.Request(this.name);
		}
	}

	/**
	 * The service response for the DeleteParam service.
	 * 
	 * @author Russell Toris -- rctoris@wpi.edu
	 * @version March 4, 2014
	 */
	public static class Response extends ServiceResponse {

		/**
		 * Create a new DeleteParam ServiceResponse.
		 */
		public Response() {
			super(ServiceResponse.EMPTY_MESSAGE, DeleteParam.TYPE);
		}

		/**
		 * Create a clone of this DeleteParam ServiceResponse.
		 */
		@Override
		public Response clone() {
			return new DeleteParam.Response();
		}
	}
}
