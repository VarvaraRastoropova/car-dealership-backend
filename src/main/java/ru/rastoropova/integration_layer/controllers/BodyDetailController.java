package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.BodyDetailDTO;
import ru.rastoropova.integration_layer.mappers.BodyDetailMapper;
import ru.rastoropova.service_layer.services.DetailService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/body_details")
@AllArgsConstructor
public class BodyDetailController {
    private DetailService service;
    private BodyDetailMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public List<BodyDetailDTO> getAll() {
        return service.viewBodyDetailList().stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public BodyDetailDTO getById(@PathVariable UUID id) {
        return mapper.toDTO(service.viewBodyDetail(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public BodyDetailDTO add(@RequestBody BodyDetailDTO dto) {
        var domain = mapper.toDomain(dto);
        var saved = service.addBodyDetailToSystem(domain);
        return mapper.toDTO(saved);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public void delete(@PathVariable UUID id) {
        service.deleteBodyDetailFromSystem(id);
    }
}
