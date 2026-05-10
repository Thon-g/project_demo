package yoot.project_demo.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yoot.project_demo.domain.enums.Gender;
import yoot.project_demo.domain.enums.Status;
import yoot.project_demo.dto.parent.ParentResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;

    private String studentCode;

    private String fullName;

    private LocalDate dateOfBirth;

    private Gender gender = Gender.OTHER;

    private String gradeLevel;

    private String schoolName;

    private String phone;

    private Status status = Status.ACTIVE;

    private BigDecimal latestScore = BigDecimal.ZERO;

    private String note;

    private ParentResponse parentResponse;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
