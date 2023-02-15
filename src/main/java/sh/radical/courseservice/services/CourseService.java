package sh.radical.courseservice.services;

import sh.radical.courseservice.entities.Context;
import sh.radical.courseservice.events.CourseCreationAccepted;
import sh.radical.courseservice.events.CourseCreationAcceptedPayload;
import sh.radical.courseservice.events.CourseDeleteAccepted;
import sh.radical.courseservice.events.CourseDeleteAcceptedPayload;
import sh.radical.courseservice.events.CourseUpdateAccepted;
import sh.radical.courseservice.events.CourseUpdateAcceptedPayload;
import sh.radical.courseservice.events.EventPublisher;
import sh.radical.courseservice.exceptions.CourseNotFound;
import sh.radical.courseservice.inputs.CreateCourseInput;
import sh.radical.courseservice.inputs.UpdateCourseInput;
import sh.radical.courseservice.mappers.CourseMapper;
import sh.radical.courseservice.models.Course;
import sh.radical.courseservice.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	EventPublisher eventPublisher;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CourseMapper courseMapper;

	public void delete(Context context, String courseId) {
		Course existingCourse = courseRepository.findById(courseId).get();

		if (existingCourse == null) {
			throw new CourseNotFound();
		}
		eventPublisher.sendCourseEvent(
			new CourseDeleteAccepted(
				new CourseDeleteAcceptedPayload(context, courseId)
			),
			courseId
		);
	}

	public void handleCourseDeleteAccepted(Context context, String courseId) {
		Course existingCourse = courseRepository.findById(courseId).get();

		if (existingCourse == null) {
			throw new CourseNotFound();
		}
		courseRepository.deleteById(courseId);
	}

	public void update(
		Context context,
		String courseId,
		UpdateCourseInput updateCourseInput
	) {
		Course existingCourse = courseRepository.findById(courseId).get();

		if (existingCourse == null) {
			throw new CourseNotFound();
		}
		eventPublisher.sendCourseEvent(
			new CourseUpdateAccepted(
				new CourseUpdateAcceptedPayload(
					context,
					courseId,
					updateCourseInput
				)
			),
			courseId
		);
	}

	public Course handleCourseUpdateAccepted(
		Context context,
		String courseId,
		UpdateCourseInput updateCourseInput
	) {
		Course existingCourse = courseRepository.findById(courseId).get();

		if (existingCourse == null) {
			throw new CourseNotFound();
		}
		Course updatedCourse = courseMapper.updateCourse(
			context,
			courseId,
			updateCourseInput,
			existingCourse
		);
		Course savedCourse = courseRepository.save(updatedCourse);
		return savedCourse;
	}

	public void create(
		Context context,
		CreateCourseInput createCourseInput,
		String courseId
	) {
		eventPublisher.sendCourseEvent(
			new CourseCreationAccepted(
				new CourseCreationAcceptedPayload(
					context,
					createCourseInput,
					courseId
				)
			),
			courseId
		);
	}

	public Course handleCourseCreationAccepted(
		Context context,
		CreateCourseInput createCourseInput,
		String courseId
	) {
		Course course = courseMapper.createCourse(
			context,
			createCourseInput,
			courseId
		);
		Course createdCourse = courseRepository.save(course);
		return createdCourse;
	}

	public Course get(Context context, String courseId) {
		Course existingCourse = courseRepository.findById(courseId).get();
		if (existingCourse == null) {
			throw new CourseNotFound();
		}
		return existingCourse;
	}
}
