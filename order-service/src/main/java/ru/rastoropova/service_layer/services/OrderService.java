package ru.rastoropova.service_layer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rastoropova.messaging.OutboxService;
import ru.rastoropova.messaging.events.OrderSentForApprovalEvent;
import ru.rastoropova.service_layer.exceptions.DomainValidationException;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.orders.*;
import ru.rastoropova.service_layer.repositories.*;
import ru.rastoropova.service_layer.users.Role;
import ru.rastoropova.service_layer.users.User;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class OrderService {
    private UserRepository users;
    private CarPurchaseOrderRepository carPurchaseOrders;
    private CarWithConfigurationPurchaseOrderRepository carWithConfigurationPurchaseOrders;
    private TestDriveOrderRepository testDriveOrders;
    private OutboxService outboxService;

    private UUID getCarManagerId() {
        List<User> carManagers = users.findAllByRole(Role.CAR_MANAGER);
        if (carManagers.isEmpty()) {
            throw new EntityNotFoundException("Error: Car Manager not found.");
        }
        int randomId = ThreadLocalRandom.current().nextInt(0, carManagers.size());
        return carManagers.get(randomId).getId();
    }

    public CarPurchaseOrder orderCar(UUID customerId, UUID carModelId, int price) {
        CarPurchaseOrder order = new CarPurchaseOrder(
                UUID.randomUUID(),
                customerId,
                getCarManagerId(),
                carModelId,
                price
        );
        order = carPurchaseOrders.save(order);
        return order;
    }

    public CarWithConfigurationPurchaseOrder orderCarWithConfiguration(UUID customerId, UUID carModelId, UUID bodyId, UUID engineId, UUID gearboxId, UUID interiorId, UUID steeringWheelId, UUID wheelsId, int price) {
        CarWithConfigurationPurchaseOrder order = new CarWithConfigurationPurchaseOrder(
                UUID.randomUUID(),
                customerId,
                getCarManagerId(),
                carModelId,
                price,
                bodyId,
                engineId,
                gearboxId,
                interiorId,
                steeringWheelId,
                wheelsId
        );
        carWithConfigurationPurchaseOrders.save(order);
        return order;
    }

    public TestDriveOrder signUpTestDrive(UUID customerId, UUID carId, String date, String time) {
        TestDriveOrder order = new TestDriveOrder(
                UUID.randomUUID(),
                customerId,
                carId,
                date,
                time
        );
        testDriveOrders.save(order);
        return order;
    }

    public CarPurchaseOrder viewOrderCar(UUID id) {
        return carPurchaseOrders.find(id);
    }

    public List<CarPurchaseOrder> viewOrdersCars() {
        return carPurchaseOrders.findAll();
    }

    public List<CarPurchaseOrder> viewMyOrdersCars(UUID userId) {
        return carPurchaseOrders.findByUserId(userId);
    }

    public CarWithConfigurationPurchaseOrder  viewOrderCarWithConfiguration(UUID id) {
        return carWithConfigurationPurchaseOrders.find(id);
    }

    public List<CarWithConfigurationPurchaseOrder> viewOrdersCarsWithConfiguration() {
        return carWithConfigurationPurchaseOrders.findAll();
    }

    public List<CarWithConfigurationPurchaseOrder> viewMyOrdersCarsWithConfiguration(UUID userId) {
        return carWithConfigurationPurchaseOrders.findByUserId(userId);
    }

    public List<TestDriveOrder> viewTestDriveApplications() {
        return testDriveOrders.findAll();
    }

    public List<TestDriveOrder> viewMyTestDriveApplications(UUID userId) {
        return testDriveOrders.findByUserId(userId);
    }

    @Transactional
    public CarPurchaseOrder changeCarPurchaseOrderStatus(UUID orderId, CarPurchaseOrderStatus newStatus) {
        CarPurchaseOrder order = carPurchaseOrders.find(orderId);
        if (order == null) {
            throw new EntityNotFoundException("Error: Order not found.");
        }
        CarPurchaseOrderStatus orderStatus = order.getStatus();
        if (!orderStatus.isAllowedStatus(newStatus)) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        order.setStatus(newStatus);
        order = carPurchaseOrders.update(order);
        if (newStatus == CarPurchaseOrderStatus.PAID) {
            OrderSentForApprovalEvent event = new OrderSentForApprovalEvent();
            event.setEventId(UUID.randomUUID());
            event.setOrderId(order.getId());
            event.setType("CAR_PURCHASE");
            event.setCarModelId(order.getCarModelId());
            outboxService.saveEvent(order.getId(), "CAR_PURCHASE", event);
        }
        return order;
    }

    @Transactional
    public CarWithConfigurationPurchaseOrder changeCarWithConfigurationPurchaseOrderStatus(UUID orderId, CarWithConfigurationPurchaseOrderStatus newStatus) {
        CarWithConfigurationPurchaseOrder order = carWithConfigurationPurchaseOrders.find(orderId);
        if (order == null) {
            throw new EntityNotFoundException("Error: Order not found.");
        }
        CarWithConfigurationPurchaseOrderStatus orderStatus = order.getStatus();
        if (!orderStatus.isAllowedStatus(newStatus)) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        order.setStatus(newStatus);
        order = carWithConfigurationPurchaseOrders.update(order);
        if (newStatus == CarWithConfigurationPurchaseOrderStatus.PAID) {
            OrderSentForApprovalEvent event = new OrderSentForApprovalEvent();
            event.setEventId(UUID.randomUUID());
            event.setOrderId(order.getId());
            event.setType("CAR_WITH_CONFIGURATION");
            event.setCarModelId(order.getCarModelId());
            outboxService.saveEvent(order.getId(), "CAR_WITH_CONFIGURATION", event);
        }
        return order;
    }

    public TestDriveOrder changeTestDriveOrderStatus(UUID orderId, TestDriveOrderStatus newStatus) {
        TestDriveOrder order = testDriveOrders.find(orderId);
        if (order == null) {
            throw new EntityNotFoundException("Error: Order not found.");
        }
        TestDriveOrderStatus orderStatus = order.getStatus();
        if (!orderStatus.isAllowedStatus(newStatus)) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        order.setStatus(newStatus);
        order = testDriveOrders.update(order);
        return order;
    }
}
