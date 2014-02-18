package edu.wpi.rail.jrosbridge.core;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.JRosbridge;
import edu.wpi.rail.jrosbridge.core.callback.ServiceCallback;

/**
 * The Service object is responsible for calling a service in ROS.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version Feb. 16, 2014
 */
public class Service {

	private Ros ros;
	private String name;
	private String type;

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
}
