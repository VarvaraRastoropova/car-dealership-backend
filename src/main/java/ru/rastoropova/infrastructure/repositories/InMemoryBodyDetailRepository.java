package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.business_logic.details.BodyDetail;
import ru.rastoropova.business_logic.repositories.BodyDetailRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBodyDetailRepository implements BodyDetailRepository {
    private Map<Integer, BodyDetail> storage = new HashMap<>();

    public void save(BodyDetail detail) {
        storage.put(detail.getId(), detail);
    }

    public BodyDetail find(int id) {
        return storage.get(id);
    }

    public List<BodyDetail> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(BodyDetail detail) {
        if (storage.containsKey(detail.getId())) {
            storage.put(detail.getId(), detail);
        }
    }

    public void delete(BodyDetail detail) {
        storage.remove(detail.getId());
    }
}
