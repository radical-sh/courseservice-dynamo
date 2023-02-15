package in.krishi.courseservice.events;

import in.krishi.courseservice.events.BaseEvent;
import in.krishi.courseservice.events.CourseCreationAcceptedPayload;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseCreationAccepted
	extends BaseEvent<CourseCreationAcceptedPayload> {

	public CourseCreationAccepted(CourseCreationAcceptedPayload data) {
		super(data);
		this.eventType =
			"in.krishi.courseservice.events.CourseCreationAccepted";
	}
}
