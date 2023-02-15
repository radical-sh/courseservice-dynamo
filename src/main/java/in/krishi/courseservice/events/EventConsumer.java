package in.krishi.courseservice.events;

import in.krishi.courseservice.events.CourseCreationAccepted;
import in.krishi.courseservice.events.CourseDeleteAccepted;
import in.krishi.courseservice.events.CourseItemCreateAccepted;
import in.krishi.courseservice.events.CourseItemDeleteAccepted;
import in.krishi.courseservice.events.CourseItemUpdateAccepted;
import in.krishi.courseservice.events.CourseUpdateAccepted;
import in.krishi.courseservice.events.SubscriberChannel;
import in.krishi.courseservice.services.CourseItemService;
import in.krishi.courseservice.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(value = SubscriberChannel.class)
@Slf4j
public class EventConsumer {

	@Autowired
	CourseService courseService;

	@Autowired
	CourseItemService courseItemService;

	@StreamListener(
		value = "courseevents",
		condition = "headers['eventType'] == 'in.krishi.courseservice.events.CourseDeleteAccepted'"
	)
	public void handleCourseDeleteAccepted(CourseDeleteAccepted event) {
		try {
			log.info("Consumed event of course {} ", event.data.courseId);
			courseService.handleCourseDeleteAccepted(
				event.data.context,
				event.data.courseId
			);
			log.info(
				"Processing complete for event course {}",
				event.data.courseId
			);
		} catch (Exception e) {
			log.error(
				"Error while processing for Course {} ",
				event.data.courseId,
				e
			);
			throw e;
		}
	}

	@StreamListener(
		value = "courseevents",
		condition = "headers['eventType'] == 'in.krishi.courseservice.events.CourseUpdateAccepted'"
	)
	public void handleCourseUpdateAccepted(CourseUpdateAccepted event) {
		try {
			log.info("Consumed event of course {} ", event.data.courseId);
			courseService.handleCourseUpdateAccepted(
				event.data.context,
				event.data.courseId,
				event.data.updateCourseInput
			);
			log.info(
				"Processing complete for event course {}",
				event.data.courseId
			);
		} catch (Exception e) {
			log.error(
				"Error while processing for Course {} ",
				event.data.courseId,
				e
			);
			throw e;
		}
	}

	@StreamListener(
		value = "courseevents",
		condition = "headers['eventType'] == 'in.krishi.courseservice.events.CourseCreationAccepted'"
	)
	public void handleCourseCreationAccepted(CourseCreationAccepted event) {
		try {
			log.info(
				"Consumed event of course {} ",
				event.data.acknowledgedCourseId
			);
			courseService.handleCourseCreationAccepted(
				event.data.context,
				event.data.createCourseInput,
				event.data.acknowledgedCourseId
			);
			log.info(
				"Processing complete for event course {}",
				event.data.acknowledgedCourseId
			);
		} catch (Exception e) {
			log.error(
				"Error while processing for Course {} ",
				event.data.acknowledgedCourseId,
				e
			);
			throw e;
		}
	}

	@StreamListener(
		value = "courseitemevents",
		condition = "headers['eventType'] == 'in.krishi.courseservice.events.CourseItemDeleteAccepted'"
	)
	public void handleCourseItemDeleteAccepted(CourseItemDeleteAccepted event) {
		try {
			log.info(
				"Consumed event of courseItem {} ",
				event.data.courseItemId
			);
			courseItemService.handleCourseItemDeleteAccepted(
				event.data.context,
				event.data.courseItemId
			);
			log.info(
				"Processing complete for event courseItem {}",
				event.data.courseItemId
			);
		} catch (Exception e) {
			log.error(
				"Error while processing for CourseItem {} ",
				event.data.courseItemId,
				e
			);
			throw e;
		}
	}

	@StreamListener(
		value = "courseitemevents",
		condition = "headers['eventType'] == 'in.krishi.courseservice.events.CourseItemUpdateAccepted'"
	)
	public void handleCourseItemUpdateAccepted(CourseItemUpdateAccepted event) {
		try {
			log.info(
				"Consumed event of courseItem {} ",
				event.data.courseItemId
			);
			courseItemService.handleCourseItemUpdateAccepted(
				event.data.context,
				event.data.courseItemId,
				event.data.updateCourseItemInput
			);
			log.info(
				"Processing complete for event courseItem {}",
				event.data.courseItemId
			);
		} catch (Exception e) {
			log.error(
				"Error while processing for CourseItem {} ",
				event.data.courseItemId,
				e
			);
			throw e;
		}
	}

	@StreamListener(
		value = "courseitemevents",
		condition = "headers['eventType'] == 'in.krishi.courseservice.events.CourseItemCreateAccepted'"
	)
	public void handleCourseItemCreateAccepted(CourseItemCreateAccepted event) {
		try {
			log.info(
				"Consumed event of courseItem {} ",
				event.data.acknowledgedCourseItemId
			);
			courseItemService.handleCourseItemCreateAccepted(
				event.data.context,
				event.data.createCourseItemInput,
				event.data.acknowledgedCourseItemId
			);
			log.info(
				"Processing complete for event courseItem {}",
				event.data.acknowledgedCourseItemId
			);
		} catch (Exception e) {
			log.error(
				"Error while processing for CourseItem {} ",
				event.data.acknowledgedCourseItemId,
				e
			);
			throw e;
		}
	}
}
