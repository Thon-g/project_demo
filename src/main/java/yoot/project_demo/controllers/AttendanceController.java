package yoot.project_demo.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.Service.AttendanceService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.dto.attendance.AttendanceCreateRequest;
import yoot.project_demo.dto.attendance.AttendanceResponse;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/attendaces")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping("/class/{classId}")
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF')")
    public ApiResponse<List<AttendanceResponse>> findByClassId(@PathVariable Long classId) {
        return ApiResponse.success(attendanceService.findByClassId(classId));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF')")
    public ApiResponse<AttendanceResponse> create(@Valid @RequestBody AttendanceCreateRequest request, Principal principal) throws BadRequestException {
        return ApiResponse.success("Attendance created", attendanceService.create(request, principal.getName()));
    }
}
