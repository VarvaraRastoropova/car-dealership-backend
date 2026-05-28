package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.*;
import ru.rastoropova.integration_layer.mappers.*;
import ru.rastoropova.integration_layer.requests.*;
import ru.rastoropova.service_layer.orders.*;
import ru.rastoropova.service_layer.services.OrderService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/car_purchase_orders")
@AllArgsConstructor
public class OrderController {
    private OrderService service;
    private CarPurchaseOrderMapper carOrderMapper;
    private CarWithConfigurationPurchaseOrderMapper carWithConfigurationOrderMapper;
    private TestDriveOrderMapper testDriveOrderMapper;

    @GetMapping("/cars/all")
    @PreAuthorize("hasAnyRole('CAR_MANAGER', 'SYSTEM_ADMINISTRATOR')")
    public List<CarPurchaseOrderDTO> getCarOrders() {
        return service.viewOrdersCars().stream()
                .map(domain -> carOrderMapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/cars/my")
    @PreAuthorize("isAuthenticated()")
    public List<CarPurchaseOrderDTO> getMyCarOrders(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return service.viewMyOrdersCars(userId).stream()
                .map(domain -> carOrderMapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/cars-with-configuration/all")
    @PreAuthorize("hasAnyRole('CAR_MANAGER', 'SYSTEM_ADMINISTRATOR')")
    public List<CarWithConfigurationPurchaseOrderDTO> getCarsWithConfigurationOrders() {
        return service.viewOrdersCarsWithConfiguration().stream()
                .map(domain -> carWithConfigurationOrderMapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/cars-with-configuration/my")
    @PreAuthorize("isAuthenticated()")
    public List<CarWithConfigurationPurchaseOrderDTO> getMyCarsWithConfigurationOrders(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return service.viewMyOrdersCarsWithConfiguration(userId).stream()
                .map(domain -> carWithConfigurationOrderMapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/test-drive/all")
    @PreAuthorize("hasAnyRole('CAR_MANAGER', 'SYSTEM_ADMINISTRATOR')")
    public List<TestDriveOrderDTO> getTestDriveOrders() {
        return service.viewTestDriveApplications().stream()
                .map(domain -> testDriveOrderMapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/test-drive/my")
    @PreAuthorize("isAuthenticated()")
    public List<TestDriveOrderDTO> getMyTestDriveOrders(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return service.viewMyTestDriveApplications(userId).stream()
                .map(domain -> testDriveOrderMapper.toDTO(domain))
                .toList();
    }

    @PostMapping("/cars")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SYSTEM_ADMINISTRATOR')")
    public CarPurchaseOrderDTO orderCar(@RequestBody CarPurchaseOrderRequest request, @AuthenticationPrincipal Jwt jwt) {
        UUID customerId = UUID.fromString(jwt.getSubject());
        var order = service.orderCar(customerId, request.getCarModelId(), request.getPrice());
        return carOrderMapper.toDTO(order);
    }

    @PostMapping("/cars-with-configuration")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SYSTEM_ADMINISTRATOR')")
    public CarWithConfigurationPurchaseOrderDTO orderCarWithConfiguration(@RequestBody CarWithConfigurationOrderRequest request, @AuthenticationPrincipal Jwt jwt) {
        UUID customerId = UUID.fromString(jwt.getSubject());
        var order = service.orderCarWithConfiguration(customerId, request.getCarModelId(),
                request.getBodyId(), request.getEngineId(), request.getGearboxId(),
                request.getInteriorId(), request.getSteeringWheelId(), request.getWheelsId(), request.getPrice());
        return carWithConfigurationOrderMapper.toDTO(order);
    }

    @PostMapping("/test-drive")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SYSTEM_ADMINISTRATOR')")
    public TestDriveOrderDTO signUpTestDrive(@RequestBody TestDriveOrderRequest request, @AuthenticationPrincipal Jwt jwt) {
        UUID customerId = UUID.fromString(jwt.getSubject());
        var order = service.signUpTestDrive(customerId, request.getCarId(), request.getDate(), request.getTime());
        return testDriveOrderMapper.toDTO(order);
    }

    @PutMapping("/cars/{orderId}/status")
    @PreAuthorize("hasAnyRole('CAR_MANAGER', 'SYSTEM_ADMINISTRATOR')")
    public CarPurchaseOrderDTO updateCarOrderStatus(@PathVariable UUID orderId, @RequestParam String status) {
        var order = service.changeCarPurchaseOrderStatus(orderId, CarPurchaseOrderStatus.valueOf(status));
        return carOrderMapper.toDTO(order);
    }

    @PutMapping("/cars/{orderId}/cancel")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SYSTEM_ADMINISTRATOR')")
    public CarPurchaseOrderDTO cancelMyCarPurchaseOrderStatus(@PathVariable UUID orderId) {
        var order = service.changeCarPurchaseOrderStatus(orderId, CarPurchaseOrderStatus.CANCELLED);
        return carOrderMapper.toDTO(order);
    }

    @PutMapping("/cars-with-configuration/{orderId}/status")
    @PreAuthorize("hasAnyRole('CAR_MANAGER', 'SYSTEM_ADMINISTRATOR')")
    public CarWithConfigurationPurchaseOrderDTO updateCarWithConfigOrderStatus(@PathVariable UUID orderId, @RequestParam String status) {
        var order = service.changeCarWithConfigurationPurchaseOrderStatus(orderId, CarWithConfigurationPurchaseOrderStatus.valueOf(status));
        return carWithConfigurationOrderMapper.toDTO(order);
    }

    @PutMapping("/cars-with-configuration/{orderId}/cancel")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SYSTEM_ADMINISTRATOR')")
    public CarWithConfigurationPurchaseOrderDTO cancelMyCarWithConfigurationPurchaseOrderStatus(@PathVariable UUID orderId) {
        var order = service.changeCarWithConfigurationPurchaseOrderStatus(orderId, CarWithConfigurationPurchaseOrderStatus.CANCELLED);
        return carWithConfigurationOrderMapper.toDTO(order);
    }

    @PutMapping("/test-drive/{orderId}/status")
    @PreAuthorize("hasAnyRole('CAR_MANAGER', 'SYSTEM_ADMINISTRATOR')")
    public TestDriveOrderDTO updateTestDriveOrderStatus(@PathVariable UUID orderId, @RequestParam String status) {
        var order = service.changeTestDriveOrderStatus(orderId, TestDriveOrderStatus.valueOf(status));
        return testDriveOrderMapper.toDTO(order);
    }

    @PutMapping("/test-drive/{orderId}/cancel")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SYSTEM_ADMINISTRATOR')")
    public TestDriveOrderDTO cancelMyTestDriveOrderStatus(@PathVariable UUID orderId) {
        var order = service.changeTestDriveOrderStatus(orderId, TestDriveOrderStatus.CANCELLED);
        return testDriveOrderMapper.toDTO(order);
    }
}
