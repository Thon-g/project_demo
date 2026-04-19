package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.domain.entity.Student;

import java.util.List;

@Service
public interface StudentService {
    List<Student> findByAll();
}
