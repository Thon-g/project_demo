package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoot.project_demo.Service.ParentService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.domain.entity.Parent;
import yoot.project_demo.repository.ParentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;

    public List<Parent> findAll() {
        return parentRepository.findAll();
    }

    public Optional<Parent> findById(Long id) {
        return parentRepository.findById(id);
    }

    public Parent save(Parent parent) {
        return parentRepository.save(parent);
    }

    public void delete(Long id) {
        parentRepository.deleteById(id);
    }
}
