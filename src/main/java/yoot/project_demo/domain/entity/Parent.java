package yoot.project_demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import yoot.project_demo.domain.AuditableEntity;

@EqualsAndHashCode(callSuper = true)
@Table(name = "parents")
@Entity
@Data
public class Parent extends AuditableEntity {
    @Column(name = "full_name", columnDefinition = "varchar(100)", nullable = false)
    private String fullName;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String phone;

    @Column(columnDefinition = "varchar(100)")
    private String email;

    @Column(columnDefinition = "varchar(255)")
    private String address;
}
