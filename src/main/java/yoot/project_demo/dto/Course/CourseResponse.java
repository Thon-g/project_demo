package yoot.project_demo.dto.Course;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {

    private Long id;

    private String courseCode;

    private String name;

    private String description;

    private BigDecimal tuitionFee;

    private int totalSessions;

    private boolean isActive;
}
