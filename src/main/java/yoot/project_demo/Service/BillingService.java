package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.dto.tuitionInvoice.TuitionInvoiceCreateRequest;
import yoot.project_demo.dto.tuitionInvoice.TuitionInvoiceResponse;

import java.util.List;

@Service
public interface BillingService {
    TuitionInvoiceResponse createInvoice(TuitionInvoiceCreateRequest request) throws NotFoundException;
    List<TuitionInvoiceResponse> findInvoicesByStudent(Long studentId, String username) throws BadRequestException, NotFoundException;
}
