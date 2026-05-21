package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.EngineDetailDTO;
import ru.rastoropova.integration_layer.mappers.EngineDetailMapper;
import ru.rastoropova.service_layer.services.DetailService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/engine_details")
@AllArgsConstructor
public class EngineDetailController {
    private DetailService service;
    private EngineDetailMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public List<EngineDetailDTO> getAll() {
        return service.viewEngineDetailList().stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public EngineDetailDTO getById(@PathVariable UUID id) {
        return mapper.toDTO(service.viewEngineDetail(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public EngineDetailDTO add(@RequestBody EngineDetailDTO dto) {
        var domain = mapper.toDomain(dto);
        service.addEngineDetailToSystem(domain);
        return mapper.toDTO(domain);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public void delete(@PathVariable UUID id) {
        service.deleteEngineDetailFromSystem(id);
    }
}
