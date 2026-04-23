package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.CarDTO;
import ru.rastoropova.integration_layer.mappers.CarMapper;
import ru.rastoropova.service_layer.cars.CarFilter;
import ru.rastoropova.service_layer.services.CarService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {
    private CarService service;
    private CarMapper mapper;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<CarDTO> getAll() {
        return service.viewCarList().stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public CarDTO getById(@PathVariable UUID id) {
        return mapper.toDTO(service.viewCar(id));
    }

    @GetMapping("/filter")
    @PreAuthorize("isAuthenticated()")
    public List<CarDTO> getByFilter(@RequestParam(required = false) Integer price, @RequestParam(required = false) String brand,
                                    @RequestParam(required = false) UUID bodyId, @RequestParam(required = false) UUID engineId,
                                    @RequestParam(required = false) UUID gearboxId, @RequestParam(required = false) UUID interiorId,
                                    @RequestParam(required = false) UUID steeringWheelId, @RequestParam(required = false) UUID wheelsId) {
        CarFilter filter = new CarFilter();
        filter.setPrice(price);
        filter.setBrand(brand);
        filter.setBodyId(bodyId);
        filter.setEngineId(engineId);
        filter.setGearboxId(gearboxId);
        filter.setInteriorId(interiorId);
        filter.setSteeringWheelId(steeringWheelId);
        filter.setWheelsId(wheelsId);
        return service.viewFilterCarList(filter).stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/test-drive")
    @PreAuthorize("isAuthenticated()")
    public List<CarDTO> getCarsForTestDrive() {
        return service.viewCarsForTestDrive().stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('WAREHOUSE_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    public CarDTO add(@RequestBody CarDTO dto) {
        service.addCarToSystem(mapper.toDomain(dto));
        return dto;
    }

    @PostMapping("/{id}/test-drive")
    @PreAuthorize("hasAnyRole('CAR_MANAGER', 'SYSTEM_ADMINISTRATOR')")
    public CarDTO addTestDriveCar(@RequestBody CarDTO dto) {
        var domain = mapper.toDomain(dto);
        service.addTestDriveCar(domain);
        return mapper.toDTO(domain);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public void delete(@PathVariable UUID id) {
        service.deleteCarFromSystem(id);
    }

    @DeleteMapping("/{id}/test-drive")
    @PreAuthorize("hasAnyRole('CAR_MANAGER', 'SYSTEM_ADMINISTRATOR')")
    public void deleteTestDriveCar(@PathVariable UUID id) {
        service.deleteTestDriveCar(id);
    }
}
