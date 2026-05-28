package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
