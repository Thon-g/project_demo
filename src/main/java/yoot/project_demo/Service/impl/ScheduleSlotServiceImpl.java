package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoot.project_demo.Service.ScheduleSlotService;
import yoot.project_demo.domain.entity.ScheduleSlot;
import yoot.project_demo.repository.ScheduleSlotRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleSlotServiceImpl implements ScheduleSlotService {
    private final ScheduleSlotRepository scheduleSlotRepository;

    public List<ScheduleSlot> findAll() {
        return scheduleSlotRepository.findAll();
    }

    public Optional<ScheduleSlot> findById(Long id) {
        return scheduleSlotRepository.findById(id);
    }

    public ScheduleSlot save(ScheduleSlot scheduleSlot) {
        return scheduleSlotRepository.save(scheduleSlot);
    }

    public void deleteById(Long id) {
        scheduleSlotRepository.deleteById(id);
    }
}
