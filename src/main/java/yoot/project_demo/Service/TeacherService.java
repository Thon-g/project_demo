package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.domain.entity.Teacher;

import java.util.List;
import java.util.Optional;

@Service
public interface TeacherService {
    List<Teacher> findAll();
    Optional<Teacher> findById(Long id);
    Teacher save(Teacher teacher);
    void delete(Long id);
}
