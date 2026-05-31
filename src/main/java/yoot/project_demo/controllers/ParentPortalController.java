package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoot.project_demo.Service.ParentPortalService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.dto.parent.ParentDashboardResponse;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/parent")
@RequiredArgsConstructor
public class ParentPortalController {

    private final ParentPortalService parentPortalService;

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('PARENT')")
    public ApiResponse<ParentDashboardResponse> dashboard(Principal principal) throws BadRequestException, NotFoundException {
        return ApiResponse.success(parentPortalService.getDashboard(principal.getName()));
    }

}