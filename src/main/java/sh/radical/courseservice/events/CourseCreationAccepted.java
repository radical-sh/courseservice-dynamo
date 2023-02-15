package sh.radical.courseservice.events;

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
