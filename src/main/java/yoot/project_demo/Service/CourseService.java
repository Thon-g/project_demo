package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.domain.entity.Course;

import java.util.List;
import java.util.Optional;

@Service
public interface CourseService {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Course save(Course course);
    void delete(Long id);
}
