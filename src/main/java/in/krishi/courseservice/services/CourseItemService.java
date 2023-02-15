package in.krishi.courseservice.services;

import in.krishi.courseservice.entities.Context;
import in.krishi.courseservice.events.CourseItemCreateAccepted;
import in.krishi.courseservice.events.CourseItemCreateAcceptedPayload;
import in.krishi.courseservice.events.CourseItemDeleteAccepted;
import in.krishi.courseservice.events.CourseItemDeleteAcceptedPayload;
import in.krishi.courseservice.events.CourseItemUpdateAccepted;
import in.krishi.courseservice.events.CourseItemUpdateAcceptedPayload;
import in.krishi.courseservice.events.EventPublisher;
import in.krishi.courseservice.exceptions.CourseItemNotFound;
import in.krishi.courseservice.inputs.CreateCourseItemInput;
import in.krishi.courseservice.inputs.UpdateCourseItemInput;
import in.krishi.courseservice.mappers.CourseItemMapper;
import in.krishi.courseservice.models.CourseItem;
import in.krishi.courseservice.repositories.CourseItemRepository;
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
