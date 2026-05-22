package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.domain.entity.ScheduleSlot;

import java.util.List;
import java.util.Optional;

@Service
public interface ScheduleSlotService {
    List<ScheduleSlot> findAll();
    Optional<ScheduleSlot> findById(Long id);
    ScheduleSlot save(ScheduleSlot scheduleSlot);
    void deleteById(Long id);
}
