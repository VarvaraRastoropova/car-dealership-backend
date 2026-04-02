package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.EngineDetailDTO;
import ru.rastoropova.integration_layer.mappers.EngineDetailMapper;
import ru.rastoropova.service_layer.services.DetailService;
import ru.rastoropova.service_layer.users.Role;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/engine_details")
@AllArgsConstructor
public class EngineDetailController {
    private DetailService service;
    private EngineDetailMapper mapper;

    @GetMapping
    public List<EngineDetailDTO> getAll(@RequestHeader("user_role") String role) {
        return service.viewEngineDetailList(Role.valueOf(role)).stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    public EngineDetailDTO getById(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        return mapper.toDTO(service.viewEngineDetail(Role.valueOf(role), id));
    }

    @PostMapping
    public EngineDetailDTO add(@RequestBody EngineDetailDTO dto, @RequestHeader("user_role") String role) {
        service.addEngineDetailToSystem(Role.valueOf(role), mapper.toDomain(dto));
        return dto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        service.deleteEngineDetailFromSystem(Role.valueOf(role), id);
    }
}
