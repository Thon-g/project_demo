package yoot.project_demo.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpsertRequest {

    @NotNull
    private String courseCode;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BigDecimal tuitionFee;

    @NotNull
    private int totalSessions;
    
    private boolean isActive;
}
