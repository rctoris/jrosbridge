package edu.wpi.rail.jrosbridge.handler;

import javax.websocket.Session;

/**
 * The RosHandler interface defines the methods that will be called during
 * certain events in the Ros connection object.
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version April 1, 2014
 */
public interface RosHandler {

	/**
	 * Handle the connection event. This occurs during a successful connection
	 * to rosbridge.
	 * 
	 * @param session
	 *            The session associated with the connection.
	 */
	public void handleConnection(Session session);

	/**
	 * Handle the disconnection event. This occurs during a successful
	 * disconnection from rosbridge.
	 * 
	 * @param session
	 *            The session associated with the disconnection.
	 */
	public void handleDisconnection(Session session);

	/**
	 * Handle the error event.
	 * 
	 * @param session
	 *            The session associated with the error.
	 * @param t
	 *            The error.
	 */
	public void handleError(Session session, Throwable t);
}
