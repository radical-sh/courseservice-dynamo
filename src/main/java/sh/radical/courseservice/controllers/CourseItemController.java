package sh.radical.courseservice.controllers;

import sh.radical.courseservice.entities.Context;
import sh.radical.courseservice.inputs.CreateCourseItemInput;
import sh.radical.courseservice.inputs.UpdateCourseItemInput;
import sh.radical.courseservice.models.CourseItem;
import sh.radical.courseservice.services.CourseItemService;
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
@RequestMapping(value = "/v2/courseitems")
public class CourseItemController {

	@Autowired
    CourseItemService courseItemService;

	@DeleteMapping(value = "/{courseItemId}")
	public void delete(
		@PathVariable(value = "courseItemId") String courseItemId
	) {
		log.info("Received a delete request for CourseItem {} ", courseItemId);
		Context context = new Context();
		courseItemService.delete(context, courseItemId);
		log.info("Delete request completed for CourseItem {} ", courseItemId);
	}

	@PutMapping(value = "/{courseItemId}")
	public ResponseEntity update(
		@PathVariable(value = "courseItemId") String courseItemId,
		@RequestBody UpdateCourseItemInput updateCourseItemInput
	) {
		log.info("Received a update request for CourseItem {} ", courseItemId);
		Context context = new Context();
		courseItemService.update(context, courseItemId, updateCourseItemInput);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/" + courseItemId);
		log.info("Update request for CourseItem {} is complete", courseItemId);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity create(
		@RequestBody CreateCourseItemInput createCourseItemInput
	) {
		Context context = new Context();
		log.info("Received a new create request");
		var id = CourseItem.getNewCourseItemId();
		courseItemService.create(context, createCourseItemInput, id);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "courseitems/" + id);
		log.info("Create request for CourseItem - {} is complete", id);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/{courseItemId}")
	public CourseItem get(
		@PathVariable(value = "courseItemId") String courseItemId
	) {
		log.info("Received a get request for CourseItem {} ", courseItemId);
		Context context = new Context();
		CourseItem existingCourseItem = courseItemService.get(
			context,
			courseItemId
		);
		log.info("Get request for CourseItem {} is complete ", courseItemId);
		return existingCourseItem;
	}
}
