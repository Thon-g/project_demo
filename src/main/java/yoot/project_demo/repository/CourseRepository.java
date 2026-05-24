package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yoot.project_demo.domain.entity.Course;
import yoot.project_demo.dto.course.CourseResponse;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT C FROM Course C WHERE C.isActive = true")
    List<CourseResponse> findByCourseActive();
}
