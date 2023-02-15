package sh.radical.courseservice.events;

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
