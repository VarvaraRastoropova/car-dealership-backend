package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.InteriorDetailDTO;
import ru.rastoropova.integration_layer.mappers.InteriorDetailMapper;
import ru.rastoropova.service_layer.services.DetailService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/interior_details")
@AllArgsConstructor
public class InteriorDetailController {
    private DetailService service;
    private InteriorDetailMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public List<InteriorDetailDTO> getAll() {
        return service.viewInteriorDetailList().stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public InteriorDetailDTO getById(@PathVariable UUID id) {
        return mapper.toDTO(service.viewInteriorDetail(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public InteriorDetailDTO add(@RequestBody InteriorDetailDTO dto) {
        var domain = mapper.toDomain(dto);
        service.addInteriorDetailToSystem(domain);
        return mapper.toDTO(domain);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public void delete(@PathVariable UUID id) {
        service.deleteInteriorDetailFromSystem(id);
    }
}
