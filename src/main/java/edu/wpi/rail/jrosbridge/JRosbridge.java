package edu.wpi.rail.jrosbridge;

/**
 * The JRosbridge class contains constant definitions used in the rosbridge
 * protocol itself (e.g., op code types).
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version Feb. 16, 2014
 */
public class JRosbridge {

	/**
	 * The args field for the rosbridge protocol.
	 */
	public static final String FIELD_ARGS = "args";

	/**
	 * The ID field for the rosbridge protocol.
	 */
	public static final String FIELD_ID = "id";

	/**
	 * The message data field for the rosbridge protocol.
	 */
	public static final String FIELD_MESSAGE = "msg";

	/**
	 * The op code field for the rosbridge protocol.
	 */
	public static final String FIELD_OP = "op";

	/**
	 * The result field for the rosbridge protocol.
	 */
	public static final String FIELD_RESULT = "result";

	/**
	 * The service field for the rosbridge protocol.
	 */
	public static final String FIELD_SERVICE = "service";

	/**
	 * The topic field for the rosbridge protocol.
	 */
	public static final String FIELD_TOPIC = "topic";

	/**
	 * The message/service type field for the rosbridge protocol.
	 */
	public static final String FIELD_TYPE = "type";

	/**
	 * The values field for the rosbridge protocol.
	 */
	public static final String FIELD_VALUES = "values";

	/**
	 * The advertise op code for the rosbridge protocol.
	 */
	public static final String OP_CODE_ADVERTISE = "advertise";

	/**
	 * The call service op code for the rosbridge protocol.
	 */
	public static final String OP_CODE_CALL_SERVICE = "call_service";

	/**
	 * The publish op code for the rosbridge protocol.
	 */
	public static final String OP_CODE_PUBLISH = "publish";

	/**
	 * The service response op code for the rosbridge protocol.
	 */
	public static final String OP_CODE_SERVICE_RESPONSE = "service_response";

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
