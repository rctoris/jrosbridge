package edu.wpi.rail.jrosbridge;

/**
 * The JRosbridge class contains constant definitions used in the rosbridge
 * protocol itself (e.g., op code types).
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version Feb. 4, 2014
 */
public class JRosbridge {

	/**
	 * The ID field for the rosbridge protocol.
	 */
	public static final String FIELD_ID = "id";

	/**
	 * The op code field for the rosbridge protocol.
	 */
	public static final String FIELD_OP = "op";

	/**
	 * The topic field for the rosbridge protocol.
	 */
	public static final String FIELD_TOPIC = "topic";

	/**
	 * The message/service type field for the rosbridge protocol.
	 */
	public static final String FIELD_TYPE = "type";

	/**
	 * The message data field for the rosbridge protocol.
	 */
	public static final String FIELD_MESSAGE = "msg";

	/**
	 * The advertise op code for the rosbridge protocol.
	 */
	public static final String OP_CODE_ADVERTISE = "advertise";

	/**
	 * The publish op code for the rosbridge protocol.
	 */
	public static final String OP_CODE_PUBLISH = "publish";

	/**
	 * The subscribe op code for the rosbridge protocol.
	 */
	public static final String OP_CODE_SUBSCRIBE = "subscribe";

	/**
	 * The unadvertise op code for the rosbridge protocol.
	 */
	public static final String OP_CODE_UNADVERTISE = "unadvertise";

	/**
	 * The unsubscribe op code for the rosbridge protocol.
	 */
	public static final String OP_CODE_UNSUBSCRIBE = "unsubscribe";
}
