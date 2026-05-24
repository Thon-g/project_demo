package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
