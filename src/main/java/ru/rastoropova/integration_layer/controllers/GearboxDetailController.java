package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.GearboxDetailDTO;
import ru.rastoropova.integration_layer.mappers.GearboxDetailMapper;
import ru.rastoropova.service_layer.services.DetailService;
import ru.rastoropova.service_layer.users.Role;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/gearbox_details")
@AllArgsConstructor
public class GearboxDetailController {
    private DetailService service;
    private GearboxDetailMapper mapper;

    @GetMapping
    public List<GearboxDetailDTO> getAll(@RequestHeader("user_role") String role) {
        return service.viewGearboxDetailList(Role.valueOf(role)).stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    public GearboxDetailDTO getById(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        return mapper.toDTO(service.viewGearboxDetail(Role.valueOf(role), id));
    }

    @PostMapping
    public GearboxDetailDTO add(@RequestBody GearboxDetailDTO dto, @RequestHeader("user_role") String role) {
        service.addGearboxDetailToSystem(Role.valueOf(role), mapper.toDomain(dto));
        return dto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        service.deleteGearboxDetailFromSystem(Role.valueOf(role), id);
    }
}
