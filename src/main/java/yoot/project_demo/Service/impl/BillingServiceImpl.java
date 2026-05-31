package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yoot.project_demo.Service.*;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Payment;
import yoot.project_demo.domain.entity.Promotion;
import yoot.project_demo.domain.entity.TuitionInvoice;
import yoot.project_demo.domain.entity.User;
import yoot.project_demo.domain.enums.DiscountType;
import yoot.project_demo.domain.enums.InvoiceStatus;
import yoot.project_demo.dto.payment.PaymentCreateRequest;
import yoot.project_demo.dto.payment.PaymentResponse;
import yoot.project_demo.dto.tuitionInvoice.TuitionInvoiceCreateRequest;
import yoot.project_demo.dto.tuitionInvoice.TuitionInvoiceResponse;
import yoot.project_demo.repository.PaymentRepository;
import yoot.project_demo.repository.PromotionRepository;
import yoot.project_demo.repository.TuitionInvoiceRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    private final TuitionInvoiceRepository tuitionInvoiceRepository;
    private final PromotionRepository promotionRepository;
    private final StudentService studentService;
    private final CourseClassService courseClassService;
    private final AuthService authService;
    private final ModelMapper mapper;
    private final PaymentRepository paymentRepository;
    private final EnrollmentService enrollmentService;

    @Transactional
    public TuitionInvoiceResponse createInvoice(TuitionInvoiceCreateRequest request) throws NotFoundException {
        TuitionInvoice invoice = new TuitionInvoice();
        invoice.setInvoiceCode(request.getInvoiceCode());
        invoice.setStudent(studentService.getStudent(request.getStudentId()));
        invoice.setCourseClass(courseClassService.getCourseClass(request.getCourseClassId()));
        invoice.setBillingMonth(request.getBillingMonth());

        BigDecimal originalAmount;
        if (request.getOriginalAmount() != 0) {
            originalAmount = BigDecimal.valueOf(request.getOriginalAmount());
        } else {
            originalAmount = invoice.getCourseClass().getTuitionFee();
        }
        invoice.setOriginalAmount(originalAmount);

        Promotion promotion = null;
        BigDecimal discountAmount = BigDecimal.ZERO;
        if (request.getPromotionId() != null) {
            promotion = promotionRepository.findById(request.getPromotionId())
                    .orElseThrow(() -> new NotFoundException("Promotion not found: " + request.getPromotionId()));
            discountAmount = calculateDiscount(originalAmount, promotion);
        }

        BigDecimal finalAmount = originalAmount.subtract(discountAmount);

        invoice.setPromotion(promotion);
        invoice.setDiscountAmount(discountAmount);
        invoice.setFinalAmount(finalAmount);
        invoice.setAmountPaid(BigDecimal.ZERO);
        invoice.setBalanceAmount(finalAmount);
        invoice.setStatus(finalAmount.compareTo(BigDecimal.ZERO) == 0 ? InvoiceStatus.PAID : InvoiceStatus.UNPAID);
        invoice.setDueDate(request.getDueDate());
        invoice.setNote(request.getNote());
        return toInvoiceResponse(tuitionInvoiceRepository.save(invoice));
    }

    @Transactional(readOnly = true)
    public List<TuitionInvoiceResponse> findInvoicesByStudent(Long studentId, String username) throws BadRequestException, NotFoundException {
        User user = authService.findActiveUserByUsername(username);
        if (user.getRole().name().equals("PARENT")) {
            studentService.getStudentForParent(studentId, user.getParent().getId());
        }
        return tuitionInvoiceRepository.findByStudentId(studentId).stream().map(this::toInvoiceResponse).toList();
    }

    private BigDecimal calculateDiscount(BigDecimal originalAmount, Promotion promotion) {
        BigDecimal discountValue = BigDecimal.valueOf(promotion.getDiscountValue());

        if (promotion.getDiscountType() == DiscountType.PERCENT) {
            return originalAmount.multiply(discountValue)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        }
        return discountValue;
    }

    private TuitionInvoiceResponse toInvoiceResponse(TuitionInvoice item) {
        TuitionInvoiceResponse result = mapper.map(item, TuitionInvoiceResponse.class);
        result.setStudentId(item.getStudent().getId());
        result.setStudentName(item.getStudent().getFullName());
        result.setCourseClassId(item.getCourseClass().getId());
        result.setClassName(item.getCourseClass().getName());
        result.setStatus(item.getStatus().name());
        if (item.getPromotion() != null) {
            result.setPromotionId(item.getPromotion().getId());
            result.setPromotionName(item.getPromotion().getName());
        }
        return result;
    }

    public Optional<TuitionInvoiceResponse> findById(Long id) {
        return tuitionInvoiceRepository.findById(id)
                .map(this::toInvoiceResponse);
    }

    @Transactional
    public PaymentResponse createPayment(PaymentCreateRequest request, String username) throws NotFoundException, BadRequestException {
        TuitionInvoice invoice = tuitionInvoiceRepository.findById(request.getInvoiceId()).orElseThrow(() -> new NotFoundException("Invoice not found: " + request.getInvoiceId()));
        if (request.getPaidAmount().compareTo(BigDecimal.ZERO) == -1) {
            throw new BadRequestException("Paid amount must be greater than 0");
        }

        User cashier = authService.findActiveUserByUsername(username);
        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setPaymentCode(request.getPaymentCode());
        payment.setPaidAmount(request.getPaidAmount());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaidAt(request.getPaidAt());
        payment.setCashierUser(cashier);
        payment.setNote(request.getNote());
        Payment savedPayment = paymentRepository.save(payment);

        BigDecimal newAmountPaid = invoice.getAmountPaid().add(request.getPaidAmount());
        BigDecimal balance = invoice.getFinalAmount().subtract(newAmountPaid);
        invoice.setAmountPaid(newAmountPaid);
        invoice.setBalanceAmount(balance);
        invoice.setStatus(calculateInvoiceStatus(balance, newAmountPaid));
        tuitionInvoiceRepository.save(invoice);

        return toPaymentResponse(savedPayment);
    }

    private InvoiceStatus calculateInvoiceStatus(BigDecimal balance, BigDecimal amountPaid) {
        if (balance.compareTo(BigDecimal.ZERO) == -1) {
            return InvoiceStatus.OVERPAID;
        }
        if (balance.compareTo(BigDecimal.ZERO) == 0) {
            return InvoiceStatus.PAID;
        }
        if (amountPaid.compareTo(BigDecimal.ZERO) == 1) {
            return InvoiceStatus.PARTIAL;
        }
        return InvoiceStatus.UNPAID;
    }

    private PaymentResponse toPaymentResponse(Payment item) {
        PaymentResponse response  = mapper.map(item, PaymentResponse.class);
        response.setInvoiceId(item.getInvoice().getId());
        response.setInvoiceCode(item.getInvoice().getInvoiceCode());
        response.setPaymentMethod(item.getPaymentMethod().toString());
        if (item.getCashierUser() != null) {
            response.setCashierUserId(item.getCashierUser().getId());
            response.setCashierUsername(item.getCashierUser().getUsername());
        }
        return response;
    }
}