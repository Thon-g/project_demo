package yoot.project_demo.dto.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yoot.project_demo.domain.entity.TuitionInvoice;
import yoot.project_demo.domain.entity.User;
import yoot.project_demo.domain.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Long id;

    private User cashierUserId;

    private TuitionInvoice invoice;

    private String paymentCode;

    private BigDecimal paidAmount;

    private LocalDateTime paidAt;

    private PaymentMethod paymentMethod;

    private String note;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
