package yoot.project_demo.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import yoot.project_demo.domain.AuditableEntity;
import yoot.project_demo.domain.enums.TeacherRole;

@EqualsAndHashCode(callSuper = true)
@Table(name = "teachers")
@Entity
@Data
public class Teacher extends AuditableEntity {
    @Column(name = "teacher_code", columnDefinition = "varchar(20)", nullable = false)
    private String teacherCode;

    @Column(name = "full_name", columnDefinition = "varchar(100)", nullable = false)
    private String fullName;

    @Column(columnDefinition = "varchar(20)")
    private String phone;

    @Column(columnDefinition = "varchar(100)")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_role", nullable = false)
    private TeacherRole teacherRole = TeacherRole.TEACHER;

    @Column(name = "cccd_image_url", columnDefinition = "varchar(100)")
    private String cccdImageUrl;

    @Column(name = "is_active", columnDefinition = "boolean")
    private boolean isActive;
}
