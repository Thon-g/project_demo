package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.User;
import yoot.project_demo.dto.auth.*;

@Service
public interface AuthService {
    AuthResponse login(LoginRequest request);

    AuthResponse refresh(RefreshTokenRequest request);

    void changePassword(String username, ChangePasswordRequest request) throws BadRequestException, NotFoundException;

    CurrentUserResponse me(String username) throws BadRequestException;

    User findActiveUserByUsername(String username) throws BadRequestException;
}
