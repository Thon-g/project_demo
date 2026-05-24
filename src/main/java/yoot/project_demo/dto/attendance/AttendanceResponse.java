package yoot.project_demo.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResponse {
    private Long id;
    private Long courseClassId;
    private String className;
    private Long studentId;
    private String studentName;
    private LocalDate attendanceDate;
    private String status;
    private String note;
    private Long recordedByUserId;
    private String recordedByUsername;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
