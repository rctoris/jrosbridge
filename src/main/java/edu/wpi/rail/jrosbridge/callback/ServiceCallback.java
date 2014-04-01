package edu.wpi.rail.jrosbridge.callback;

import edu.wpi.rail.jrosbridge.services.ServiceResponse;

/**
 * The ServiceCallback interface defines a single method which will be called
 * when an incoming service response is received for an associated service
 * request.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version April 1, 2014
 */
public interface ServiceCallback {

	/**
	 * This function is called when an incoming service response is received for
	 * a given service request. No ROS type checking is done on the internal
	 * data. A flag indicating if the call was successful is given.
	 * 
	 * @param response
	 *            The service response that was received.
	 */
	public void handleServiceResponse(ServiceResponse response);
}
