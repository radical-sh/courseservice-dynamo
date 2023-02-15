package sh.radical.courseservice.mappers;

import sh.radical.courseservice.entities.Context;
import sh.radical.courseservice.inputs.CreateCourseItemInput;
import sh.radical.courseservice.inputs.UpdateCourseItemInput;
import sh.radical.courseservice.models.CourseItem;
import org.springframework.stereotype.Component;

@Component
public class CourseItemMapper {

	public CourseItem updateCourseItem(
		Context context,
		String courseItemId,
		UpdateCourseItemInput updateCourseItemInput,
		CourseItem existingCourseItem
	) {
		existingCourseItem.setContents(updateCourseItemInput.contents);
		existingCourseItem.setCreatedBy(context.userContext.userId);
		//existingCourseItem.setCreatedOn(OffsetDateTime.now().toInstant());
		existingCourseItem.setLastModifiedBy(context.userContext.userId);
		//existingCourseItem.setLastModifiedOn(OffsetDateTime.now().toInstant());
		return existingCourseItem;
	}

	public CourseItem createCourseItem(
		Context context,
		CreateCourseItemInput createCourseItemInput,
		String courseItemId
	) {
		CourseItem courseItem = new CourseItem();
		courseItem.setContents(createCourseItemInput.contents);
		courseItem.setCreatedBy(context.userContext.userId);
		//courseItem.setCreatedOn(OffsetDateTime.now().toInstant());
		courseItem.setLastModifiedBy(context.userContext.userId);
		//courseItem.setLastModifiedOn(OffsetDateTime.now().toInstant());
		courseItem.setCourseItemId(courseItemId);
		return courseItem;
	}
}
