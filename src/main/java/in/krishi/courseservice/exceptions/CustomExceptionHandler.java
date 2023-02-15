package in.krishi.courseservice.exceptions;

import in.krishi.courseservice.exceptions.CourseItemNotFound;
import in.krishi.courseservice.exceptions.CourseNotFound;
import java.lang.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = CourseNotFound.class)
	public ResponseEntity handleCourseNotFound(CourseNotFound exception) {
		return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CourseItemNotFound.class)
	public ResponseEntity handleCourseItemNotFound(
		CourseItemNotFound exception
	) {
		return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity genericExceptionHandler(Exception exception) {
		return new ResponseEntity(
			"Internal Server Error",
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
}
