package yoot.project_demo.dto.CourseClass;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yoot.project_demo.domain.entity.Course;
import yoot.project_demo.domain.entity.Room;
import yoot.project_demo.domain.entity.ScheduleSlot;
import yoot.project_demo.domain.entity.Teacher;
import yoot.project_demo.domain.enums.ClassStatus;
import yoot.project_demo.dto.course.CourseResponse;
import yoot.project_demo.dto.room.RoomResponse;
import yoot.project_demo.dto.teacher.TeacherResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseClassResponse {
    private Long id;

    private String classCode;

    private String name;

    private CourseResponse course;

    private RoomResponse room;

    private ScheduleSlot scheduleSlot;

    private TeacherResponse mainTeacher;

    private TeacherResponse assistantTeacher;

    private LocalDate startDate;

    private LocalDate endDate;

    private int maxStudents;

    private double tuitionFee;

    private ClassStatus status = ClassStatus.OPEN;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
