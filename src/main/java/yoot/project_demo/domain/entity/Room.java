package yoot.project_demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import yoot.project_demo.domain.AuditableEntity;

@EqualsAndHashCode(callSuper = true)
@Table(name = "rooms")
@Entity
@Data
public class Room extends AuditableEntity {
    @Column(length = 20)
    private String room_code;

    @Column(length = 100)
    private String name;
    
    private int capacity;

    private String description;
}
