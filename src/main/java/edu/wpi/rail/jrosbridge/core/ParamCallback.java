package edu.wpi.rail.jrosbridge.core;

/**
 * The ParamCallback interface defines a single method which will be called when
 * an incoming response is received a rosapi call.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version Feb. 18, 2014
 */
public interface ParamCallback {

	/**
	 * Handle the response from the rosapi get parameter call.
	 * 
	 * @param param
	 *            The value of the returned parameter.
	 */
	public void handleResponse(String param);
}
