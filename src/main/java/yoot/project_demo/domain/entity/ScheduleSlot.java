package yoot.project_demo.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import yoot.project_demo.domain.AuditableEntity;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "schedule_slots")
public class ScheduleSlot extends AuditableEntity{

    @Column(name = "slot_code", length = 20, nullable = false)
    private String slotCode;

    @Column(nullable = false)
    private byte weekday;

    @Column(name = "start_time",  nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", length = 255, nullable = false)
    private LocalDateTime endTime;

    private String note;
}
