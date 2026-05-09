package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import yoot.project_demo.Service.TeacherService;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Teacher;
import yoot.project_demo.dto.Teacher.TeacherResponse;
import yoot.project_demo.dto.Teacher.TeacherUpsertRequest;
import yoot.project_demo.repository.TeacherRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper mapper;

    public TeacherResponse map(Teacher teacher) {
        return mapper.map(teacher, TeacherResponse.class);
    }

    public List<TeacherResponse> findByAll() {
        return teacherRepository.findAll().stream()
                .map(this::map).toList();
    }

    public Optional<TeacherResponse> findById(Long id) {
        return teacherRepository.findById(id)
                .map(this::map);
    }

    public TeacherResponse create(TeacherUpsertRequest request) {
        Teacher newTeacher = mapper.map(request, Teacher.class);
        newTeacher.setCreateAt(LocalDateTime.now());
        newTeacher.setUpdatedAt(LocalDateTime.now());
        Teacher result = teacherRepository.save(newTeacher);
        return map(result);
    }

    public TeacherResponse update(Long id, TeacherUpsertRequest request) {
        Teacher newTeacher = mapper.map(request, Teacher.class);
        newTeacher.setId(id);
        newTeacher.setUpdatedAt(LocalDateTime.now());
        Teacher result = teacherRepository.save(newTeacher);
        return map(result);
    }

    public void delete(Long id) throws NotFoundException {
        if(teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
        } else {
            throw new NotFoundException("Delete teacher error");
        }
    }
}
