package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.SteeringWheelDetailDTO;
import ru.rastoropova.integration_layer.mappers.SteeringWheelDetailMapper;
import ru.rastoropova.service_layer.services.DetailService;
import ru.rastoropova.service_layer.users.Role;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/steering_wheel_details")
@AllArgsConstructor
public class SteeringWheelDetailController {
    private DetailService service;
    private SteeringWheelDetailMapper mapper;

    @GetMapping
    public List<SteeringWheelDetailDTO> getAll(@RequestHeader("user_role") String role) {
        return service.viewSteeringWheelDetailList(Role.valueOf(role)).stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    public SteeringWheelDetailDTO getById(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        return mapper.toDTO(service.viewSteeringWheelDetail(Role.valueOf(role), id));
    }

    @PostMapping
    public SteeringWheelDetailDTO add(@RequestBody SteeringWheelDetailDTO dto, @RequestHeader("user_role") String role) {
        service.addSteeringWheelDetailToSystem(Role.valueOf(role), mapper.toDomain(dto));
        return dto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        service.deleteSteeringWheelDetailFromSystem(Role.valueOf(role), id);
    }
}
