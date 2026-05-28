package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.CarDTO;
import ru.rastoropova.integration_layer.grpc.CarGrpcClient;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cars")
@AllArgsConstructor
public class CarController {
    private CarGrpcClient carGrpcClient;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<CarDTO> getAvailableCars() {
        return carGrpcClient.getAvailableCars();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public CarDTO getCarById(@PathVariable UUID id) {
        return carGrpcClient.getCarById(id);
    }
}
