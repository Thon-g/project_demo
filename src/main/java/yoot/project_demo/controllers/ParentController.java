package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.Service.ParentService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Parent;
import yoot.project_demo.dto.parent.ParentResponse;
import yoot.project_demo.dto.parent.ParentUpsertRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parent")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ParentResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(parentService.findByAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ParentResponse>> findById(@PathVariable Long id) {
        Optional<ParentResponse> parent = parentService.findById(id);
        return parent.map(value -> ResponseEntity.ok(ApiResponse.success(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ParentResponse>> create(@RequestBody ParentUpsertRequest parent) {
        return ResponseEntity.ok(ApiResponse.success(parentService.create(parent)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ParentResponse>> update(@PathVariable Long id ,@RequestBody ParentUpsertRequest parent) {
        return ResponseEntity.ok(ApiResponse.success(parentService.update(id, parent)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) throws NotFoundException {
        parentService.delete(id);
        return ResponseEntity.ok(ApiResponse.successMessage("Xóa parent thành công"));
    }
}
