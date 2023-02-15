package in.krishi.courseservice.events;

import in.krishi.courseservice.events.BaseEvent;
import in.krishi.courseservice.events.CourseItemDeleteAcceptedPayload;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseItemDeleteAccepted
	extends BaseEvent<CourseItemDeleteAcceptedPayload> {

	public CourseItemDeleteAccepted(CourseItemDeleteAcceptedPayload data) {
		super(data);
		this.eventType =
			"in.krishi.courseservice.events.CourseItemDeleteAccepted";
	}
}
