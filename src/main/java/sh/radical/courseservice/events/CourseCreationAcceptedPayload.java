package sh.radical.courseservice.events;

import sh.radical.courseservice.entities.Context;
import sh.radical.courseservice.inputs.CreateCourseInput;
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
