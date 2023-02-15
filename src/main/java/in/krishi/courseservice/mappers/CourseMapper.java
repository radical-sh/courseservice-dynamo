package in.krishi.courseservice.mappers;

import in.krishi.courseservice.entities.Context;
import in.krishi.courseservice.inputs.CreateCourseInput;
import in.krishi.courseservice.inputs.UpdateCourseInput;
import in.krishi.courseservice.models.Course;
import java.time.OffsetDateTime;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

	public Course updateCourse(
		Context context,
		String courseId,
		UpdateCourseInput updateCourseInput,
		Course existingCourse
	) {
		existingCourse.setOrganizationId(updateCourseInput.organizationId);
		existingCourse.setCourseItems(updateCourseInput.courseItems);
		existingCourse.setCreatedBy(context.userContext.userId);
		existingCourse.setLastModifiedBy(context.userContext.userId);
		existingCourse.setVisibilityLevel(updateCourseInput.visibilityLevel);
		existingCourse.setDescription(updateCourseInput.description);
		existingCourse.setCreatedOn(OffsetDateTime.now().toInstant());
		existingCourse.setLastModifiedOn(OffsetDateTime.now().toInstant());
		existingCourse.setTags(updateCourseInput.tags);
		return existingCourse;
	}

	public Course createCourse(
		Context context,
		CreateCourseInput createCourseInput,
		String courseId
	) {
		Course course = new Course();
		course.setOrganizationId(createCourseInput.organizationId);
		course.setCourseItems(createCourseInput.courseItems);
		course.setCreatedBy(context.userContext.userId);
		course.setLastModifiedBy(context.userContext.userId);
		course.setVisibilityLevel(createCourseInput.visibilityLevel);
		course.setDescription(createCourseInput.description);
		course.setCreatedOn(OffsetDateTime.now().toInstant());
		course.setLastModifiedOn(OffsetDateTime.now().toInstant());
		course.setTags(createCourseInput.tags);
		course.setCourseId(courseId);
		course.setAuthors(createCourseInput.getAuthors());
		return course;
	}
}
