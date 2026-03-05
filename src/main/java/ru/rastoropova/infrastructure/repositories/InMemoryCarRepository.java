package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.business_logic.cars.Car;
import ru.rastoropova.business_logic.repositories.CarRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCarRepository implements CarRepository {
    private Map<Integer, Car> storage = new HashMap<>();

    public void save(Car car) { storage.put(car.getId(), car); }

    public Car find(int id) { return storage.get(id); }

    public List<Car> findAll() { return new ArrayList<>(storage.values()); }

    public void update(Car car) {
        if (storage.containsKey(car.getId())) {
            storage.put(car.getId(), car);
        }
    }

    public void delete(int id) { storage.remove(id); }
}
