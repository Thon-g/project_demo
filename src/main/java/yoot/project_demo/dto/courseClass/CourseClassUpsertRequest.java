package yoot.project_demo.dto.courseClass;

import jakarta.validation.constraints.*;
import lombok.Data;
import yoot.project_demo.domain.enums.ClassStatus;

import java.time.LocalDate;

@Data
public class CourseClassUpsertRequest{
        @NotBlank
        @Size(max = 20)
        private String classCode;

        @NotBlank
        private String name;

        @NotNull
        private Long courseId;

        @NotNull
        private Long roomId;

        @NotNull
        private Long scheduleSlotId;

        @NotNull
        private Long mainTeacherId;

        private Long assistantTeacherId;

        private LocalDate startDate;

        private LocalDate endDate;

        @NotNull
        @Min(1)
        private Integer maxStudents;

        @NotNull
        @DecimalMin("0.0")
        private Double tuitionFee;

        @NotNull
        private ClassStatus status;
}
