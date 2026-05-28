package yoot.project_demo.dto.tuitionInvoice;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TuitionInvoiceResponse {

    //@Schema(description = "Invoice identifier.", example = "1")
    private Long id;

    //@Schema(description = "Invoice code.", example = "INV-2026-001")
    private String invoiceCode;

    //@Schema(description = "Student identifier.", example = "1")
    private Long studentId;

    //@Schema(description = "Student full name.", example = "Nguyen Van A")
    private String studentName;

    //@Schema(description = "Course class identifier.", example = "3")
    private Long courseClassId;

    //@Schema(description = "Course class name.", example = "English Beginner - Morning")
    private String className;

    //@Schema(description = "Billing month.", example = "2026-06-01")
    private LocalDate billingMonth;

    //@Schema(description = "Original amount before discount.", example = "2800000")
    private float originalAmount;

    //@Schema(description = "Discount amount applied.", example = "300000")
    private float discountAmount;

    //@Schema(description = "Final amount after discount.", example = "2500000")
    private float finalAmount;

    //@Schema(description = "Amount already paid.", example = "1000000")
    private float amountPaid;

    //@Schema(description = "Remaining balance.", example = "1500000")
    private float balanceAmount;

    //@Schema(description = "Invoice status.", example = "PARTIALLY_PAID")
    private String status;

    //@Schema(description = "Promotion identifier.", example = "2", nullable = true)
    private Long promotionId;

    //@Schema(description = "Promotion name.", example = "Summer discount", nullable = true)
    private String promotionName;

    //@Schema(description = "Due date.", example = "2026-06-15", nullable = true)
    private LocalDate dueDate;

    //@Schema(description = "Billing note.", example = "Early bird discount applied.", nullable = true)
    private String note;

    //@Schema(description = "Creation timestamp.", example = "2026-05-24T10:00:00")
    private LocalDateTime createdAt;

    //@Schema(description = "Last update timestamp.", example = "2026-05-24T11:30:00")
    private LocalDateTime updatedAt;
}