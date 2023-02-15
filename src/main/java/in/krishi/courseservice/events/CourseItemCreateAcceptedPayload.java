package in.krishi.courseservice.events;

import in.krishi.courseservice.entities.Context;
import in.krishi.courseservice.inputs.CreateCourseItemInput;
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
