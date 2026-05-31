package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.Enrollment;
import yoot.project_demo.domain.enums.EnrollmentStatus;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentIdAndCourseClassId(Long studentId, Long courseClassId);

    long countByCourseClassIdAndStatus(Long courseClassId, EnrollmentStatus status);

    List<Enrollment> findByCourseClassId(Long courseClassId);

    Optional<Enrollment> findByStudentIdAndCourseClassId(Long studentId, Long courseClassId);

    List<Enrollment> findByStudentId(Long studentId);
}
