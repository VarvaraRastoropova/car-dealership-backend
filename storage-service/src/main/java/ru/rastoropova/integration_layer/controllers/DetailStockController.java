package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.DetailStockDTO;
import ru.rastoropova.integration_layer.mappers.DetailStockMapper;
import ru.rastoropova.service_layer.details.DetailStock;
import ru.rastoropova.service_layer.services.DetailStockService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/detail_stocks")
@AllArgsConstructor
public class DetailStockController {
    private DetailStockService service;
    private DetailStockMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public List<DetailStockDTO> getAll() {
        return service.viewDetailStockList().stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public DetailStockDTO getById(@PathVariable UUID id) {
        return mapper.toDTO(service.viewDetailStock(id));
    }

    @GetMapping("/by-detail/{detailId}")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public DetailStockDTO getByDetailId(@RequestParam UUID detailId) {
        return mapper.toDTO(service.viewDetailStockByDetailId(detailId));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public DetailStockDTO add(@RequestBody DetailStockDTO dto) {
        var domain = mapper.toDomain(dto);
        service.addDetailStock(domain);
        return mapper.toDTO(domain);
    }

    @PostMapping("/reserve")
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public DetailStockDTO reserve(@RequestParam UUID detailId, @RequestParam int quantity) {
        DetailStock reserved = service.reserveDetailStock(detailId, quantity);
        return mapper.toDTO(reserved);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public void delete(@PathVariable UUID id) {
        service.deleteDetailStock(id);
    }
}
