package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.Service.TeacherService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Teacher;
import yoot.project_demo.dto.Teacher.TeacherResponse;
import yoot.project_demo.dto.Teacher.TeacherUpsertRequest;
import yoot.project_demo.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TeacherResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(teacherService.findByAll()));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ApiResponse<TeacherResponse>> findById(@PathVariable Long id) {
        Optional<TeacherResponse> teacher = teacherService.findById(id);
        return teacher.map(value -> ResponseEntity.ok(ApiResponse.success(value)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TeacherResponse>> create(@RequestBody TeacherUpsertRequest teacher){
        return ResponseEntity.ok(ApiResponse.success(teacherService.create(teacher)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TeacherResponse>> update(@PathVariable Long id, @RequestBody TeacherUpsertRequest teacher){
        return ResponseEntity.ok(ApiResponse.success(teacherService.update(id, teacher)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) throws NotFoundException {
        teacherService.delete(id);
        return ResponseEntity.ok(ApiResponse.successMessage("Xóa teacher thành công"));
    }
}
