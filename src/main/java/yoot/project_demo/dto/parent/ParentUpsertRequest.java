package yoot.project_demo.dto.parent;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentUpsertRequest {
    @Size(min = 2)
    private String fullName;

    @Pattern(regexp = "^(84|0[35789])+([0-9]{8})$")
    private String phone;

    @Email
    private String email;

    private String address;
}
