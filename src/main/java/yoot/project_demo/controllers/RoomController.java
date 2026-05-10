package yoot.project_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoot.project_demo.Service.RoomService;
import yoot.project_demo.common.ApiResponse;
import yoot.project_demo.dto.Room.RoomResponse;
import yoot.project_demo.dto.Room.RoomUpsertRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ApiResponse<List<RoomResponse>> findAll() {
        return ApiResponse.success(roomService.findByAll());
    }

    @GetMapping(value = "/{id}")
    public ApiResponse<RoomResponse> findById(@PathVariable Long id) {
        Optional<RoomResponse> roomResponse = roomService.findById(id);
        return roomResponse.map(ApiResponse::success)
                .orElseGet(() -> ApiResponse.error("Not found", new RoomResponse()));
    }

    @PostMapping
    public ApiResponse<RoomResponse> create(@RequestBody RoomUpsertRequest request) {
        return ApiResponse.success(roomService.create(request));
    }

    @PutMapping(value = "/{id}")
    public ApiResponse<RoomResponse> update(@PathVariable Long id, @RequestBody RoomUpsertRequest request) {
        return ApiResponse.success(roomService.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ApiResponse.successMessage("Xóa room thành công");
    }
}
