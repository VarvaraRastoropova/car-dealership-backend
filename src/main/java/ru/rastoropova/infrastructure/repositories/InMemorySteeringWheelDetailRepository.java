package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.business_logic.details.SteeringWheelDetail;
import ru.rastoropova.business_logic.repositories.SteeringWheelDetailRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySteeringWheelDetailRepository implements SteeringWheelDetailRepository {
    private Map<Integer, SteeringWheelDetail> storage = new HashMap<>();

    public void save(SteeringWheelDetail detail) {
        storage.put(detail.getId(), detail);
    }

    public SteeringWheelDetail find(int id) {
        return storage.get(id);
    }

    public List<SteeringWheelDetail> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(SteeringWheelDetail detail) {
        if (storage.containsKey(detail.getId())) {
            storage.put(detail.getId(), detail);
        }
    }

    public void delete(int id) { storage.remove(id); }
}
