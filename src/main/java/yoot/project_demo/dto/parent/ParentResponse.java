package yoot.project_demo.dto.parent;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentResponse {
    private Long id;

    private String fullName;

    private String phone;

    private String email;

    private String address;
}
