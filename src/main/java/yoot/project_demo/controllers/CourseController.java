package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.Service.CourseService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.domain.entity.Course;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getCourse() {
        return ResponseEntity.ok(ApiResponse.success(courseService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.findById(id);
        return course.map(value -> ResponseEntity.ok(ApiResponse.success(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@RequestBody Course course) {
        return ResponseEntity.ok(ApiResponse.success(courseService.save(course)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(@PathVariable Long id, Course courseDetails) {
        Optional<Course> courseOptional = courseService.findById(id);
        if(courseOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Course existingCourse = courseOptional.get();
        existingCourse.setName(courseDetails.getName());
        existingCourse.setDescription(courseDetails.getDescription());
        existingCourse.setTotalSessions(courseDetails.getTotalSessions());
        existingCourse.setTuitionFee(courseDetails.getTuitionFee());
        existingCourse.setActive(courseDetails.isActive());

        Course updatedCourse = courseService.save(existingCourse);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật course thành công", updatedCourse));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.ok(ApiResponse.successMessage("Xóa course thành công"));
    }
}
