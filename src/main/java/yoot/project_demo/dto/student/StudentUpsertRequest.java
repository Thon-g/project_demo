package yoot.project_demo.dto.student;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yoot.project_demo.domain.enums.Gender;
import yoot.project_demo.domain.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpsertRequest {
    @Size(min = 2)
    private String studentCode;

    @Size(min = 2)
    private String fullName;

    private LocalDate dateOfBirth;

    @NotNull
    private Gender gender = Gender.OTHER;

    @NotBlank
    private String gradeLevel;

    private String schoolName;

    @Pattern(regexp = "^(84|0[35789])+([0-9]{8})$")
    private String phone;

    private Status status = Status.ACTIVE;

    @Min(value = 0)
    @Max(value = 10)
    private BigDecimal latestScore = BigDecimal.ZERO;

    private String note;

    private Long parentId;
}
