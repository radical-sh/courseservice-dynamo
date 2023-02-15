package in.krishi.courseservice.repositories;

import in.krishi.courseservice.models.Course;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//@EnableScan
public interface CourseRepository extends DynamoDBCrudRepository<Course, String> {}
