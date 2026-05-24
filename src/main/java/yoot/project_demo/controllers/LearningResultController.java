package yoot.project_demo.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.Service.LearningResultService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.dto.learningResult.LearningResultCreateRequest;
import yoot.project_demo.dto.learningResult.LearningResultResponse;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/learning-result")
public class LearningResultController {
    private final LearningResultService learningResultService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF')")
    public ApiResponse<LearningResultResponse> create(@Valid @RequestBody LearningResultCreateRequest request, Principal principal) throws BadRequestException {
        return ApiResponse.success("Learning result created", learningResultService.create(request, principal.getName()));
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF','PARENT')")
    public ApiResponse<List<LearningResultResponse>> findByStudentId(@PathVariable Long studentId, Principal principal) throws BadRequestException {
        return ApiResponse.success(learningResultService.findByStudentId(studentId, principal.getName()));
    }
}
