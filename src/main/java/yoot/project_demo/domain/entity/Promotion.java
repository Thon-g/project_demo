package yoot.project_demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import yoot.project_demo.domain.AuditableEntity;
import yoot.project_demo.domain.enums.DiscountType;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "promotions")
public class Promotion extends AuditableEntity {
    @Column(name = "discount_type", nullable = false)
    private DiscountType discountType;
    @Column(name = "discount_value", nullable = false)
    private double discountValue;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String note;
    @Column(name = "promo_code", nullable = false)
    private String promoCode;
}
