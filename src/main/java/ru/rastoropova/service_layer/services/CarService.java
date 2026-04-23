package ru.rastoropova.service_layer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rastoropova.service_layer.cars.Car;
import ru.rastoropova.service_layer.cars.CarFilter;
import ru.rastoropova.service_layer.exceptions.DomainValidationException;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.CarRepository;
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

    public void addTestDriveCar(Car car) {
        if (!car.isAvailableForTestDrive()) {
            car.setAvailabilityForTestDrive(true);
        }
    }

    public void deleteTestDriveCar(UUID id) {
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

    public Car addCarToSystem(Car car) {
        if (car.getId() != null && carRepository.find(car.getId()) != null) {
            throw new DomainValidationException("Error: Car with id " + car.getId() + " already exists.");
        }
        return carRepository.save(car);
    }

    public void deleteCarFromSystem(UUID id) {
        if (carRepository.find(id) == null) {
            throw new EntityNotFoundException("Error: Car not found.");
        }
        carRepository.delete(id);
    }
}
