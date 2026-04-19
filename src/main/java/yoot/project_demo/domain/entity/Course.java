package yoot.project_demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.domain.Auditable;
import yoot.project_demo.domain.AuditableEntity;

@Entity
@Data
public class Course extends AuditableEntity {
    @Column(columnDefinition = "varchar(20)")
    private String courseCode;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    private double tuition_fee;

    private int total_sessions;

    private byte is_active;
}
