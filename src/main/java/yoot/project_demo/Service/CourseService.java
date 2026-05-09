package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Course;
import yoot.project_demo.dto.Course.CourseResponse;
import yoot.project_demo.dto.Course.CourseUpsertRequest;

import java.util.List;
import java.util.Optional;

@Service
public interface CourseService {
    List<CourseResponse> findByAll();
    Optional<CourseResponse> findById(Long id);
    CourseResponse create(CourseUpsertRequest request);
    CourseResponse update(Long id, CourseUpsertRequest request);
    void delete(Long id) throws NotFoundException;
}
