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
    private CarModelRepository carModels;
    private CarRepository cars;
    private BodyDetailRepository bodyDetails;
    private EngineDetailRepository engineDetails;
    private GearboxDetailRepository gearboxDetails;
    private InteriorDetailRepository interiorDetails;
    private SteeringWheelDetailRepository steeringWheelDetails;
    private WheelsDetailRepository wheelsDetails;

    private UUID getCarManagerId() {
        List<User> carManagers = users.findAllByRole(Role.CAR_MANAGER);
        if (carManagers.isEmpty()) {
            throw new EntityNotFoundException("Error: Car Manager not found.");
        }
        int randomId = ThreadLocalRandom.current().nextInt(0, carManagers.size());
        return carManagers.get(randomId).getId();
    }

    public CarPurchaseOrder orderCar(UUID customerId, UUID carId) {
        Car car = cars.find(carId);
        if (car == null) {
            throw new EntityNotFoundException("Error: Car not found.");
        }
        CarPurchaseOrder order = new CarPurchaseOrder(
                UUID.randomUUID(),
                customerId,
                getCarManagerId(),
                car.getModel().getId(),
                car.getPrice()
        );
        carPurchaseOrders.save(order);
        return order;
    }

    public CarWithConfigurationPurchaseOrder orderCarWithConfiguration(UUID customerId, UUID carModelId, UUID bodyId, UUID engineId, UUID gearboxId, UUID interiorId, UUID steeringWheelId, UUID wheelsId) {
        CarModel model = carModels.find(carModelId);
        Configuration configuration = new Configuration(bodyDetails.find(bodyId), engineDetails.find(engineId), gearboxDetails.find(gearboxId), interiorDetails.find(interiorId), steeringWheelDetails.find(steeringWheelId), wheelsDetails.find(wheelsId));
        int price = configuration.calculatePrice(model);
        CarWithConfigurationPurchaseOrder order = new CarWithConfigurationPurchaseOrder(
                UUID.randomUUID(),
                customerId,
                getCarManagerId(),
                carModelId,
                price,
                configuration
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

    public List<CarPurchaseOrder> viewOrdersCars() {
        return carPurchaseOrders.findAll();
    }

    public List<CarPurchaseOrder> viewMyOrdersCars(UUID userId) {
        return carPurchaseOrders.findByUserId(userId);
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
        return order;
    }

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
