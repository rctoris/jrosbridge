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

	Thread.sleep(1000);
	ros.disconnect();
}
```

### License
jrosbridge is released with a BSD license. For full terms and conditions, see the [LICENSE](LICENSE) file.

### Authors
See the [AUTHORS](AUTHORS.md) file for a full list of contributors.
