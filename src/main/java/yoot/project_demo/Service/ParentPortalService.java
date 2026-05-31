package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.dto.parent.ParentDashboardResponse;

@Service
public interface ParentPortalService {
    public ParentDashboardResponse getDashboard(String username) throws BadRequestException, NotFoundException;
}
