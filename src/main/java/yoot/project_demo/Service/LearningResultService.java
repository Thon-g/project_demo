package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.dto.learningResult.LearningResultCreateRequest;
import yoot.project_demo.dto.learningResult.LearningResultResponse;

import java.util.List;

@Service
public interface LearningResultService {
    LearningResultResponse create(LearningResultCreateRequest request, String username) throws BadRequestException;
    List<LearningResultResponse> findByStudentId(Long studentId, String username) throws BadRequestException, NotFoundException;
}
