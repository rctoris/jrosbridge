jrosbridge [![Build Status](https://api.travis-ci.org/rctoris/jrosbridge.png)](https://travis-ci.org/rctoris/jrosbridge)
==========

#### A Native Java EE rosbridge Client

### Example Usage

To include this library, use the following in your Maven configuration:

```
<dependency>
    <groupId>edu.wpi.rail</groupId>
    <artifactId>jrosbridge</artifactId>
    <version>0.2.0</version>
</dependency>
```


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
	ServiceResponse response = addTwoInts.callServiceAndWait(request);
	System.out.println(response.toString());
	
	ros.disconnect();
}
```

### License
jrosbridge is released with a BSD license. For full terms and conditions, see the [LICENSE](LICENSE) file.

### Authors
See the [AUTHORS](AUTHORS.md) file for a full list of contributors.
