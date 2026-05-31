package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Enrollment;
import yoot.project_demo.dto.enrollment.EnrollmentCreateRequest;
import yoot.project_demo.dto.enrollment.EnrollmentResponse;

import java.util.List;

@Service
public interface EnrollmentService {
    EnrollmentResponse create(EnrollmentCreateRequest request) throws BadRequestException, NotFoundException;

    List<EnrollmentResponse> findByClassId(Long classId);

    Enrollment getEnrollment(Long studentId, Long classId) throws BadRequestException;
}
