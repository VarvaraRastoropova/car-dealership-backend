package ru.rastoropova.business_logic.repositories;

import ru.rastoropova.business_logic.cars.Car;

import java.util.List;

public interface CarRepository {
    void save(Car car);
    Car find(int id);
    List<Car> findAll();
    void update(Car car);
    void delete(int id);
}
