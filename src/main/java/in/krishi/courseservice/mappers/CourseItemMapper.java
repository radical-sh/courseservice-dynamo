package in.krishi.courseservice.mappers;

import in.krishi.courseservice.entities.Context;
import in.krishi.courseservice.inputs.CreateCourseItemInput;
import in.krishi.courseservice.inputs.UpdateCourseItemInput;
import in.krishi.courseservice.models.CourseItem;
import java.time.OffsetDateTime;
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
