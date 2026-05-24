package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    boolean existsByCourseClassIdAndStudentIdAndAttendanceDate(Long courseClassId, Long studentId, LocalDate attendanceDate);
    List<Attendance> findByCourseClassId(Long courseClassId);
    List<Attendance> findByStudentId(Long studentId);
}
