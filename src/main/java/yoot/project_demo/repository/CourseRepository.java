package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
