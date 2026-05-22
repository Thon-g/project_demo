package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.dto.teacher.TeacherResponse;
import yoot.project_demo.dto.teacher.TeacherUpsertRequest;

import java.util.List;
import java.util.Optional;

@Service
public interface TeacherService {
    List<TeacherResponse> findByAll();
    Optional<TeacherResponse> findById(Long id);
    TeacherResponse create(TeacherUpsertRequest request);
    TeacherResponse update(Long id, TeacherUpsertRequest request);
    void delete(Long id) throws NotFoundException;
}
