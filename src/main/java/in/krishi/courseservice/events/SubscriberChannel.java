package in.krishi.courseservice.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface SubscriberChannel {
	@Input(value = "courseevents")
	SubscribableChannel courseEventsChannel();

	@Input(value = "courseitemevents")
	SubscribableChannel courseItemEventsChannel();
}
