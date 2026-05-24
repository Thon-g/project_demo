package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
