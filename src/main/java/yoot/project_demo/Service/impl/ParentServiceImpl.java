package yoot.project_demo.Service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import yoot.project_demo.Service.ParentService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Parent;
import yoot.project_demo.dto.parent.ParentResponse;
import yoot.project_demo.dto.parent.ParentUpsertRequest;
import yoot.project_demo.repository.ParentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;
    private final ModelMapper mapper;

    private ParentResponse map(Parent parent) {
        return mapper.map(parent, ParentResponse.class);
    }

    public List<ParentResponse> findByAll() {
        return parentRepository.findAll().stream()
                .map(this::map).toList();
    }

    public Optional<ParentResponse> findById(Long id) {
        return parentRepository.findById(id)
                .map(this::map);
    }

    public ParentResponse create(ParentUpsertRequest request) {
        Parent newParent = mapper.map(request, Parent.class);
        newParent.setCreateAt(LocalDateTime.now());
        newParent.setUpdatedAt(LocalDateTime.now());
        Parent result = parentRepository.save(newParent);
        return map(result);
    }

    public ParentResponse update(Long id, ParentUpsertRequest request) {
        Parent newParent = mapper.map(request, Parent.class);
        newParent.setId(id);
        newParent.setUpdatedAt(LocalDateTime.now());
        Parent result = parentRepository.save(newParent);
        return map(result);
    }

    public void delete(Long id) throws NotFoundException {
        if(parentRepository.existsById(id)) {
            parentRepository.deleteById(id);
        } else {
            throw new NotFoundException("Delete parent error");
        }

    }
}
