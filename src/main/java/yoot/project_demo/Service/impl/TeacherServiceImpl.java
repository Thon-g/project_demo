package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoot.project_demo.Service.TeacherService;
import yoot.project_demo.domain.entity.Teacher;
import yoot.project_demo.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

}
