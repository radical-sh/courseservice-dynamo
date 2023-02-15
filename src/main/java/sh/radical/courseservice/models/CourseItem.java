package sh.radical.courseservice.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.lang.Override;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@NoArgsConstructor
@Getter
@Setter
@DynamoDBTable(tableName = "CourseItem")
@DynamoDbBean
public class CourseItem {

	@DynamoDBAttribute
	String contents;

	@DynamoDBAttribute
	String createdBy;

//	@DynamoDBAttribute
//	Instant createdOn = OffsetDateTime.now().toInstant();

	@Id
	@DynamoDBAttribute
	@DynamoDBHashKey
	String courseItemId;

	@DynamoDBAttribute
	String lastModifiedBy;
//
//	@DynamoDBAttribute
//	Instant lastModifiedOn = OffsetDateTime.now().toInstant();

	@Override
	public String toString() {
		return courseItemId;
	}

	@DynamoDBIgnore
	public static String getNewCourseItemId() {
		return UUID.randomUUID().toString();
	}
}
