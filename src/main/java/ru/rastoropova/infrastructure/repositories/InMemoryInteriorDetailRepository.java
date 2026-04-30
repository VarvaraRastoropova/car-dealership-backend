package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.service_layer.details.InteriorDetail;
import ru.rastoropova.service_layer.repositories.InteriorDetailRepository;

import java.util.*;

public class InMemoryInteriorDetailRepository implements InteriorDetailRepository {
    private Map<UUID, InteriorDetail> storage = new HashMap<>();

    public InteriorDetail save(InteriorDetail detail) {
        storage.put(detail.getId(), detail);
        return detail;
    }

    public InteriorDetail find(UUID id) {
        return storage.get(id);
    }

    public List<InteriorDetail> findAll() {
        return new ArrayList<>(storage.values());
    }

    public InteriorDetail update(InteriorDetail detail) {
        if (storage.containsKey(detail.getId())) {
            storage.put(detail.getId(), detail);
        }
        return detail;
    }

    public void delete(UUID id) { storage.remove(id); }
}
