package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.Service.TeacherService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.domain.entity.Teacher;
import yoot.project_demo.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Teacher>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(teacherService.findAll()));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ApiResponse<Teacher>> findById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.findById(id);
        return teacher.map(value -> ResponseEntity.ok(ApiResponse.success(value)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Teacher>> save(@RequestBody Teacher teacher){
        return ResponseEntity.ok(ApiResponse.success(teacherService.save(teacher)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Teacher>> update(@PathVariable Long id, @RequestBody Teacher teacherDetails) {
        Optional<Teacher> teacherOptional = teacherService.findById(id);
        if (teacherOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Teacher existingTeacher = teacherOptional.get();
        existingTeacher.setFullName(teacherDetails.getFullName());
        existingTeacher.setPhone(teacherDetails.getPhone());
        existingTeacher.setTeacherRole(teacherDetails.getTeacherRole());
        existingTeacher.setActive(teacherDetails.isActive());

        Teacher updatedTeacher = teacherService.save(existingTeacher);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật teacher thành công", updatedTeacher));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id){
        teacherService.delete(id);
        return ResponseEntity.ok(ApiResponse.successMessage("Xóa teacher thành công"));
    }
}
