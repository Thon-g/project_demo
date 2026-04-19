package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
