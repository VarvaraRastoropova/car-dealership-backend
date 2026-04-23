package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.service_layer.details.SteeringWheelDetail;
import ru.rastoropova.service_layer.repositories.SteeringWheelDetailRepository;

import java.util.*;

public class InMemorySteeringWheelDetailRepository implements SteeringWheelDetailRepository {
    private Map<UUID, SteeringWheelDetail> storage = new HashMap<>();

    public SteeringWheelDetail save(SteeringWheelDetail detail) {
        storage.put(detail.getId(), detail);
        return detail;
    }

    public SteeringWheelDetail find(UUID id) {
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

    public void delete(UUID id) { storage.remove(id); }
}
