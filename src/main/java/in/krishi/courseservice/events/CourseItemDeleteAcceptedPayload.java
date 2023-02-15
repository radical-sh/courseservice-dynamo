package in.krishi.courseservice.events;

import in.krishi.courseservice.entities.Context;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseItemDeleteAcceptedPayload {

	Context context;

	String courseItemId;

	public CourseItemDeleteAcceptedPayload(
		Context context,
		String courseItemId
	) {
		this.context = context;
		this.courseItemId = courseItemId;
	}
}
