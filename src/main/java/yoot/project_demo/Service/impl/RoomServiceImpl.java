package yoot.project_demo.Service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import yoot.project_demo.Service.RoomService;
import yoot.project_demo.common.exception.NotFoundException;
import yoot.project_demo.domain.entity.Room;
import yoot.project_demo.dto.Room.RoomResponse;
import yoot.project_demo.dto.Room.RoomUpsertRequest;
import yoot.project_demo.repository.RoomRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper mapper;

    private RoomResponse map(Room room) {
        return mapper.map(room, RoomResponse.class);
    }

    public List<RoomResponse> findByAll() {
        return roomRepository.findAll().stream()
                .map(this::map).toList();
    }

    public Optional<RoomResponse> findById(Long id) {
        return roomRepository.findById(id)
                .map(this::map);
    }

    public RoomResponse create(RoomUpsertRequest request) {
        Room newRoom = mapper.map(request, Room.class);
        Room result = roomRepository.save(newRoom);

        return map(result);
    }

    public RoomResponse update(Long id, RoomUpsertRequest request) {
        Room newRoom = mapper.map(request, Room.class);
        newRoom.setId(id);
        Room result = roomRepository.save(newRoom);

        return map(result);
    }

    public void delete(Long id) throws NotFoundException {
        if(roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
        } else {
            throw new NotFoundException("Delete room error");
        }
    }
}
