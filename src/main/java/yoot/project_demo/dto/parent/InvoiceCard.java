package yoot.project_demo.dto.parent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCard {
    private Long id;
    private String invoiceCode;
    private String studentName;
    private String className;
    private LocalDate billingMonth;
    private BigDecimal finalAmount;
    private BigDecimal amountPaid;
    private BigDecimal balanceAmount;
    private String status;
    private LocalDate dueDate;
}