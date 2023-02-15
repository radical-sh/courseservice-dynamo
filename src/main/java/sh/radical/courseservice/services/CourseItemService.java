package sh.radical.courseservice.services;

import sh.radical.courseservice.entities.Context;
import sh.radical.courseservice.events.CourseItemCreateAccepted;
import sh.radical.courseservice.events.CourseItemCreateAcceptedPayload;
import sh.radical.courseservice.events.CourseItemDeleteAccepted;
import sh.radical.courseservice.events.CourseItemDeleteAcceptedPayload;
import sh.radical.courseservice.events.CourseItemUpdateAccepted;
import sh.radical.courseservice.events.CourseItemUpdateAcceptedPayload;
import sh.radical.courseservice.events.EventPublisher;
import sh.radical.courseservice.exceptions.CourseItemNotFound;
import sh.radical.courseservice.inputs.CreateCourseItemInput;
import sh.radical.courseservice.inputs.UpdateCourseItemInput;
import sh.radical.courseservice.mappers.CourseItemMapper;
import sh.radical.courseservice.models.CourseItem;
import sh.radical.courseservice.repositories.CourseItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseItemService {

	@Autowired
	EventPublisher eventPublisher;

	@Autowired
	CourseItemRepository courseItemRepository;

	@Autowired
	CourseItemMapper courseItemMapper;

	public void delete(Context context, String courseItemId) {
		CourseItem existingCourseItem = courseItemRepository
			.findById(courseItemId)
			.get();

		if (existingCourseItem == null) {
			throw new CourseItemNotFound();
		}
		eventPublisher.sendCourseItemEvent(
			new CourseItemDeleteAccepted(
				new CourseItemDeleteAcceptedPayload(context, courseItemId)
			),
			courseItemId
		);
	}

	public void handleCourseItemDeleteAccepted(
		Context context,
		String courseItemId
	) {
		CourseItem existingCourseItem = courseItemRepository
			.findById(courseItemId)
			.get();

		if (existingCourseItem == null) {
			throw new CourseItemNotFound();
		}
		courseItemRepository.deleteById(courseItemId);
	}

	public void update(
		Context context,
		String courseItemId,
		UpdateCourseItemInput updateCourseItemInput
	) {
		CourseItem existingCourseItem = courseItemRepository
			.findById(courseItemId)
			.get();

		if (existingCourseItem == null) {
			throw new CourseItemNotFound();
		}
		eventPublisher.sendCourseItemEvent(
			new CourseItemUpdateAccepted(
				new CourseItemUpdateAcceptedPayload(
					context,
					courseItemId,
					updateCourseItemInput
				)
			),
			courseItemId
		);
	}

	public CourseItem handleCourseItemUpdateAccepted(
		Context context,
		String courseItemId,
		UpdateCourseItemInput updateCourseItemInput
	) {
		CourseItem existingCourseItem = courseItemRepository
			.findById(courseItemId)
			.get();

		if (existingCourseItem == null) {
			throw new CourseItemNotFound();
		}
		CourseItem updatedCourseItem = courseItemMapper.updateCourseItem(
			context,
			courseItemId,
			updateCourseItemInput,
			existingCourseItem
		);
		CourseItem savedCourseItem = courseItemRepository.save(
			updatedCourseItem
		);
		return savedCourseItem;
	}

	public void create(
		Context context,
		CreateCourseItemInput createCourseItemInput,
		String courseItemId
	) {
		eventPublisher.sendCourseItemEvent(
			new CourseItemCreateAccepted(
				new CourseItemCreateAcceptedPayload(
					context,
					createCourseItemInput,
					courseItemId
				)
			),
			courseItemId
		);
	}

	public CourseItem handleCourseItemCreateAccepted(
		Context context,
		CreateCourseItemInput createCourseItemInput,
		String courseItemId
	) {
		CourseItem courseItem = courseItemMapper.createCourseItem(
			context,
			createCourseItemInput,
			courseItemId
		);
		CourseItem createdCourseItem = courseItemRepository.save(courseItem);
		return createdCourseItem;
	}

	public CourseItem get(Context context, String courseItemId) {
		CourseItem existingCourseItem = courseItemRepository
			.findById(courseItemId)
			.get();
		if (existingCourseItem == null) {
			throw new CourseItemNotFound();
		}
		return existingCourseItem;
	}
}
