package yoot.project_demo.dto.teacher;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yoot.project_demo.domain.enums.TeacherRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherUpsertRequest {

    private String teacherCode;

    @Size(min = 2)
    private String fullName;

    @Pattern(regexp = "^(84|0[35789])+([0-9]{8})$")
    private String phone;

    @Email
    private String email;

    @NotBlank
    private TeacherRole teacherRole = TeacherRole.TEACHER;

    @NotNull
    private String cccdImageUrl;

    private boolean isActive;
}
