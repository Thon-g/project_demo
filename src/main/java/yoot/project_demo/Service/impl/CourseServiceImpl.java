package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import yoot.project_demo.Service.CourseService;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Course;
import yoot.project_demo.domain.entity.Student;
import yoot.project_demo.dto.Course.CourseResponse;
import yoot.project_demo.dto.Course.CourseUpsertRequest;
import yoot.project_demo.dto.student.StudentResponse;
import yoot.project_demo.dto.student.StudentUpsertRequest;
import yoot.project_demo.repository.CourseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper mapper;

    private CourseResponse map(Course course) {
        return mapper.map(course, CourseResponse.class);
    }

    public List<CourseResponse> findByAll() {
        return courseRepository.findAll().stream()
                .map(this::map).toList();
    }

    public Optional<CourseResponse> findById(Long id) {
        return courseRepository.findById(id)
                .map(this::map);
    }

    public CourseResponse create(CourseUpsertRequest request) {
        Course newCourse = mapper.map(request, Course.class);
        Course result = courseRepository.save(newCourse);
        return map(result);
    }

    public CourseResponse update(Long id, CourseUpsertRequest request) {
        Course newCourse = mapper.map(request, Course.class);
        newCourse.setId(id);
        Course result = courseRepository.save(newCourse);
        return map(result);
    }

    public void delete(Long id) throws NotFoundException {
        if(courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new NotFoundException("Delete course error");
        }
    }
}
