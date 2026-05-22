package yoot.project_demo.dto.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import yoot.project_demo.domain.entity.Parent;
import yoot.project_demo.domain.entity.Teacher;
import yoot.project_demo.domain.enums.Role;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;

    private String username;

    private String passwordHash;

    private String fullName;

    private String phone;

    private String email;

    private Role role;

    private Parent parent;

    private Teacher teacher;

    private String isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
