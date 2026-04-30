package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.service_layer.cars.Car;
import ru.rastoropova.service_layer.cars.CarFilter;
import ru.rastoropova.service_layer.repositories.CarRepository;
import java.util.*;

public class InMemoryCarRepository implements CarRepository {
    private Map<UUID, Car> storage = new HashMap<>();

    public Car save(Car car) {
        storage.put(car.getId(), car);
        return car;
    }

    public Car find(UUID id) { return storage.get(id); }

    public List<Car> findAll() { return new ArrayList<>(storage.values()); }

    public List<Car> findByFilter(CarFilter filter) {
        return storage.values().stream()
                .filter(car ->
                        filter.getPrice() == 0 || car.getPrice() == filter.getPrice())
                .filter(car ->
                        filter.getBrand() == null || car.getModel().getBrand() == filter.getBrand())
                .filter(car ->
                        filter.getBodyId() == null || car.getModel().getBaseConfiguration().getBody().getId() == filter.getBodyId())
                .filter(car ->
                        filter.getEngineId() == null || car.getModel().getBaseConfiguration().getEngine().getId() == filter.getEngineId())
                .filter(car ->
                        filter.getGearboxId() == null || car.getModel().getBaseConfiguration().getGearbox().getId() == filter.getGearboxId())
                .filter(car ->
                        filter.getInteriorId() == null || car.getModel().getBaseConfiguration().getInterior().getId() == filter.getInteriorId())
                .filter(car ->
                        filter.getSteeringWheelId() == null || car.getModel().getBaseConfiguration().getSteeringWheel().getId() == filter.getSteeringWheelId())
                .filter(car ->
                        filter.getWheelsId() == null || car.getModel().getBaseConfiguration().getWheels().getId() == filter.getWheelsId()).toList();
    }

    public Car update(Car car) {
        if (storage.containsKey(car.getId())) {
            storage.put(car.getId(), car);
        }
        return car;
    }

    public void delete(UUID id) { storage.remove(id); }
}
