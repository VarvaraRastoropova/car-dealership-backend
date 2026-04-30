package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.service_layer.details.WheelsDetail;
import ru.rastoropova.service_layer.repositories.WheelsDetailRepository;

import java.util.*;

public class InMemoryWheelsDetailRepository implements WheelsDetailRepository {
    private Map<UUID, WheelsDetail> storage = new HashMap<>();

    public WheelsDetail save(WheelsDetail detail) {
        storage.put(detail.getId(), detail);
        return detail;
    }

    public WheelsDetail find(UUID id) {
        return storage.get(id);
    }

    public List<WheelsDetail> findAll() {
        return new ArrayList<>(storage.values());
    }

    public WheelsDetail update(WheelsDetail detail) {
        if (storage.containsKey(detail.getId())) {
            storage.put(detail.getId(), detail);
        }
        return detail;
    }

    public void delete(UUID id) { storage.remove(id); }
}
