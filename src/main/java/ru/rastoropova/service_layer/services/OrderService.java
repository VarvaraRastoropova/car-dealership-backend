package ru.rastoropova.service_layer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rastoropova.service_layer.car_characteristics.CarModel;
import ru.rastoropova.service_layer.cars.Car;
import ru.rastoropova.service_layer.cars.Configuration;
import ru.rastoropova.service_layer.exceptions.DomainValidationException;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.orders.*;
import ru.rastoropova.service_layer.repositories.*;
import ru.rastoropova.service_layer.users.Role;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
    private CarPurchaseOrderRepository carPurchaseOrders;
    private CarWithConfigurationPurchaseOrderRepository carWithConfigurationPurchaseOrders;
    private TestDriveOrderRepository testDriveOrders;
    private CarModelRepository carModels;
    private CarRepository cars;
    private BodyDetailRepository bodyDetails;
    private EngineDetailRepository engineDetails;
    private GearboxDetailRepository gearboxDetails;
    private InteriorDetailRepository interiorDetails;
    private SteeringWheelDetailRepository steeringWheelDetails;
    private WheelsDetailRepository wheelsDetails;

    public void orderCar(Role userRole, UUID userId, UUID carId) {
        if (userRole != Role.CUSTOMER) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        Car car = cars.find(carId);
        carPurchaseOrders.find(carId);
        CarPurchaseOrder order = new CarPurchaseOrder(
                UUID.randomUUID(),
                userId,
                userId,
                carId,
                car.getPrice()
        );
        carPurchaseOrders.save(order);
    }

    public void orderCarWithConfiguration(Role userRole, UUID userId, UUID carModelId, UUID bodyId, UUID engineId, UUID gearboxId, UUID interiorId, UUID steeringWheelId, UUID wheelsId) {
        if (userRole != Role.CUSTOMER) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        CarModel model = carModels.find(carModelId);
        Configuration configuration = new Configuration(bodyDetails.find(bodyId), engineDetails.find(engineId), gearboxDetails.find(gearboxId), interiorDetails.find(interiorId), steeringWheelDetails.find(steeringWheelId), wheelsDetails.find(wheelsId));
        int price = configuration.calculatePrice(model);
        CarWithConfigurationPurchaseOrder order = new CarWithConfigurationPurchaseOrder(
                UUID.randomUUID(),
                userId,
                userId,
                carModelId,
                price,
                configuration
        );
        carWithConfigurationPurchaseOrders.save(order);
    }

    public void signUpTestDrive(Role userRole, UUID userId, UUID carId, String date, String time) {
        if (userRole != Role.CUSTOMER) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        TestDriveOrder order = new TestDriveOrder(
                UUID.randomUUID(),
                userId,
                carId,
                date,
                time
        );
        testDriveOrders.save(order);
    }

    public List<CarPurchaseOrder> viewOrdersCars(Role userRole) {
        if (userRole != Role.CAR_MANAGER && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return carPurchaseOrders.findAll();
    }

    public List<CarWithConfigurationPurchaseOrder> viewOrdersCarsWithConfiguration(Role userRole) {
        if (userRole != Role.CAR_MANAGER && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return carWithConfigurationPurchaseOrders.findAll();
    }

    public List<TestDriveOrder> viewTestDriveApplications(Role userRole) {
        if (userRole != Role.CAR_MANAGER && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return testDriveOrders.findAll();
    }

    public void changeCarPurchaseOrderStatus(UUID orderId, CarPurchaseOrderStatus newStatus) {
        CarPurchaseOrder order = carPurchaseOrders.find(orderId);
        if (order == null) {
            throw new EntityNotFoundException("Error: Order not found.");
        }
        CarPurchaseOrderStatus orderStatus = order.getStatus();
        if (!orderStatus.isAllowedStatus(newStatus)) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        order.setStatus(newStatus);
    }

    public void changeCarWithConfigurationPurchaseOrderStatus(UUID orderId, CarWithConfigurationPurchaseOrderStatus newStatus) {
        CarWithConfigurationPurchaseOrder order = carWithConfigurationPurchaseOrders.find(orderId);
        if (order == null) {
            throw new EntityNotFoundException("Error: Order not found.");
        }
        CarWithConfigurationPurchaseOrderStatus orderStatus = order.getStatus();
        if (!orderStatus.isAllowedStatus(newStatus)) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        order.setStatus(newStatus);
    }

    public void changeTestDriveOrderStatus(UUID orderId, TestDriveOrderStatus newStatus) {
        TestDriveOrder order = testDriveOrders.find(orderId);
        if (order == null) {
            throw new EntityNotFoundException("Error: Order not found.");
        }
        TestDriveOrderStatus orderStatus = order.getStatus();
        if (!orderStatus.isAllowedStatus(newStatus)) {
            throw new DomainValidationException("Error: Invalid status.");
        }
        order.setStatus(newStatus);
    }
}
