package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.CourseClass;

public interface CourseClassRepository extends JpaRepository<CourseClass, Long> {
}
