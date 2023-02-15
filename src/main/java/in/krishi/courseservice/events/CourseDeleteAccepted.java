package in.krishi.courseservice.events;

import in.krishi.courseservice.events.BaseEvent;
import in.krishi.courseservice.events.CourseDeleteAcceptedPayload;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseDeleteAccepted
	extends BaseEvent<CourseDeleteAcceptedPayload> {

	public CourseDeleteAccepted(CourseDeleteAcceptedPayload data) {
		super(data);
		this.eventType = "in.krishi.courseservice.events.CourseDeleteAccepted";
	}
}
