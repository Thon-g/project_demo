package yoot.project_demo.Service.impl;

import org.springframework.stereotype.Service;
import yoot.project_demo.Service.StudentService;
import yoot.project_demo.domain.entity.Student;
import yoot.project_demo.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findByAll() {
        return studentRepository.findAll();
    }
}
