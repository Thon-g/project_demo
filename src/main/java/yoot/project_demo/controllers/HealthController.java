package yoot.project_demo.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yoot.project_demo.common.ApiResponse;

@RestController
public class HealthController {
    @GetMapping("/api/health")
    public ApiResponse<String> health() {
        return ApiResponse.success("ok ok backend is runing");
    }

    @PostMapping("/api/health/echo")
    public ApiResponse<String> echo(@Valid @RequestBody EchoRequest request) {
        return ApiResponse.success(request.message());
    }

    public record EchoRequest(@NotBlank(message = "message must not be blank") String message) {}
}
