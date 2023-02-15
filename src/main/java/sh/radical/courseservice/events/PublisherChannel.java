package sh.radical.courseservice.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface PublisherChannel {
	@Output(value = "courseevents")
	SubscribableChannel courseEventsChannel();

	@Output(value = "courseitemevents")
	SubscribableChannel courseItemEventsChannel();
}
