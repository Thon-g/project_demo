package yoot.project_demo.dto.promotion;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private String promoCode;
    private String name;
    private DiscountType discountType;
    private double discountValue;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive = true;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
