package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yoot.project_demo.Service.PaymentService;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Payment;
import yoot.project_demo.domain.entity.TuitionInvoice;
import yoot.project_demo.domain.entity.User;
import yoot.project_demo.domain.enums.InvoiceStatus;
import yoot.project_demo.domain.enums.PaymentMethod;
import yoot.project_demo.dto.payment.PaymentCreateRequest;
import yoot.project_demo.dto.payment.PaymentResponse;
import yoot.project_demo.repository.PaymentRepository;
import yoot.project_demo.repository.TuitionInvoiceRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final TuitionInvoiceRepository tuitionInvoiceRepository;
    private final ModelMapper mapper;

    @Transactional
    public PaymentResponse createPayment(User currentUser, PaymentCreateRequest request) {
        TuitionInvoice invoice = tuitionInvoiceRepository.findById(request.getInvoiceId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy hóa đơn"));

        if (invoice.getStatus() == InvoiceStatus.PAID) {
            throw new RuntimeException("Hóa đơn đã thanh toán");
        }

        BigDecimal paidAmount = request.getPaidAmount();
        if (paidAmount.compareTo(invoice.getBalanceAmount()) > 0) {
            throw new RuntimeException("Số tiền đóng vượt quá số dư cần phải đóng");
        }

        Payment payment = new Payment();
        payment.setCashierUserId(currentUser);
        payment.setInvoice(invoice);
        payment.setPaymentCode("PAY-"+ UUID.randomUUID().toString().substring(0,10).toUpperCase());
        payment.setPaidAmount(paidAmount);
        payment.setPaidAt(LocalDateTime.now());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setNote(request.getNote());

        BigDecimal newAmountPaid = invoice.getAmountPaid().add(paidAmount);
        BigDecimal newBalanceAmount = invoice.getFinalAmount().subtract(newAmountPaid);
        invoice.setAmountPaid(newAmountPaid);
        invoice.setBalanceAmount(newBalanceAmount);

        if (newBalanceAmount.compareTo(BigDecimal.ZERO) == 0) {
            invoice.setStatus(InvoiceStatus.PAID);
        } else {
            invoice.setStatus(InvoiceStatus.PARTIAL);
        }

        tuitionInvoiceRepository.save(invoice);
        Payment savePayment = paymentRepository.save(payment);
        return toPaymentResponse(savePayment);

    }

    private PaymentResponse toPaymentResponse(Payment item) {
        PaymentResponse result = mapper.map(item, PaymentResponse.class);
        result.setCashierUserId(item.getCashierUserId().getId());
        result.setInvoiceId(item.getInvoice().getId());
        result.setInvoiceCode(item.getInvoice().getInvoiceCode());
        result.setPaymentMethod(item.getPaymentMethod());

        return result;
    }
}
