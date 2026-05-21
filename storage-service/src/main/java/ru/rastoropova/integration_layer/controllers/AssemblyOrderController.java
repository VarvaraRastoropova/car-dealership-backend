package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.AssemblyOrderDTO;
import ru.rastoropova.integration_layer.mappers.AssemblyOrderMapper;
import ru.rastoropova.service_layer.assembly.AssemblyOrderStatus;
import ru.rastoropova.service_layer.services.WarehouseService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assembly_orders")
@AllArgsConstructor
public class AssemblyOrderController {
    private WarehouseService service;
    private AssemblyOrderMapper mapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public AssemblyOrderDTO add(@RequestBody AssemblyOrderDTO dto) {
        var saved = service.createAssemblyOrder(dto);
        return mapper.toDTO(saved);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public AssemblyOrderDTO changeStatus(@PathVariable UUID id, @RequestParam String status) {
        var updated = service.changeAssemblyOrderStatus(id, AssemblyOrderStatus.valueOf(status));
        return mapper.toDTO(updated);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public AssemblyOrderDTO getById(@PathVariable UUID id) {
        return mapper.toDTO(service.viewAssemblyOrder(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public List<AssemblyOrderDTO> getAll() {
        return service.viewAssemblyOrderList().stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public AssemblyOrderDTO update(@PathVariable UUID id, @RequestBody AssemblyOrderDTO dto) {
        dto.setId(id);
        var updated = service.updateAssemblyOrder(dto);
        return mapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public void delete(@PathVariable UUID id) {
        service.deleteAssemblyOrder(id);
    }
}
