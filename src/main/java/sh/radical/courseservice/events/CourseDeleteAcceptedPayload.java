package sh.radical.courseservice.events;

import sh.radical.courseservice.entities.Context;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDeleteAcceptedPayload {

	Context context;

	String courseId;

	public CourseDeleteAcceptedPayload(Context context, String courseId) {
		this.context = context;
		this.courseId = courseId;
	}
}
