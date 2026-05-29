package yoot.project_demo.dto.promotion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yoot.project_demo.domain.enums.DiscountType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionUpsertRequest {
    private String promoCode;
    private String name;
    private DiscountType discountType;
    private double discountValue;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive = true;
    private String note;
}
