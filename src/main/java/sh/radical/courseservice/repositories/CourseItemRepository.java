package sh.radical.courseservice.repositories;

import sh.radical.courseservice.models.CourseItem;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface CourseItemRepository
	extends CrudRepository<CourseItem, String> {}
