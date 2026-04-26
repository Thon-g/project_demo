package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.domain.entity.Parent;
import yoot.project_demo.repository.ParentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parent")
@RequiredArgsConstructor
public class ParentController {
    private final ParentRepository parentRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Parent>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(parentRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Parent>> findById(@PathVariable Long id) {
        Optional<Parent> parent = parentRepository.findById(id);
        return parent.map(value -> ResponseEntity.ok(ApiResponse.success(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Parent>> save(@RequestBody Parent parent) {
        return ResponseEntity.ok(ApiResponse.success(parentRepository.save(parent)));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        parentRepository.deleteById(id);
        return ResponseEntity.ok(ApiResponse.successMessage("Xóa parent thành công"));
    }
}
