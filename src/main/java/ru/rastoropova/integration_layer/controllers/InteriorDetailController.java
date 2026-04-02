package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.InteriorDetailDTO;
import ru.rastoropova.integration_layer.mappers.InteriorDetailMapper;
import ru.rastoropova.service_layer.services.DetailService;
import ru.rastoropova.service_layer.users.Role;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/interior_details")
@AllArgsConstructor
public class InteriorDetailController {
    private DetailService service;
    private InteriorDetailMapper mapper;

    @GetMapping
    public List<InteriorDetailDTO> getAll(@RequestHeader("user_role") String role) {
        return service.viewInteriorDetailList(Role.valueOf(role)).stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    public InteriorDetailDTO getById(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        return mapper.toDTO(service.viewInteriorDetail(Role.valueOf(role), id));
    }

    @PostMapping
    public InteriorDetailDTO add(@RequestBody InteriorDetailDTO dto, @RequestHeader("user_role") String role) {
        service.addInteriorDetailToSystem(Role.valueOf(role), mapper.toDomain(dto));
        return dto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        service.deleteInteriorDetailFromSystem(Role.valueOf(role), id);
    }
}
