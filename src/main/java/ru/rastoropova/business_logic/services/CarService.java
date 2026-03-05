package ru.rastoropova.business_logic.services;

import ru.rastoropova.business_logic.cars.Car;
import ru.rastoropova.business_logic.cars.CarFilter;
import ru.rastoropova.business_logic.exceptions.DomainValidationException;
import ru.rastoropova.business_logic.exceptions.EntityNotFoundException;
import ru.rastoropova.business_logic.repositories.CarRepository;
import ru.rastoropova.business_logic.users.Role;
import ru.rastoropova.business_logic.users.User;

import java.util.List;

public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car viewCar(int id) {
        if (carRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Car not found.");
        }
        return carRepository.find(id);
    }

    public List<Car> viewCarList() {
        return carRepository.findAll();
    }

    public List<Car> viewFilterCarList(CarFilter carFilter) {
        return carRepository.findAll().stream()
                .filter(car ->
                        carFilter.getPrice() == 0 || car.getPrice() == carFilter.getPrice())
                .filter(car ->
                        carFilter.getBrand() == null || car.getModel().getBrand() == carFilter.getBrand())
                .filter(car ->
                        carFilter.getBody() == null || car.getConfiguration().getBody() == carFilter.getBody())
                .filter(car ->
                        carFilter.getEngine() == null || car.getConfiguration().getEngine() == carFilter.getEngine())
                .filter(car ->
                        carFilter.getGearbox() == null || car.getConfiguration().getGearbox() == carFilter.getGearbox())
                .filter(car ->
                        carFilter.getInterior() == null || car.getConfiguration().getInterior() == carFilter.getInterior())
                .filter(car ->
                        carFilter.getSteeringWheel() == null || car.getConfiguration().getSteeringWheel() == carFilter.getSteeringWheel())
                .filter(car ->
                        carFilter.getWheels() == null || car.getConfiguration().getWheels() == carFilter.getWheels()).toList();
    }

    public void addTestDriveCar(User user, Car car) {
        if (user.getRole() != Role.CAR_MANAGER && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (!car.isAvailableForTestDrive()) {
            car.setAvailabilityForTestDrive(true);
        }
    }

    public void deleteTestDriveCar(User user, int id) {
        if (user.getRole() != Role.CAR_MANAGER && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        Car car = carRepository.find(id);
        if (car == null) {
            throw new EntityNotFoundException("Error: Car not found.");
        }
        if (car.isAvailableForTestDrive()) {
            car.setAvailabilityForTestDrive(false);
        }
    }

    public List<Car> viewCarsForTestDrive() {
        return carRepository.findAll().stream().filter(car -> car.isAvailableForTestDrive()).toList();
    }

    public void addCarToSystem(User user, Car car) {
        if (user.getRole() != Role.WAREHOUSE_ADMINISTRATOR && user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (carRepository.find(car.getId()) != null) {
            throw new DomainValidationException("Error: Car with id " + car.getId() + " already exists.");
        }
        carRepository.save(car);
    }

    public void deleteCarFromSystem(User user, int id) {
        if (user.getRole() != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        };
        if (carRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Car not found.");
        }
        carRepository.delete(id);
    }
}
