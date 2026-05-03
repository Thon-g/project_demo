package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.dto.student.StudentResponse;
import yoot.project_demo.dto.student.StudentUpsertRequest;

import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {
    List<StudentResponse> findByAll();
    Optional<StudentResponse> findById(Long id);
    StudentResponse create(StudentUpsertRequest request);
    StudentResponse update(Long id, StudentUpsertRequest request);
    void delete(Long id) throws NotFoundException;
}
