jrosbridge [![Build Status](https://api.travis-ci.org/WPI-RAIL/jrosbridge.png)](https://travis-ci.org/WPI-RAIL/jrosbridge)
==========

#### A Native Java EE rosbridge Client
This project is released as part of the [Robot Web Tools](http://robotwebtools.org/) effort.

### Example Usage

```java
public static void main(String[] args) throws InterruptedException {
	Ros ros = new Ros("localhost");
	ros.connect();

	Topic echo = new Topic(ros, "/echo", "std_msgs/String");
	Message toSend = new Message("{\"data\": \"hello, world!\"}");
	echo.publish(toSend);

	Topic echoBack = new Topic(ros, "/echo_back", "std_msgs/String");
	echoBack.subscribe(new TopicCallback() {
		@Override
		public void handleMessage(Message message) {
			System.out.println("From ROS: " + message.toString());
		}
	});

	Service addTwoInts = new Service(ros, "/add_two_ints", "rospy_tutorials/AddTwoInts");

	ServiceRequest request = new ServiceRequest("{\"a\": 10, \"b\": 20}");

	addTwoInts.callService(request, new ServiceCallback() {
		@Override
		public void handleServiceResponse(ServiceResponse response,
				boolean success) {
			if (success) {
				System.out.println(response.toString());
			} else {
				System.err.println("ROS service returned with a failure.");
			}
		}
	});

	Thread.sleep(1000);
	ros.disconnect();
}
```

### License
jrosbridge is released with a BSD license. For full terms and conditions, see the [LICENSE](LICENSE) file.

### Authors
See the [AUTHORS](AUTHORS.md) file for a full list of contributors.
