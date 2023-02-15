package in.krishi.courseservice.inputs;

import in.krishi.courseservice.enums.VisibilityLevel;
import java.util.List;

import in.krishi.courseservice.models.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateCourseInput {

	public String organizationId;

	public List<String> courseItems;

	public VisibilityLevel visibilityLevel;

	public String description;

	public List<String> tags;

	List<Author> authors;
}
