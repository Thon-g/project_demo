package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yoot.project_demo.Service.AuthService;
import yoot.project_demo.Service.BillingService;
import yoot.project_demo.Service.CourseClassService;
import yoot.project_demo.Service.StudentService;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Promotion;
import yoot.project_demo.domain.entity.TuitionInvoice;
import yoot.project_demo.domain.entity.User;
import yoot.project_demo.domain.enums.DiscountType;
import yoot.project_demo.domain.enums.InvoiceStatus;
import yoot.project_demo.dto.tuitionInvoice.TuitionInvoiceCreateRequest;
import yoot.project_demo.dto.tuitionInvoice.TuitionInvoiceResponse;
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

}