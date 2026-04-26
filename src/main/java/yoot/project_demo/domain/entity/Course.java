package yoot.project_demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import yoot.project_demo.domain.AuditableEntity;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "courses")
public class Course extends AuditableEntity {
    @Column(columnDefinition = "varchar(20)")
    private String courseCode;

    @Column(columnDefinition = "varchar(100)")
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "tuition_fee")
    private BigDecimal tuitionFee;

    @Column(name = "total_sessions")
    private int totalSessions;

    @Column(name = "is_active")
    private boolean isActive;
}
