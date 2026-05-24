package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Attendance;
import yoot.project_demo.dto.attendance.AttendanceCreateRequest;
import yoot.project_demo.dto.attendance.AttendanceResponse;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AttendanceService {
    AttendanceResponse create(AttendanceCreateRequest request, String username) throws BadRequestException, NotFoundException;
    List<AttendanceResponse> findByClassId(Long classId);
}
