package yoot.project_demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import yoot.project_demo.Service.BillingService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.dto.payment.PaymentCreateRequest;
import yoot.project_demo.dto.payment.PaymentResponse;
import yoot.project_demo.dto.tuitionInvoice.TuitionInvoiceCreateRequest;
import yoot.project_demo.dto.tuitionInvoice.TuitionInvoiceResponse;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
@Tag(name = "Billing", description = "Invoice and tuition billing endpoints.")
@SecurityRequirement(name = "bearerAuth")
public class BillingController {

    private final BillingService billingService;

    @PostMapping("/invoices")
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF','CASHIER')")
    @Operation(summary = "Create invoice", description = "Creates a tuition invoice for a student and course class, optionally applying a promotion.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Invoice created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Missing or invalid JWT token"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Insufficient permission")
    })
    public ApiResponse<TuitionInvoiceResponse> createInvoice(@Valid @RequestBody TuitionInvoiceCreateRequest request) throws NotFoundException {
        return ApiResponse.success("Invoice created", billingService.createInvoice(request));
    }

    @GetMapping("/students/{studentId}/invoices")
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF','CASHIER','PARENT')")
    @Operation(summary = "List invoices by student", description = "Returns invoices for the specified student. Parents only see invoices they are allowed to access.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Invoices returned successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Missing or invalid JWT token"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Insufficient permission"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ApiResponse<List<TuitionInvoiceResponse>> findInvoicesByStudent(@Parameter(description = "Student identifier", example = "1") @PathVariable Long studentId, @Parameter(hidden = true) Principal principal) throws BadRequestException, NotFoundException {
        return ApiResponse.success(billingService.findInvoicesByStudent(studentId, principal.getName()));
    }

    @PostMapping("/payments")
    @PreAuthorize("hasAnyRole('ADMIN','CASHIER')")
    public ApiResponse<PaymentResponse> createPayment(@Valid @RequestBody PaymentCreateRequest request, Principal principal) throws NotFoundException, BadRequestException {
        return ApiResponse.success("Payment created", billingService.createPayment(request, principal.getName()));
    }
}