package sh.radical.courseservice.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(value = PublisherChannel.class)
public class EventPublisher {

	@Autowired
	PublisherChannel publishChannel;

	private static final <T extends BaseEvent> Message<T> message(
		T val,
		String key
	) {
		return MessageBuilder
			.withPayload(val)
			.setHeader(KafkaHeaders.MESSAGE_KEY, key)
			.setHeader("eventType", val.eventType)
			.build();
	}

	public void sendCourseEvent(BaseEvent event, String key) {
		publishChannel.courseEventsChannel().send(message(event, key));
	}

	public void sendCourseItemEvent(BaseEvent event, String key) {
		publishChannel.courseItemEventsChannel().send(message(event, key));
	}
}
