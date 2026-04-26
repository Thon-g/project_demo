package yoot.project_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoot.project_demo.domain.entity.Parent;

import java.util.List;
import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}
