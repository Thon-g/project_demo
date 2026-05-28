package yoot.project_demo.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import yoot.project_demo.domain.AuditableEntity;
import yoot.project_demo.domain.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "payments")
public class Payment extends AuditableEntity {

    @Column(nullable = true)
    @ManyToOne
    @JoinColumn(name = "cashier_user_id")
    private User cashierUserId;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private TuitionInvoice invoice;

    @Column(name = "payment_code", length = 30, nullable = false)
    private String paymentCode;

    @Column(name = "paid_amount", precision = 12, scale = 2, nullable = false)
    private BigDecimal paidAmount;

    @Column(name = "paid_at", nullable = false)
    private LocalDateTime paidAt;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(length = 255, nullable = true)
    private String note;
}
