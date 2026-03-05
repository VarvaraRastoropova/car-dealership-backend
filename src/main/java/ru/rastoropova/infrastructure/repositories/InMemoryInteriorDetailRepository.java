package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.business_logic.details.InteriorDetail;
import ru.rastoropova.business_logic.repositories.InteriorDetailRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryInteriorDetailRepository implements InteriorDetailRepository {
    private Map<Integer, InteriorDetail> storage = new HashMap<>();

    public void save(InteriorDetail detail) {
        storage.put(detail.getId(), detail);
    }

    public InteriorDetail find(int id) {
        return storage.get(id);
    }

    public List<InteriorDetail> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(InteriorDetail detail) {
        if (storage.containsKey(detail.getId())) {
            storage.put(detail.getId(), detail);
        }
    }

    public void delete(InteriorDetail detail) {
        storage.remove(detail.getId());
    }
}
