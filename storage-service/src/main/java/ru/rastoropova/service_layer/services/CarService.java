package ru.rastoropova.service_layer.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rastoropova.integration_layer.dto.CarDTO;
import ru.rastoropova.integration_layer.mappers.CarMapper;
import ru.rastoropova.service_layer.car_characteristics.CarModel;
import ru.rastoropova.service_layer.cars.Car;
import ru.rastoropova.service_layer.cars.CarFilter;
import ru.rastoropova.service_layer.exceptions.DomainValidationException;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.CarModelRepository;
import ru.rastoropova.service_layer.repositories.CarRepository;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarService {
    private CarModelRepository carModelRepository;
    private CarRepository carRepository;
    private CarMapper carMapper;

    private Car fromDTO(CarDTO carDTO) {
        Car car = carMapper.toDomain(carDTO);
        CarModel model = carModelRepository.find(carDTO.getModelId());
        car.setModel(model);
        return car;
    }

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

    public void addTestDriveCar(CarDTO dto) {
        Car car = fromDTO(dto);
        if (!car.isAvailableForTestDrive()) {
            car.setAvailableForTestDrive(true);
        }
    }

    public void deleteTestDriveCar(UUID id) {
        Car car = carRepository.find(id);
        if (car == null) {
            throw new EntityNotFoundException("Error: Car not found.");
        }
        if (car.isAvailableForTestDrive()) {
            car.setAvailableForTestDrive(false);
        }
    }

    public List<Car> viewCarsForTestDrive() {
        return carRepository.findAll().stream().filter(car -> car.isAvailableForTestDrive()).toList();
    }

    public Car addCarToSystem(CarDTO dto) {
        Car car = fromDTO(dto);
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
