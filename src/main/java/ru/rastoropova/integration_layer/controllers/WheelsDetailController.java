package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.WheelsDetailDTO;
import ru.rastoropova.integration_layer.mappers.WheelsDetailMapper;
import ru.rastoropova.service_layer.services.DetailService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/wheels_details")
@AllArgsConstructor
public class WheelsDetailController {
    private DetailService service;
    private WheelsDetailMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public List<WheelsDetailDTO> getAll() {
        return service.viewWheelsDetailList().stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public WheelsDetailDTO getById(@PathVariable UUID id) {
        return mapper.toDTO(service.viewWheelsDetail(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public WheelsDetailDTO add(@RequestBody WheelsDetailDTO dto) {
        var domain = mapper.toDomain(dto);
        service.addWheelsDetailToSystem(domain);
        return mapper.toDTO(domain);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public void delete(@PathVariable UUID id) {
        service.deleteWheelsDetailFromSystem(id);
    }
}
