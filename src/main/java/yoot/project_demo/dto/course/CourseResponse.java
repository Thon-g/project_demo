package yoot.project_demo.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
