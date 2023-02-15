package sh.radical.courseservice.repositories;

import sh.radical.courseservice.models.Course;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//@EnableScan
public interface CourseRepository extends DynamoDBCrudRepository<Course, String> {}
