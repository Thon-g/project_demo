package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yoot.project_demo.Service.StudentService;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Student;
import yoot.project_demo.dto.student.StudentResponse;
import yoot.project_demo.dto.student.StudentUpsertRequest;
import yoot.project_demo.repository.ParentRepository;
import yoot.project_demo.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final ModelMapper modelMapper;

    private StudentResponse map(Student student) {
        return modelMapper.map(student, StudentResponse.class);
    }

    public List<StudentResponse> findByAll() {
        return studentRepository.findAll().stream()
                .map(this::map).toList();
    }

    public Optional<StudentResponse> findById(Long id) {
        return studentRepository.findById(id)
                .map(this::map);
    }

    public StudentResponse create(StudentUpsertRequest request) {
        Student student = modelMapper.map(request, Student.class);
        parentRepository.findById(request.getParentId())
                .ifPresent(student::setParent);
        Student result = studentRepository.save(student);
        return map(result);
    }

    public StudentResponse update(Long id, StudentUpsertRequest request) {
        Student student = modelMapper.map(request, Student.class);
        student.setId(id);
        parentRepository.findById(request.getParentId())
                .ifPresent(student::setParent);
        Student result = studentRepository.save(student);
        return map(result);
    }

    public void delete(Long id) throws NotFoundException {
        if(studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new NotFoundException("Delete error");
        }

    }

    @Transactional(readOnly = true)
    public Student getStudentForParent(Long studentId, Long parentId) throws NotFoundException {
        Student student = getStudent(studentId);
        if (student.getParent() == null || !student.getParent().getId().equals(parentId)) {
            throw new org.springframework.security.access.AccessDeniedException("Student does not belong to current parent account");
        }
        return student;
    }

    public Student getStudent(Long id) throws NotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found: " + id));
    }

//    private StudentResponse map(Student student) {
//        StudentResponse result = new StudentResponse();
//        ParentResponse pResult = new ParentResponse();
//        if (student.getParent() != null) {
//            pResult.setId(student.getParent().getId());
//            pResult.setEmail(student.getParent().getEmail());
//            pResult.setAddress(student.getParent().getAddress());
//            pResult.setFullName(student.getParent().getFullName());
//            pResult.setPhone(student.getParent().getPhone());
//
//        }
//        result.setId(student.getId());
//        result.setStudentCode(student.getStudentCode());
//        result.setFullName(student.getFullName());
//        result.setDateOfBirth(student.getDateOfBirth());
//        result.setNote(student.getNote());
//        result.setGender(student.getGender());
//        result.setGradeLevel(student.getGradeLevel());
//        result.setPhone(student.getPhone());
//        result.setLatestScore(student.getLatestScore());
//        result.setStatus(student.getStatus());
//        result.setSchoolName(student.getSchoolName());
//        result.setParentResponse(pResult);
//
//        return result;
//    }
}
