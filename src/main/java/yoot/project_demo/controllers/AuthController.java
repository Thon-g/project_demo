package yoot.project_demo.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoot.project_demo.Service.AuthService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.dto.auth.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success("Login successfully", authService.login(request));
    }

    @PostMapping(value = "/refresh")
    public ApiResponse<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return ApiResponse.success("", authService.refresh(request));
    }

    @PostMapping(value = "/change-password")
    public ApiResponse<Void> changPassword(Principal principal, @Valid @RequestBody ChangePasswordRequest request) throws BadRequestException {
        authService.changePassword(principal.getName(), request);
        return ApiResponse.successMessage("Change password successfully");
    }

    @PostMapping(value = "/me")
    public ApiResponse<CurrentUserResponse> me(Principal principal) throws BadRequestException {
        return ApiResponse.success("Get user name", authService.me(principal.getName()));
    }
}
