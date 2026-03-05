package ru.rastoropova.business_logic.services;

import ru.rastoropova.business_logic.car_characteristics.CarModel;
import ru.rastoropova.business_logic.cars.Car;
import ru.rastoropova.business_logic.cars.Configuration;
import ru.rastoropova.business_logic.exceptions.DomainValidationException;
import ru.rastoropova.business_logic.exceptions.EntityNotFoundException;
import ru.rastoropova.business_logic.orders.*;
import ru.rastoropova.business_logic.repositories.CarPurchaseOrderRepository;
import ru.rastoropova.business_logic.repositories.CarRepository;
import ru.rastoropova.business_logic.repositories.CarWithConfigurationPurchaseOrderRepository;
import ru.rastoropova.business_logic.repositories.TestDriveOrderRepository;
import ru.rastoropova.business_logic.users.Role;
import ru.rastoropova.business_logic.users.User;

import java.util.List;

public class OrderService {
    private CarPurchaseOrderRepository carPurchaseOrders;
    private CarWithConfigurationPurchaseOrderRepository carWithConfigurationPurchaseOrders;
    private TestDriveOrderRepository testDriveOrders;
    private CarRepository cars;

    public OrderService(CarPurchaseOrderRepository carPurchaseOrders,
                        CarWithConfigurationPurchaseOrderRepository carWithConfigurationPurchaseOrders,
                        TestDriveOrderRepository testDriveOrders) {
        this.carPurchaseOrders = carPurchaseOrders;
        this.carWithConfigurationPurchaseOrders = carWithConfigurationPurchaseOrders;
        this.testDriveOrders = testDriveOrders;
    }

    public void orderCar(User user, int carId) {
        if (user.getRole() != Role.CUSTOMER) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        Car car = cars.find(carId);
        carPurchaseOrders.find(carId);
        CarPurchaseOrder order = new CarPurchaseOrder(
                carPurchaseOrders.findAll().size() + 1,
                user.getId(),
                user.getId(),
                carId,
                car.getPrice()
        );
        carPurchaseOrders.save(order);
    }

    public void orderCarWithConfiguration(User user, CarModel model, Configuration configuration) {
        if (user.getRole() != Role.CUSTOMER) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        int price = configuration.calculatePrice(model);
        CarWithConfigurationPurchaseOrder order = new CarWithConfigurationPurchaseOrder(
                carWithConfigurationPurchaseOrders.findAll().size() + 1,
                user.getId(),
                user.getId(),
                model.getId(),
                price,
                configuration
        );
        carWithConfigurationPurchaseOrders.save(order);
    }

    public void signUpTestDrive(User user, int carId, String date, String time) {
        if (user.getRole() != Role.CUSTOMER) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        TestDriveOrder order = new TestDriveOrder(
                testDriveOrders.findAll().size() + 1,
                user.getId(),
                carId,
                date,
                time
        );
        testDriveOrders.save(order);
    }

    public List<CarPurchaseOrder> viewOrdersCars(User user) {
        if (user.getRole() != Role.CAR_MANAGER && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return carPurchaseOrders.findAll();
    }

    public List<CarWithConfigurationPurchaseOrder> viewOrdersCarsWithConfiguration(User user) {
        if (user.getRole() != Role.CAR_MANAGER && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return carWithConfigurationPurchaseOrders.findAll();
    }

    public List<TestDriveOrder> viewTestDriveApplications(User user) {
        if (user.getRole() != Role.CAR_MANAGER && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        return testDriveOrders.findAll();
    }

    public void changeCarPurchaseOrderStatus(int orderId, CarPurchaseOrderStatus newStatus) {
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

    public void changeCarWithConfigurationPurchaseOrderStatus(int orderId, CarWithConfigurationPurchaseOrderStatus newStatus) {
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

    public void changeTestDriveOrderStatus(int orderId, TestDriveOrderStatus newStatus) {
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
