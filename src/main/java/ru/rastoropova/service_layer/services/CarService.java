package ru.rastoropova.service_layer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rastoropova.service_layer.cars.Car;
import ru.rastoropova.service_layer.cars.CarFilter;
import ru.rastoropova.service_layer.exceptions.DomainValidationException;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.CarRepository;
import ru.rastoropova.service_layer.users.Role;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarService {
    private CarRepository carRepository;

    public Car viewCar(UUID id) {
        if (carRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Car not found.");
        }
        return carRepository.find(id);
    }

    public List<Car> viewCarList() {
        return carRepository.findAll();
    }

    public List<Car> viewFilterCarList(CarFilter filter) {
        return carRepository.findByFilter(filter);
    }

    public void addTestDriveCar(Role userRole, Car car) {
        if (userRole != Role.CAR_MANAGER && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (!car.isAvailableForTestDrive()) {
            car.setAvailabilityForTestDrive(true);
        }
    }

    public void deleteTestDriveCar(Role userRole, UUID id) {
        if (userRole != Role.CAR_MANAGER && userRole != Role.SYSTEM_ADMINISTRATOR) {
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

    public void addCarToSystem(Role userRole, Car car) {
        if (userRole != Role.WAREHOUSE_ADMINISTRATOR && userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        }
        if (car.getId() != null && carRepository.find(car.getId()) != null) {
            throw new DomainValidationException("Error: Car with id " + car.getId() + " already exists.");
        }
        carRepository.save(car);
    }

    public void deleteCarFromSystem(Role userRole, UUID id) {
        if (userRole != Role.SYSTEM_ADMINISTRATOR) {
            throw new DomainValidationException("Error: You do not have sufficient permissions to perform this operation.");
        };
        if (carRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Car not found.");
        }
        carRepository.delete(id);
    }
}
