package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
