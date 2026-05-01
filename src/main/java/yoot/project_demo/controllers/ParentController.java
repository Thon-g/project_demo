package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.Service.ParentService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.domain.entity.Parent;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parent")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Parent>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(parentService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Parent>> findById(@PathVariable Long id) {
        Optional<Parent> parent = parentService.findById(id);
        return parent.map(value -> ResponseEntity.ok(ApiResponse.success(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Parent>> save(@RequestBody Parent parent) {
        return ResponseEntity.ok(ApiResponse.success(parentService.save(parent)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Parent>> update(@PathVariable Long id, @RequestBody Parent parentDetails) {
        Optional<Parent> parentOptional = parentService.findById(id);
        if (parentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Parent existingParent = parentOptional.get();
        existingParent.setFullName(parentDetails.getFullName());
        existingParent.setAddress(parentDetails.getAddress());
        existingParent.setPhone(parentDetails.getPhone());

        Parent updatedParent = parentService.save(existingParent);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật parent thành công", updatedParent));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        parentService.delete(id);
        return ResponseEntity.ok(ApiResponse.successMessage("Xóa parent thành công"));
    }
}
