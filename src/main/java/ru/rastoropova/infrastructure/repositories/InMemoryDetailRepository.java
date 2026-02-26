package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.business_logic.details.Detail;
import ru.rastoropova.business_logic.repositories.DetailRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDetailRepository implements DetailRepository {
    private Map<Integer, Detail> storage = new HashMap<>();

    public void save(Detail detail) {
        storage.put(detail.getId(), detail);
    }

    public Detail find(int id) {
        return storage.get(id);
    }

    public List<Detail> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(Detail detail) {
        if (storage.containsKey(detail.getId())) {
            storage.put(detail.getId(), detail);
        }
    }

    public void delete(Detail detail) {
        storage.remove(detail.getId());
    }
}
