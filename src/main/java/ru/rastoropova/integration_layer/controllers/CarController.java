package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.CarDTO;
import ru.rastoropova.integration_layer.mappers.CarMapper;
import ru.rastoropova.service_layer.cars.CarFilter;
import ru.rastoropova.service_layer.services.CarService;
import ru.rastoropova.service_layer.users.Role;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {
    private CarService service;
    private CarMapper mapper;

    @GetMapping
    public List<CarDTO> getAll() {
        return service.viewCarList().stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/{id}")
    public CarDTO getById(@PathVariable UUID id) {
        return mapper.toDTO(service.viewCar(id));
    }

    @GetMapping("/filter")
    public List<CarDTO> getByFilter(@RequestParam int price, @RequestParam  String brand,
                                    @RequestParam  UUID bodyId, @RequestParam UUID engineId,
                                    @RequestParam UUID gearboxId, @RequestParam UUID interiorId,
                                    @RequestParam UUID steeringWheelId, @RequestParam UUID wheelsId) {
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
    public List<CarDTO> getCarsForTestDrive() {
        return service.viewCarsForTestDrive().stream()
                .map(domain -> mapper.toDTO(domain))
                .toList();
    }

    @PostMapping
    public CarDTO add(@RequestBody CarDTO dto, @RequestHeader("user_role") String role) {
        service.addCarToSystem(Role.valueOf(role), mapper.toDomain(dto));
        return dto;
    }

    @PostMapping("/{id}/test-drive")
    public CarDTO addTestDriveCar(@RequestBody CarDTO dto, @RequestHeader("user_role") String role) {
        service.addTestDriveCar(Role.valueOf(role), mapper.toDomain(dto));
        return dto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        service.deleteCarFromSystem(Role.valueOf(role), id);
    }

    @DeleteMapping("/{id}/test-drive")
    public void deleteTestDriveCar(@PathVariable UUID id, @RequestHeader("user_role") String role) {
        service.deleteTestDriveCar(Role.valueOf(role), id);
    }
}
