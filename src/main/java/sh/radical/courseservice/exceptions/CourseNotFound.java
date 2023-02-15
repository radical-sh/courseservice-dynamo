package sh.radical.courseservice.exceptions;

import java.lang.Throwable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CourseNotFound extends CourseserviceCustomException {

	public CourseNotFound() {
		super();
	}

	public CourseNotFound(String message) {
		super(message);
	}

	public CourseNotFound(String message, Throwable t) {
		super(message, t);
	}
}
