package edu.wpi.rail.jrosbridge.callback;

import edu.wpi.rail.jrosbridge.services.ServiceRequest;

/**
 * The CallServiceCallback interface defines a single method which will be called
 * when an incoming service request is received for an associated service request.
 * 
 * @author Russell Toris - russell.toris@gmail.com
 * @version November 26, 2014
 */
public interface CallServiceCallback {

	/**
	 * This function is called when an incoming service request is received for
	 * a given service request. No ROS type checking is done on the internal
	 * data.
	 *
	 * @param request
	 *            The service request that was received.
	 */
	public void handleServiceCall(ServiceRequest request);
}
