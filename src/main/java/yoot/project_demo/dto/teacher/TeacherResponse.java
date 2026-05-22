package yoot.project_demo.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yoot.project_demo.domain.enums.TeacherRole;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherResponse {
    private Long id;

    private String teacherCode;

    private String fullName;

    private String phone;

    private String email;

    private TeacherRole teacherRole = TeacherRole.TEACHER;

    private String cccdImageUrl;

    private boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
