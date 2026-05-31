package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yoot.project_demo.Service.AuthService;
import yoot.project_demo.Service.ParentPortalService;
import yoot.project_demo.Service.StudentService;
import yoot.project_demo.common.exception.BadRequestException;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.User;
import yoot.project_demo.domain.enums.NotificationRecipientType;
import yoot.project_demo.domain.enums.Role;
import yoot.project_demo.dto.parent.InvoiceCard;
import yoot.project_demo.dto.parent.NotificationCard;
import yoot.project_demo.dto.parent.ParentDashboardResponse;
import yoot.project_demo.dto.parent.StudentCard;
import yoot.project_demo.repository.NotificationRepository;
import yoot.project_demo.repository.TuitionInvoiceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentPortalServiceImpl implements ParentPortalService {
    private final AuthService authService;
    private final StudentService studentService;
    private final TuitionInvoiceRepository tuitionInvoiceRepository;
    private final NotificationRepository notificationRepository;

    @Transactional(readOnly = true)
    public ParentDashboardResponse getDashboard(String username) throws BadRequestException, NotFoundException {
        User user = authService.findActiveUserByUsername(username);
        if (user.getRole() != Role.PARENT || user.getParent() == null) {
            throw new BadRequestException("Current user is not a parent account");
        }

        Long parentId = user.getParent().getId();
        List<StudentCard> students = studentService.findByParentId(parentId).stream()
                .map(s -> new StudentCard(
                        s.getId(), s.getStudentCode(), s.getFullName(), s.getStatus().name(), s.getLatestScore()))
                .toList();

        List<InvoiceCard> invoices = tuitionInvoiceRepository.findByStudentParentId(parentId).stream()
                .map(i -> new InvoiceCard(
                        i.getId(),
                        i.getInvoiceCode(),
                        i.getStudent().getFullName(),
                        i.getCourseClass().getName(),
                        i.getBillingMonth(),
                        i.getFinalAmount(),
                        i.getAmountPaid(),
                        i.getBalanceAmount(),
                        i.getStatus().name(),
                        i.getDueDate()
                ))
                .toList();

        List<NotificationCard> notifications = notificationRepository
                .findByRecipientTypeAndRecipientRefIdOrderByCreatedAtDesc(NotificationRecipientType.PARENT, parentId)
                .stream()
                .map(n -> new NotificationCard(
                        n.getId(),
                        n.getType().name(),
                        n.getTitle(),
                        n.getContent(),
                        n.getIsRead(),
                        n.getCreatedAt(),
                        n.getUpdatedAt()
                ))
                .toList();

        return new ParentDashboardResponse(
                parentId,
                user.getParent().getFullName(),
                user.getUsername(),
                students,
                invoices,
                notifications
        );
    }
}
