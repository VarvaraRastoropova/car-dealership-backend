package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.SteeringWheelDetailDTO;
import ru.rastoropova.integration_layer.mappers.SteeringWheelDetailMapper;
import ru.rastoropova.service_layer.services.DetailService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/steering_wheel_details")
@AllArgsConstructor
public class SteeringWheelDetailController {
    private DetailService service;
    private SteeringWheelDetailMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public List<SteeringWheelDetailDTO> getAll() {
        return service.viewSteeringWheelDetailList().stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public SteeringWheelDetailDTO getById(@PathVariable UUID id) {
        return mapper.toDTO(service.viewSteeringWheelDetail(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public SteeringWheelDetailDTO add(@RequestBody SteeringWheelDetailDTO dto) {
        var domain = mapper.toDomain(dto);
        service.addSteeringWheelDetailToSystem(domain);
        return mapper.toDTO(domain);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public void delete(@PathVariable UUID id) {
        service.deleteSteeringWheelDetailFromSystem(id);
    }
}
