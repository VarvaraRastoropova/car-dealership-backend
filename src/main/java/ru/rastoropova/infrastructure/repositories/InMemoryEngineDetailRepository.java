package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.business_logic.details.EngineDetail;
import ru.rastoropova.business_logic.repositories.EngineDetailRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryEngineDetailRepository implements EngineDetailRepository {
    private Map<Integer, EngineDetail> storage = new HashMap<>();

    public void save(EngineDetail detail) {
        storage.put(detail.getId(), detail);
    }

    public EngineDetail find(int id) {
        return storage.get(id);
    }

    public List<EngineDetail> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(EngineDetail detail) {
        if (storage.containsKey(detail.getId())) {
            storage.put(detail.getId(), detail);
        }
    }

    public void delete(EngineDetail detail) {
        storage.remove(detail.getId());
    }
}
