package yoot.project_demo.dto.payment;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class PaymentCreateRequest {

//    private Long cashierUserId;

    @NotNull
    private Long invoiceId;

//    @NotNull
//    private String invoiceCode;

    @Size(min = 8, max = 30)
    @NotNull
    private String paymentCode;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal paidAmount;

    @NotNull
    private LocalDateTime paidAt;

    @NotNull
    private PaymentMethod paymentMethod;

    @Size(max = 255)
    private String note;
}
