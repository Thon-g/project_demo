package yoot.project_demo.dto.enrollment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import yoot.project_demo.domain.entity.CourseClass;
import yoot.project_demo.domain.entity.Student;
import yoot.project_demo.domain.enums.EnrollmentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponse {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long courseClassId;
    private String className;
    private LocalDate enrolledAt;
    private String status;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
