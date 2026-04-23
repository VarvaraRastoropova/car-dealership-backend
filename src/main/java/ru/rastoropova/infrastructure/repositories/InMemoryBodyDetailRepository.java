package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.service_layer.details.BodyDetail;
import ru.rastoropova.service_layer.repositories.BodyDetailRepository;

import java.util.*;

public class InMemoryBodyDetailRepository implements BodyDetailRepository {
    private Map<UUID, BodyDetail> storage = new HashMap<>();

    public BodyDetail save(BodyDetail detail) {
        storage.put(detail.getId(), detail);
        return detail;
    }

    public BodyDetail find(UUID id) { return storage.get(id); }

    public List<BodyDetail> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(BodyDetail detail) {
        if (storage.containsKey(detail.getId())) {
            storage.put(detail.getId(), detail);
        }
    }

    public void delete(UUID id) { storage.remove(id);}
}
