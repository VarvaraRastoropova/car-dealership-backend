package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.service_layer.details.EngineDetail;
import ru.rastoropova.service_layer.repositories.EngineDetailRepository;

import java.util.*;

public class InMemoryEngineDetailRepository implements EngineDetailRepository {
    private Map<UUID, EngineDetail> storage = new HashMap<>();

    public EngineDetail save(EngineDetail detail) {
        storage.put(detail.getId(), detail);
        return detail;
    }

    public EngineDetail find(UUID id) {
        return storage.get(id);
    }

    public List<EngineDetail> findAll() {
        return new ArrayList<>(storage.values());
    }

    public EngineDetail update(EngineDetail detail) {
        if (storage.containsKey(detail.getId())) {
            storage.put(detail.getId(), detail);
        }
        return detail;
    }

    public void delete(UUID id) { storage.remove(id); }
}
