package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.ScheduleSlot;

public interface ScheduleSlotRepository extends JpaRepository<ScheduleSlot, Long> {
}
