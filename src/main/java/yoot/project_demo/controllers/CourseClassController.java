package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.Service.CourseClassService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.domain.entity.CourseClass;
import yoot.project_demo.dto.CourseClass.CourseClassResponse;
import yoot.project_demo.dto.CourseClass.CourseClassUpsertRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/course-class")
public class CourseClassController {
    private final CourseClassService courseClassService;

    @GetMapping
    public ApiResponse<List<CourseClassResponse>> findAll() {
        return ApiResponse.success(courseClassService.findAll());
    }

    @GetMapping(value = "/id")
    public ApiResponse<CourseClassResponse> findById(@PathVariable Long id) {
        Optional<CourseClassResponse> cc = courseClassService.findById(id);
        return cc.map(ApiResponse::success)
                .orElseGet(() -> ApiResponse.error("not found", null));
    }

    @PostMapping
    public ApiResponse<CourseClassResponse> create(@RequestBody CourseClassUpsertRequest request) {
        return ApiResponse.success(courseClassService.create(request));
    }

    @PutMapping(value = "/id")
    public ApiResponse<CourseClassResponse> update(@PathVariable Long id, @RequestBody CourseClassUpsertRequest request) {
        return ApiResponse.success(courseClassService.update(id, request));
    }

    @DeleteMapping(value = "/id")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        courseClassService.delete(id);
        return ApiResponse.successMessage("Xóa course class thành công");
    }

}
