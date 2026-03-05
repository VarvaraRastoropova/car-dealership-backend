package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.business_logic.details.GearboxDetail;
import ru.rastoropova.business_logic.repositories.GearboxDetailRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryGearboxDetailRepository implements GearboxDetailRepository {
    private Map<Integer, GearboxDetail> storage = new HashMap<>();

    public void save(GearboxDetail detail) {
        storage.put(detail.getId(), detail);
    }

    public GearboxDetail find(int id) {
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

    public void delete(GearboxDetail detail) {
        storage.remove(detail.getId());
    }
}
