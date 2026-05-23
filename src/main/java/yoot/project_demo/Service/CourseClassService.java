package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.dto.CourseClass.CourseClassResponse;
import yoot.project_demo.dto.CourseClass.CourseClassUpsertRequest;

import java.util.List;
import java.util.Optional;

@Service
public interface CourseClassService {
    List<CourseClassResponse> findAll();
    Optional<CourseClassResponse> findById(Long id);
    CourseClassResponse create(CourseClassUpsertRequest request);
    CourseClassResponse update(Long id, CourseClassUpsertRequest request);
    void delete(Long id);
}
