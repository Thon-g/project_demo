package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.TuitionInvoice;

import java.util.List;

public interface TuitionInvoiceRepository extends JpaRepository<TuitionInvoice, Long> {
    List<TuitionInvoice> findByStudentId(Long studentId);

    List<TuitionInvoice> findByStudentParentId(Long parentId);
}
