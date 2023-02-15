package in.krishi.courseservice.events;

import in.krishi.courseservice.entities.Context;
import in.krishi.courseservice.inputs.UpdateCourseItemInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseItemUpdateAcceptedPayload {

	Context context;

	String courseItemId;

	UpdateCourseItemInput updateCourseItemInput;

	public CourseItemUpdateAcceptedPayload(
		Context context,
		String courseItemId,
		UpdateCourseItemInput updateCourseItemInput
	) {
		this.context = context;
		this.courseItemId = courseItemId;
		this.updateCourseItemInput = updateCourseItemInput;
	}
}
