package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.domain.entity.Parent;

import java.util.List;
import java.util.Optional;

@Service
public interface ParentService {
    List<Parent> findAll();
    Optional<Parent> findById(Long id);
    Parent save(Parent parent);
    void delete(Long id);
}
