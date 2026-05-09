package yoot.project_demo.dto.Teacher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yoot.project_demo.domain.enums.TeacherRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherUpsertRequest {
    private String teacherCode;

    private String fullName;

    @Pattern(regexp = "^(84|0[35789])+([0-9]{8})$")
    private String phone;

    @Email
    private String email;

    private TeacherRole teacherRole = TeacherRole.TEACHER;

    private String cccdImageUrl;

    private boolean isActive;
}
