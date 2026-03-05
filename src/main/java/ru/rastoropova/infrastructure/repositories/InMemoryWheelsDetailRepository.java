package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.business_logic.details.WheelsDetail;
import ru.rastoropova.business_logic.repositories.WheelsDetailRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryWheelsDetailRepository implements WheelsDetailRepository {
    private Map<Integer, WheelsDetail> storage = new HashMap<>();

    public void save(WheelsDetail detail) {
        storage.put(detail.getId(), detail);
    }

    public WheelsDetail find(int id) {
        return storage.get(id);
    }

    public List<WheelsDetail> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(WheelsDetail detail) {
        if (storage.containsKey(detail.getId())) {
            storage.put(detail.getId(), detail);
        }
    }

    public void delete(int id) { storage.remove(id); }
}
