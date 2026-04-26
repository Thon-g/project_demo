package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoot.project_demo.Service.CourseService;
import yoot.project_demo.domain.entity.Course;
import yoot.project_demo.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
