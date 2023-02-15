package sh.radical.courseservice.exceptions;

import java.lang.RuntimeException;
import java.lang.Throwable;

public class CourseserviceCustomException extends RuntimeException {

	public CourseserviceCustomException() {
		super();
	}

	public CourseserviceCustomException(String message) {
		super(message);
	}

	public CourseserviceCustomException(String message, Throwable t) {
		super(message, t);
	}
}
