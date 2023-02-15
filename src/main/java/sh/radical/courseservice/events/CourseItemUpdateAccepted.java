package sh.radical.courseservice.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseItemUpdateAccepted
	extends BaseEvent<CourseItemUpdateAcceptedPayload> {

	public CourseItemUpdateAccepted(CourseItemUpdateAcceptedPayload data) {
		super(data);
		this.eventType =
			"in.krishi.courseservice.events.CourseItemUpdateAccepted";
	}
}
