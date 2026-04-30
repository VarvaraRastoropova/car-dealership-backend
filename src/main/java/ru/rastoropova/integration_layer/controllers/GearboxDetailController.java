package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.GearboxDetailDTO;
import ru.rastoropova.integration_layer.mappers.GearboxDetailMapper;
import ru.rastoropova.service_layer.services.DetailService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/gearbox_details")
@AllArgsConstructor
public class GearboxDetailController {
    private DetailService service;
    private GearboxDetailMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public List<GearboxDetailDTO> getAll() {
        return service.viewGearboxDetailList().stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public GearboxDetailDTO getById(@PathVariable UUID id) {
        return mapper.toDTO(service.viewGearboxDetail(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public GearboxDetailDTO add(@RequestBody GearboxDetailDTO dto) {
        var domain = mapper.toDomain(dto);
        service.addGearboxDetailToSystem(domain);
        return mapper.toDTO(domain);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public void delete(@PathVariable UUID id) {
        service.deleteGearboxDetailFromSystem(id);
    }
}
