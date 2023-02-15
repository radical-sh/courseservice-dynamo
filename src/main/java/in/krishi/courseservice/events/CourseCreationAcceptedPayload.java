package in.krishi.courseservice.events;

import in.krishi.courseservice.entities.Context;
import in.krishi.courseservice.inputs.CreateCourseInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreationAcceptedPayload {

	Context context;

	CreateCourseInput createCourseInput;

	String acknowledgedCourseId;

	public CourseCreationAcceptedPayload(
		Context context,
		CreateCourseInput createCourseInput,
		String acknowledgedCourseId
	) {
		this.context = context;
		this.createCourseInput = createCourseInput;
		this.acknowledgedCourseId = acknowledgedCourseId;
	}
}
