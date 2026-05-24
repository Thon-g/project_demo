package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.dto.promotion.PromotionResponse;
import yoot.project_demo.dto.promotion.PromotionUpsertRequest;

import java.util.List;
import java.util.Optional;

@Service
public interface PromotionService {
    List<PromotionResponse> findByAll();
    Optional<PromotionResponse> findById(Long id);
    PromotionResponse create(PromotionUpsertRequest request);
    PromotionResponse update(Long id, PromotionUpsertRequest request);
    void delete(Long id) throws NotFoundException;
}
