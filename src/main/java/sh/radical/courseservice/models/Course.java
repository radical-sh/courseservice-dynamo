package sh.radical.courseservice.models;


import com.amazonaws.services.dynamodbv2.datamodeling.*;
import sh.radical.courseservice.enums.VisibilityLevel;
import java.lang.Override;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import sh.radical.courseservice.mappers.LocalDateTimeToStringTypeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@NoArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "Course")
@DynamoDbBean
public class Course {

	@DynamoDBAttribute
	String organizationId;

	@DynamoDBAttribute
	List<String> courseItems;

	@DynamoDBAttribute
	String createdBy;

	@DynamoDBAttribute
	String lastModifiedBy;

	@DynamoDBAttribute
	@DynamoDBTypeConvertedEnum
	VisibilityLevel visibilityLevel;

	@DynamoDBAttribute
	String description;

	@Id
	@DynamoDBAttribute
	@DynamoDBHashKey
	String courseId;

	@DynamoDBAttribute
	@DynamoDBTypeConverted(converter = LocalDateTimeToStringTypeConverter.class)
	Instant createdOn = OffsetDateTime.now().toInstant();

	@DynamoDBAttribute
	@DynamoDBTypeConverted(converter = LocalDateTimeToStringTypeConverter.class)
	Instant lastModifiedOn = OffsetDateTime.now().toInstant();

	@DynamoDBAttribute
	List<String> tags;

	@Override
	public String toString() {
		return courseId;
	}

	@DynamoDBIgnore
	public static String getNewCourseId() {
		return UUID.randomUUID().toString();
	}

	List<Author> authors;
}
