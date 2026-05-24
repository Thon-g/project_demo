package yoot.project_demo.dto.promotion;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yoot.project_demo.domain.enums.DiscountType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionResponse {
    private Long id;
    private DiscountType discountType;
    private double discountValue;
    private LocalDate endDate;
    private LocalDate startDate;
    private String name;
    private String note;
    private String promoCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
