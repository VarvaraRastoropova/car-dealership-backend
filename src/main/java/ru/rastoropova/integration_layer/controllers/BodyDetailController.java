package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.BodyDetailDTO;
import ru.rastoropova.integration_layer.mappers.BodyDetailMapper;
import ru.rastoropova.service_layer.services.DetailService;
import ru.rastoropova.service_layer.users.Role;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/body_details")
@AllArgsConstructor
public class BodyDetailController {
    private DetailService service;
    private BodyDetailMapper mapper;

    @GetMapping
    public List<BodyDetailDTO> getAll(@RequestHeader("user_role") String role) {
        return service.viewBodyDetailList(Role.valueOf(role)).stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    public BodyDetailDTO getById(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        return mapper.toDTO(service.viewBodyDetail(Role.valueOf(role), id));
    }

    @PostMapping
    public BodyDetailDTO add(@RequestBody BodyDetailDTO dto, @RequestHeader("user_role") String role) {
        service.addBodyDetailToSystem(Role.valueOf(role), mapper.toDomain(dto));
        return dto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        service.deleteBodyDetailFromSystem(Role.valueOf(role), id);
    }
}
