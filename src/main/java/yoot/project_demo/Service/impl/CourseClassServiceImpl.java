package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import yoot.project_demo.Service.CourseClassService;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.CourseClass;
import yoot.project_demo.dto.CourseClass.CourseClassResponse;
import yoot.project_demo.dto.CourseClass.CourseClassUpsertRequest;
import yoot.project_demo.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseClassServiceImpl implements CourseClassService {
    private final CourseClassRepository courseClassRepository;
    private final ScheduleSlotRepository scheduleSlotRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final RoomRepository roomRepository;
    private final ModelMapper mapper;

    public CourseClassResponse toCourseClassResponse(CourseClass cc) {
        return mapper.map(cc, CourseClassResponse.class);
    }

    public List<CourseClassResponse> findAll() {
        return courseClassRepository.findAll().stream()
                .map(this::toCourseClassResponse).toList();
    }

    public Optional<CourseClassResponse> findById(Long id) {
        return courseClassRepository.findById(id)
                .map(this::toCourseClassResponse);
    }

    public CourseClassResponse create(CourseClassUpsertRequest request) {
        CourseClass cc = mapper.map(request, CourseClass.class);
        if(request.getCourseId() != null) {
            courseRepository.findById(request.getCourseId()).ifPresent(cc::setCourse);
        }

        if(request.getScheduleSlotId() != null) {
            scheduleSlotRepository.findById(request.getScheduleSlotId()).ifPresent(cc::setScheduleSlot);
        }

        if(request.getRoomId() != null) {
            roomRepository.findById(request.getRoomId()).ifPresent(cc::setRoom);
        }

        if (request.getMainTeacherId() != null) {
            teacherRepository.findById(request.getMainTeacherId()).ifPresent(cc::setMainTeacher);
        }
        if (request.getAssistantTeacherId() != null) {
            teacherRepository.findById(request.getAssistantTeacherId()).ifPresent(cc::setAssistantTeacher);
        }

        CourseClass result = courseClassRepository.save(cc);
        return toCourseClassResponse(result);
    }

    public CourseClassResponse update(Long id, CourseClassUpsertRequest request) {
        Optional<CourseClass> courseClass = courseClassRepository.findById(id);
        if(courseClass.isPresent()) {
            CourseClass cc = courseClass.get();

            copyToCourseClass(request, cc);

            CourseClass result = courseClassRepository.save(cc);
            return toCourseClassResponse(result);
        } else {
            throw new NotFoundException("Course not exists");
        }
    }

    private void copyToCourseClass(CourseClassUpsertRequest request, CourseClass cc) {

    }

}
