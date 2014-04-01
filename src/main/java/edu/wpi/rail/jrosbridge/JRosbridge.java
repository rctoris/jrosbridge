package edu.wpi.rail.jrosbridge;

/**
 * The JRosbridge class contains constant definitions used in the rosbridge
 * protocol itself (e.g., op code types).
 * 
 * @author Russell Toris - rctoris@wpi.edu
 * @version April 1, 2014
 */
public class JRosbridge {

	/**
	 * The args field for the rosbridge protocol.
	 */
	public static final String FIELD_ARGS = "args";

	/**
	 * The client field for the rosbridge protocol.
	 */
	public static final String FIELD_CLIENT = "client";

	/**
	 * The compression field for the rosbridge protocol.
	 */
	public static final String FIELD_COMPRESSION = "compression";

	/**
	 * The data field for the rosbridge protocol.
	 */
	public static final String FIELD_DATA = "data";

	/**
	 * The destination field for the rosbridge protocol.
	 */
	public static final String FIELD_DESTINATION = "dest";

	/**
	 * The end time field for the rosbridge protocol.
	 */
	public static final String FIELD_END_TIME = "end";

	/**
	 * The ID field for the rosbridge protocol.
	 */
	public static final String FIELD_ID = "id";

	/**
	 * The user level field for the rosbridge protocol.
	 */
	public static final String FIELD_LEVEL = "level";

	/**
	 * The MAC field for the rosbridge protocol.
	 */
	public static final String FIELD_MAC = "mac";

	/**
	 * The message data field for the rosbridge protocol.
	 */
	public static final String FIELD_MESSAGE = "msg";

	/**
	 * The op code field for the rosbridge protocol.
	 */
	public static final String FIELD_OP = "op";

	/**
	 * The random field for the rosbridge protocol.
	 */
	public static final String FIELD_RAND = "rand";

	/**
	 * The result field for the rosbridge protocol.
	 */
	public static final String FIELD_RESULT = "result";

	/**
	 * The service field for the rosbridge protocol.
	 */
	public static final String FIELD_SERVICE = "service";

	/**
	 * The throttle rate field for the rosbridge protocol.
	 */
	public static final String FIELD_THROTTLE_RATE = "throttle_rate";

	/**
	 * The time field for the rosbridge protocol.
	 */
	public static final String FIELD_TIME = "t";

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
	 * The authenticate op code for the rosbridge protocol.
	 */
	public static final String OP_CODE_AUTH = "auth";

	/**
	 * The call service op code for the rosbridge protocol.
	 */
	public static final String OP_CODE_CALL_SERVICE = "call_service";

	/**
	 * The png compression op code for the rosbridge protocol.
	 */
	public static final String OP_CODE_PNG = "png";

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

	/**
	 * The types of websocket protocols supported by jrosbridge and rosbridge.
	 * 
	 * @author Russell Toris - rctoris@wpi.edu
	 * @version April 1, 2014
	 */
	public enum WebSocketType {
		ws, wss
	}

	/**
	 * The types of compression supported by jrosbridge and rosbridge.
	 * 
	 * @author Russell Toris - rctoris@wpi.edu
	 * @version April 1, 2014
	 */
	public enum CompressionType {
		png, none
	}
}
