package in.krishi.courseservice.services;

import in.krishi.courseservice.entities.Context;
import in.krishi.courseservice.events.CourseCreationAccepted;
import in.krishi.courseservice.events.CourseCreationAcceptedPayload;
import in.krishi.courseservice.events.CourseDeleteAccepted;
import in.krishi.courseservice.events.CourseDeleteAcceptedPayload;
import in.krishi.courseservice.events.CourseUpdateAccepted;
import in.krishi.courseservice.events.CourseUpdateAcceptedPayload;
import in.krishi.courseservice.events.EventPublisher;
import in.krishi.courseservice.exceptions.CourseNotFound;
import in.krishi.courseservice.inputs.CreateCourseInput;
import in.krishi.courseservice.inputs.UpdateCourseInput;
import in.krishi.courseservice.mappers.CourseMapper;
import in.krishi.courseservice.models.Course;
import in.krishi.courseservice.repositories.CourseRepository;
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
