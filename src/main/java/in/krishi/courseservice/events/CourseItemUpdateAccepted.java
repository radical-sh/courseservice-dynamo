package in.krishi.courseservice.events;

import in.krishi.courseservice.events.BaseEvent;
import in.krishi.courseservice.events.CourseItemUpdateAcceptedPayload;
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
