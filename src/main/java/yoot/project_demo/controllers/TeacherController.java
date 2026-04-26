package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.domain.entity.Teacher;
import yoot.project_demo.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/teacher")
public class TeacherController {
    private final TeacherRepository teacherRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Teacher>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(teacherRepository.findAll()));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ApiResponse<Teacher>> findById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher.map(value -> ResponseEntity.ok(ApiResponse.success(value)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Teacher>> save(@RequestBody Teacher teacher){
        return ResponseEntity.ok(ApiResponse.success(teacherRepository.save(teacher)));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id){
        teacherRepository.deleteById(id);
        return ResponseEntity.ok(ApiResponse.successMessage("Xóa teacher thành công"));
    }
}
