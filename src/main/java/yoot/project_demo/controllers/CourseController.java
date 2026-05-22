package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.Service.CourseService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.dto.course.CourseResponse;
import yoot.project_demo.dto.course.CourseUpsertRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getCourse() {
        return ResponseEntity.ok(ApiResponse.success(courseService.findByAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> getCourseById(@PathVariable Long id) {
        Optional<CourseResponse> course = courseService.findById(id);
        return course.map(value -> ResponseEntity.ok(ApiResponse.success(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> create(@RequestBody CourseUpsertRequest course) {
        return ResponseEntity.ok(ApiResponse.success(courseService.create(course)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> update(@PathVariable Long id, @RequestBody CourseUpsertRequest course) {
        return ResponseEntity.ok(ApiResponse.success(courseService.update(id, course)));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) throws NotFoundException {
        courseService.delete(id);
        return ResponseEntity.ok(ApiResponse.successMessage("Xóa course thành công"));
    }
}
