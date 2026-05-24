package yoot.project_demo.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.Service.StudentService;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.dto.student.StudentResponse;
import yoot.project_demo.dto.student.StudentUpsertRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<StudentResponse>> findAll(){
        return ResponseEntity.ok(studentService.findByAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentUpsertRequest request) {
        return ResponseEntity.ok(studentService.create(request));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, StudentUpsertRequest request) {
        return ResponseEntity.ok(studentService.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
