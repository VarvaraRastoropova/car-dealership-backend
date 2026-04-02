package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.service_layer.details.GearboxDetail;
import ru.rastoropova.service_layer.repositories.GearboxDetailRepository;

import java.util.*;

public class InMemoryGearboxDetailRepository implements GearboxDetailRepository {
    private Map<UUID, GearboxDetail> storage = new HashMap<>();

    public void save(GearboxDetail detail) {
        storage.put(detail.getId(), detail);
    }

    public GearboxDetail find(UUID id) {
        return storage.get(id);
    }

    public List<GearboxDetail> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(GearboxDetail detail) {
        if (storage.containsKey(detail.getId())) {
            storage.put(detail.getId(), detail);
        }
    }

    public void delete(UUID id) { storage.remove(id); }
}
