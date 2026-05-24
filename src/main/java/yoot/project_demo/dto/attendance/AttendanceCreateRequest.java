package yoot.project_demo.dto.attendance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yoot.project_demo.domain.enums.AttendanceStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceCreateRequest {
    @NotNull
    Long courseClassId;
    @NotNull
    Long studentId;
    @NotNull
    LocalDate attendanceDate;
    @NotNull
    AttendanceStatus status;
    @Size(max = 255)
    String note;
}
