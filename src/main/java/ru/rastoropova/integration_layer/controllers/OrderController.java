package ru.rastoropova.integration_layer.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rastoropova.integration_layer.dto.CarPurchaseOrderDTO;
import ru.rastoropova.integration_layer.dto.CarWithConfigurationPurchaseOrderDTO;
import ru.rastoropova.integration_layer.dto.TestDriveOrderDTO;
import ru.rastoropova.integration_layer.mappers.CarPurchaseOrderMapper;
import ru.rastoropova.integration_layer.mappers.CarWithConfigurationPurchaseOrderMapper;
import ru.rastoropova.integration_layer.mappers.TestDriveOrderMapper;
import ru.rastoropova.integration_layer.requests.CarWithConfigurationOrderRequest;
import ru.rastoropova.integration_layer.requests.TestDriveOrderRequest;
import ru.rastoropova.service_layer.orders.CarPurchaseOrderStatus;
import ru.rastoropova.service_layer.orders.CarWithConfigurationPurchaseOrderStatus;
import ru.rastoropova.service_layer.orders.TestDriveOrderStatus;
import ru.rastoropova.service_layer.services.OrderService;
import ru.rastoropova.service_layer.users.Role;
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

    @GetMapping("/cars")
    public List<CarPurchaseOrderDTO> getCarOrders(@RequestHeader("user_role") String role) {
        return service.viewOrdersCars(Role.valueOf(role)).stream()
                .map(domain -> carOrderMapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/cars-with-configuration")
    public List<CarWithConfigurationPurchaseOrderDTO> getCarsWithConfigurationOrders(@RequestHeader("user_role") String role) {
        return service.viewOrdersCarsWithConfiguration(Role.valueOf(role)).stream()
                .map(domain -> carWithConfigurationOrderMapper.toDTO(domain))
                .toList();
    }

    @GetMapping("/test-drive")
    public List<TestDriveOrderDTO> getTestDriveOrders(@RequestHeader("user_role") String role) {
        return service.viewTestDriveApplications(Role.valueOf(role)).stream()
                .map(domain -> testDriveOrderMapper.toDTO(domain))
                .toList();
    }

    @PostMapping("/cars")
    public void orderCar(@RequestParam UUID carId, @RequestHeader("user_id") UUID id, @RequestHeader("user_role") String role) {
        service.orderCar(Role.valueOf(role), id, carId);
    }

    @PostMapping("/cars-with-configuration")
    public void orderCarWithConfiguration(@RequestBody CarWithConfigurationOrderRequest request, @RequestHeader("user_id") UUID id, @RequestHeader("user_role") String role) {
        service.orderCarWithConfiguration(Role.valueOf(role), id, request.getCarModelId(), request.getBodyId(), request.getEngineId(), request.getGearboxId(), request.getInteriorId(), request.getSteeringWheelId(), request.getWheelsId());
    }

    @PostMapping("/test-drive")
    public void signUpTestDrive(@RequestBody TestDriveOrderRequest request, @RequestHeader("user_id") UUID id, @RequestHeader("user_role") String role) {
        service.signUpTestDrive(Role.valueOf(role), id, request.getCarId(), request.getDate(), request.getTime());
    }

    @PutMapping("/cars/{orderId}/status")
    public void updateCarOrderStatus(@PathVariable UUID orderId, @RequestParam String status) {
        service.changeCarPurchaseOrderStatus(orderId, CarPurchaseOrderStatus.valueOf(status));
    }

    @PutMapping("/cars-with-configuration/{orderId}/status")
    public void updateCarWithConfigOrderStatus(@PathVariable UUID orderId, @RequestParam String status) {
        service.changeCarWithConfigurationPurchaseOrderStatus(orderId, CarWithConfigurationPurchaseOrderStatus.valueOf(status));
    }

    @PutMapping("/test-drive/{orderId}/status")
    public void updateTestDriveOrderStatus(@PathVariable UUID orderId, @RequestParam String status) {
        service.changeTestDriveOrderStatus(orderId, TestDriveOrderStatus.valueOf(status));
    }
}
