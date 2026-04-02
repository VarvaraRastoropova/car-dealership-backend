package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.WheelsDetailDTO;
import ru.rastoropova.integration_layer.mappers.WheelsDetailMapper;
import ru.rastoropova.service_layer.services.DetailService;
import ru.rastoropova.service_layer.users.Role;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/wheels_details")
@AllArgsConstructor
public class WheelsDetailController {
    private DetailService service;
    private WheelsDetailMapper mapper;

    @GetMapping
    public List<WheelsDetailDTO> getAll(@RequestHeader("user_role") String role) {
        return service.viewWheelsDetailList(Role.valueOf(role)).stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    public WheelsDetailDTO getById(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        return mapper.toDTO(service.viewWheelsDetail(Role.valueOf(role), id));
    }

    @PostMapping
    public WheelsDetailDTO add(@RequestBody WheelsDetailDTO dto, @RequestHeader("user_role") String role) {
        service.addWheelsDetailToSystem(Role.valueOf(role), mapper.toDomain(dto));
        return dto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        service.deleteWheelsDetailFromSystem(Role.valueOf(role), id);
    }
}
