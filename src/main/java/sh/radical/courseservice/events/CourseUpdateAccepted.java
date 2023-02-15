package sh.radical.courseservice.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseUpdateAccepted
	extends BaseEvent<CourseUpdateAcceptedPayload> {

	public CourseUpdateAccepted(CourseUpdateAcceptedPayload data) {
		super(data);
		this.eventType = "in.krishi.courseservice.events.CourseUpdateAccepted";
	}
}
