package yoot.project_demo.dto.payment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Long id;
    private Long invoiceId;
    private String invoiceCode;
    private String paymentCode;
    private BigDecimal paidAmount;
    private String paymentMethod;
    private LocalDateTime paidAt;
    private Long cashierUserId;
    private String cashierUsername;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
