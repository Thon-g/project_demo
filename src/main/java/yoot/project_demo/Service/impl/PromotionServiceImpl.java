package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import yoot.project_demo.Service.PromotionService;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Promotion;
import yoot.project_demo.dto.promotion.PromotionResponse;
import yoot.project_demo.dto.promotion.PromotionUpsertRequest;
import yoot.project_demo.repository.PromotionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final ModelMapper mapper;

    private PromotionResponse map(Promotion promotion) {
        return mapper.map(promotion, PromotionResponse.class);
    }

    public List<PromotionResponse> findByAll() {
        return promotionRepository.findAll().stream()
                .map(this::map).toList();
    }

    public Optional<PromotionResponse> findById(Long id) {
        return promotionRepository.findById(id)
                .map(this::map);
    }

    public PromotionResponse create(PromotionUpsertRequest request) {
        Promotion newPromo = mapper.map(request, Promotion.class);
        Promotion result = promotionRepository.save(newPromo);
        return map(result);
    }

    public PromotionResponse update(Long id, PromotionUpsertRequest request) {
        Promotion newPromo = mapper.map(request, Promotion.class);
        newPromo.setId(id);
        Promotion result = promotionRepository.save(newPromo);
        return map(result);
    }

    public void delete(Long id) throws NotFoundException {
        if(promotionRepository.existsById(id)) {
            promotionRepository.deleteById(id);
        } else {
            throw new NotFoundException("Delete promotion error");
        }
    }
}
