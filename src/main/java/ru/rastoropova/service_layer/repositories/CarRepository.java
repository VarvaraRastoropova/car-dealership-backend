package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.cars.Car;
import ru.rastoropova.service_layer.cars.CarFilter;
import java.util.List;
import java.util.UUID;

public interface CarRepository {
    Car save(Car car);
    Car find(UUID id);
    List<Car> findAll();
    List<Car> findByFilter(CarFilter filter);
    void update(Car car);
    void delete(UUID id);
}
