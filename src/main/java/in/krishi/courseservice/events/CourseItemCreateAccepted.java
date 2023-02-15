package in.krishi.courseservice.events;

import in.krishi.courseservice.events.BaseEvent;
import in.krishi.courseservice.events.CourseItemCreateAcceptedPayload;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseItemCreateAccepted
	extends BaseEvent<CourseItemCreateAcceptedPayload> {

	public CourseItemCreateAccepted(CourseItemCreateAcceptedPayload data) {
		super(data);
		this.eventType =
			"in.krishi.courseservice.events.CourseItemCreateAccepted";
	}
}
