package yoot.project_demo.Service;

import org.springframework.stereotype.Service;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.dto.room.RoomResponse;
import yoot.project_demo.dto.room.RoomUpsertRequest;

import java.util.List;
import java.util.Optional;

@Service
public interface RoomService {
    List<RoomResponse> findByAll();
    Optional<RoomResponse> findById(Long id);
    RoomResponse create(RoomUpsertRequest request);
    RoomResponse update(Long id, RoomUpsertRequest request);
    void delete(Long id) throws NotFoundException;
}
