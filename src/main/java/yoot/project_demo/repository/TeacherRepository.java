package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
