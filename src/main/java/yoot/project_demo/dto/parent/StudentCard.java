package yoot.project_demo.dto.parent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCard {
    private Long id;
    private String studentCode;
    private String fullName;
    private String status;
    private BigDecimal latestScore;
}
