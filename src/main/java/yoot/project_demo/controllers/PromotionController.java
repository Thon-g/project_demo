package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.Service.PromotionService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.dto.promotion.PromotionResponse;
import yoot.project_demo.dto.promotion.PromotionUpsertRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/promotion")
public class PromotionController {
    private final PromotionService promotionService;

    @GetMapping
    public ApiResponse<List<PromotionResponse>> findAll() {
        return ApiResponse.success(promotionService.findByAll());
    }

    @GetMapping(value = "/{id}")
    public ApiResponse<PromotionResponse> findById(@PathVariable Long id) {
        Optional<PromotionResponse> promo = promotionService.findById(id);
        return promo.map(ApiResponse::success)
                .orElseGet(() -> ApiResponse.error("Not found", null));
    }

    @PostMapping
    public ApiResponse<PromotionResponse> create(@RequestBody PromotionUpsertRequest request) {
        return ApiResponse.success(promotionService.create(request));
    }

    @PostMapping(value = "/{id}")
    public ApiResponse<PromotionResponse> update(@PathVariable Long id, @RequestBody PromotionUpsertRequest request) {
        return ApiResponse.success(promotionService.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        promotionService.delete(id);
        return ApiResponse.successMessage("Xóa promotion thành công");
    }
}
