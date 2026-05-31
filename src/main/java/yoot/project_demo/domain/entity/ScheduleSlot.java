package yoot.project_demo.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import yoot.project_demo.domain.AuditableEntity;

import java.sql.Time;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "schedule_slots")
public class ScheduleSlot extends AuditableEntity{

    @Column(name = "slot_code", length = 20, nullable = false)
    private String slotCode;

    @Column(nullable = false)
    private byte weekday;

    @Column(name = "start_time",  nullable = false)
    private Time startTime;

    @Column(name = "end_time", length = 255, nullable = false)
    private Time endTime;

    private String note;
}
