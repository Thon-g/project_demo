package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.Notification;
import yoot.project_demo.domain.enums.NotificationRecipientType;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientTypeAndRecipientRefIdOrderByCreatedAtDesc(NotificationRecipientType recipientType, Long recipientRefId);
}
