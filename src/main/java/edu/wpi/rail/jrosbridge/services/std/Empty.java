package edu.wpi.rail.jrosbridge.services.std;

import edu.wpi.rail.jrosbridge.services.ServiceRequest;
import edu.wpi.rail.jrosbridge.services.ServiceResponse;

/**
 * The std_srvs/Empty service.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version February 26, 2014
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
	 * @version February 26, 2014
	 */
	public static class Request extends ServiceRequest {

		/**
		 * Create a new Empty ServiceRequest.
		 */
		public Request() {
			super(ServiceRequest.EMPTY_MESSAGE, Empty.TYPE);
		}

		/**
		 * Create a deep clone of this Empty ServiceRequest.
		 */
		@Override
		public Request clone() {
			return new Empty.Request();
		}
	}

	/**
	 * The service response for the Empty service.
	 * 
	 * @author Russell Toris -- rctoris@wpi.edu
	 * @version February 26, 2014
	 */
	public static class Response extends ServiceResponse {

		/**
		 * Create a new Empty ServiceResponse.
		 */
		public Response() {
			super(ServiceResponse.EMPTY_MESSAGE, Empty.TYPE);
		}

		/**
		 * Create a deep clone of this Empty ServiceResponse.
		 */
		@Override
		public Response clone() {
			return new Empty.Response();
		}
	}
}
