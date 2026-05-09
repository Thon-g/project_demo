package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.dto.parent.ParentResponse;
import yoot.project_demo.dto.parent.ParentUpsertRequest;

import java.util.List;
import java.util.Optional;

@Service
public interface ParentService {
    List<ParentResponse> findByAll();
    Optional<ParentResponse> findById(Long id);
    ParentResponse create(ParentUpsertRequest request);
    ParentResponse update(Long id, ParentUpsertRequest request);
    void delete(Long id) throws NotFoundException;
}
