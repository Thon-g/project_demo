package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yoot.project_demo.Service.CourseClassService;
import yoot.project_demo.Service.EnrollmentService;
import yoot.project_demo.Service.StudentService;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.CourseClass;
import yoot.project_demo.domain.entity.Enrollment;
import yoot.project_demo.domain.entity.Student;
import yoot.project_demo.domain.enums.EnrollmentStatus;
import yoot.project_demo.dto.enrollment.EnrollmentCreateRequest;
import yoot.project_demo.dto.enrollment.EnrollmentResponse;
import yoot.project_demo.repository.EnrollmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseClassService courseClassService;
    private final ModelMapper mapper;

    @Transactional
    public EnrollmentResponse create(EnrollmentCreateRequest request) throws BadRequestException, NotFoundException {
        if (enrollmentRepository.existsByStudentIdAndCourseClassId(request.getStudentId(), request.getCourseClassId())) {
            throw new BadRequestException("Student is already enrolled in this class");
        }

        CourseClass courseClass = courseClassService.getCourseClass(request.getCourseClassId());
        long activeCount = enrollmentRepository.countByCourseClassIdAndStatus(request.getCourseClassId(), EnrollmentStatus.ACTIVE);
        if (activeCount >= courseClass.getMaxStudents()) {
            throw new BadRequestException("Class is full");
        }

        Student student = studentService.getStudent(request.getStudentId());
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourseClass(courseClass);
        enrollment.setEnrolledAt(request.getEnrolledAt());
        enrollment.setStatus(request.getStatus());
        enrollment.setNote(request.getNote());
        return toResponse(enrollmentRepository.save(enrollment));
    }

    @Transactional(readOnly = true)
    public List<EnrollmentResponse> findByClassId(Long classId) {
        return enrollmentRepository.findByCourseClassId(classId).stream().map(this::toResponse).toList();
    }

    public Enrollment getEnrollment(Long studentId, Long classId) throws BadRequestException {
        return enrollmentRepository.findByStudentIdAndCourseClassId(studentId, classId)
                .orElseThrow(() -> new BadRequestException("Enrollment not found for student and class"));
    }

    private EnrollmentResponse toResponse(Enrollment enrollment) {
        EnrollmentResponse result = mapper.map(enrollment, EnrollmentResponse.class);
        result.setStudentId(enrollment.getStudent().getId());
        result.setStudentName(enrollment.getStudent().getFullName());
        result.setCourseClassId(enrollment.getCourseClass().getId());
        result.setClassName(enrollment.getCourseClass().getName());
        result.setStatus(enrollment.getStatus().toString());
        return result;
    }
}
