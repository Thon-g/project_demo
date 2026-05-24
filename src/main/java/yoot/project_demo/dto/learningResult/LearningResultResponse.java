package yoot.project_demo.dto.learningResult;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import yoot.project_demo.domain.entity.CourseClass;
import yoot.project_demo.domain.entity.Student;
import yoot.project_demo.domain.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class LearningResultResponse {
    private Long studentId;

    private String studentName;

    private Long courseClassId;

    private String className;

    private LocalDate resultMonth;

    private BigDecimal score;

    private String teacherComment;

    private Long createdByUserId;

    private String createdByUsername;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
