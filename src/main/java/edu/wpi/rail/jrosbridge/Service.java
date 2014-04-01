package edu.wpi.rail.jrosbridge;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.callback.ServiceCallback;
import edu.wpi.rail.jrosbridge.services.ServiceRequest;
import edu.wpi.rail.jrosbridge.services.ServiceResponse;

/**
 * The Service object is responsible for calling a service in ROS.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version April 1, 2014
 */
public class Service {

	private final Ros ros;
	private final String name;
	private final String type;

	/**
	 * Create a ROS service with the given information.
	 * 
	 * @param ros
	 *            A handle to the ROS connection.
	 * @param name
	 *            The name of the service (e.g., "/add_two_ints").
	 * @param type
	 *            The service type (e.g., "rospy_tutorials/AddTwoInts").
	 */
	public Service(Ros ros, String name, String type) {
		this.ros = ros;
		this.name = name;
		this.type = type;
	}

	/**
	 * Get the ROS connection handle for this service.
	 * 
	 * @return The ROS connection handle for this service.
	 */
	public Ros getRos() {
		return this.ros;
	}

	/**
	 * Get the name of this service.
	 * 
	 * @return The name of this service.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the service type of this service.
	 * 
	 * @return The service type of this service.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Call this service. The callback function will be called with the
	 * associated service response.
	 * 
	 * @param request
	 *            The service request to send.
	 * @param cb
	 *            The callback used when the associated response comes back.
	 */
	public void callService(ServiceRequest request, ServiceCallback cb) {
		// construct the unique ID
		String callServceId = "call_service:" + this.name + ":"
				+ this.ros.nextId();

		// register the callback function
		this.ros.registerServiceCallback(callServceId, cb);

		// build and send the rosbridge call
		JsonObject call = Json.createObjectBuilder()
				.add(JRosbridge.FIELD_OP, JRosbridge.OP_CODE_CALL_SERVICE)
				.add(JRosbridge.FIELD_ID, callServceId)
				.add(JRosbridge.FIELD_TYPE, this.type)
				.add(JRosbridge.FIELD_SERVICE, this.name)
				.add(JRosbridge.FIELD_ARGS, request.toJsonObject()).build();
		this.ros.send(call);
	}

	/**
	 * Call the service and wait for a response. This is a blocking call and
	 * will only return once rosbridge returns the service response. For an
	 * asynchronous version of this call, see the
	 * {@link #callService(ServiceRequest request, ServiceCallback cb)
	 * callService} method.
	 * 
	 * @param request
	 *            The service request to send.
	 * @return The corresponding service response from ROS.
	 */
	public synchronized ServiceResponse callServiceAndWait(
			ServiceRequest request) {

		// private inner class to use as a callback
		BlockingCallback cb = new BlockingCallback(this);
		// use the asynchronous version and block on the result
		this.callService(request, cb);

		// wait for a response
		while (cb.getResponse() == null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// continue on
			}
		}

		return cb.getResponse();
	}

	/**
	 * A private {@link edu.wpi.rail.jrosbridge.callback.ServiceCallback
	 * ServiceCallback} used to block and wait for a response from rosbridge.
	 * 
	 * @author Russell Toris - rctoris@wpi.edu
	 * @version April 1, 2014
	 */
	private class BlockingCallback implements ServiceCallback {

		private ServiceResponse response;
		private Service service;

		/**
		 * Create a new callback function which will notify the given
		 * {@link edu.wpi.rail.jrosbridge.Service Service} once a response
		 * has been received.
		 * 
		 * @param service
		 *            The {@link edu.wpi.rail.jrosbridge.Service Service}
		 *            to notify once a response has been received.
		 */
		public BlockingCallback(Service service) {
			this.response = null;
			this.service = service;
		}

		/**
		 * Store the response internally and notify the corresponding
		 * {@link edu.wpi.rail.jrosbridge.Service Service}.
		 * 
		 * @param respose
		 *            The incoming service response from ROS.
		 */
		@Override
		public void handleServiceResponse(ServiceResponse response) {
			this.response = response;
			synchronized (this.service) {
				this.service.notifyAll();
			}
		}

		/**
		 * Get the response stored in this callback, if one exists. Otherwise,
		 * null is returned.
		 * 
		 * @return The resulting service response from ROS, or null if one does
		 *         not exist yet.
		 */
		public ServiceResponse getResponse() {
			return this.response;
		}
	}
}
