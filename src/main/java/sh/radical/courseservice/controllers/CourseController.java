package sh.radical.courseservice.controllers;

import sh.radical.courseservice.entities.Context;
import sh.radical.courseservice.inputs.CreateCourseInput;
import sh.radical.courseservice.inputs.UpdateCourseInput;
import sh.radical.courseservice.models.Course;
import sh.radical.courseservice.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v2/courses")
public class CourseController {

	@Autowired
    CourseService courseService;

	@DeleteMapping(value = "/{courseId}")
	public void delete(@PathVariable(value = "courseId") String courseId) {
		log.info("Received a delete request for Course {} ", courseId);
		Context context = new Context();
		courseService.delete(context, courseId);
		log.info("Delete request completed for Course {} ", courseId);
	}

	@PutMapping(value = "/{courseId}")
	public ResponseEntity update(
		@PathVariable(value = "courseId") String courseId,
		@RequestBody UpdateCourseInput updateCourseInput
	) {
		log.info("Received a update request for Course {} ", courseId);
		Context context = new Context();
		courseService.update(context, courseId, updateCourseInput);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/" + courseId);
		log.info("Update request for Course {} is complete", courseId);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity create(
		@RequestBody CreateCourseInput createCourseInput
	) {
		Context context = new Context();
		log.info("Received a new create request");
		var id = Course.getNewCourseId();
		courseService.create(context, createCourseInput, id);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "courses/" + id);
		log.info("Create request for Course - {} is complete", id);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/{courseId}")
	public Course get(@PathVariable(value = "courseId") String courseId) {
		log.info("Received a get request for Course {} ", courseId);
		Context context = new Context();
		Course existingCourse = courseService.get(context, courseId);
		log.info("Get request for Course {} is complete ", courseId);
		return existingCourse;
	}
}
