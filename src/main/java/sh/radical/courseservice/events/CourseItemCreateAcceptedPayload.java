package sh.radical.courseservice.events;

import sh.radical.courseservice.entities.Context;
import sh.radical.courseservice.inputs.CreateCourseItemInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseItemCreateAcceptedPayload {

	Context context;

	CreateCourseItemInput createCourseItemInput;

	String acknowledgedCourseItemId;

	public CourseItemCreateAcceptedPayload(
		Context context,
		CreateCourseItemInput createCourseItemInput,
		String acknowledgedCourseItemId
	) {
		this.context = context;
		this.createCourseItemInput = createCourseItemInput;
		this.acknowledgedCourseItemId = acknowledgedCourseItemId;
	}
}
