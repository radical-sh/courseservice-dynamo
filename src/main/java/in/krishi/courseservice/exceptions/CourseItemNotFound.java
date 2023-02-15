package in.krishi.courseservice.exceptions;

import in.krishi.courseservice.exceptions.CourseserviceCustomException;
import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CourseItemNotFound extends CourseserviceCustomException {

	public CourseItemNotFound() {
		super();
	}

	public CourseItemNotFound(String message) {
		super(message);
	}

	public CourseItemNotFound(String message, Throwable t) {
		super(message, t);
	}
}
