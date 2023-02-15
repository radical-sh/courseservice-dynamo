package in.krishi.courseservice.events;

import in.krishi.courseservice.entities.Context;
import in.krishi.courseservice.inputs.UpdateCourseInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseUpdateAcceptedPayload {

	Context context;

	String courseId;

	UpdateCourseInput updateCourseInput;

	public CourseUpdateAcceptedPayload(
		Context context,
		String courseId,
		UpdateCourseInput updateCourseInput
	) {
		this.context = context;
		this.courseId = courseId;
		this.updateCourseInput = updateCourseInput;
	}
}
