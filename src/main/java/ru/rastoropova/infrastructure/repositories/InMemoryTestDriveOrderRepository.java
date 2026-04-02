package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.service_layer.orders.TestDriveOrder;
import ru.rastoropova.service_layer.repositories.TestDriveOrderRepository;

import java.util.*;

public class InMemoryTestDriveOrderRepository implements TestDriveOrderRepository {
    private Map<UUID, TestDriveOrder> storage = new HashMap<>();

    public void save(TestDriveOrder order) {
        storage.put(order.getId(), order);
    }

    public TestDriveOrder find(UUID id) {
        return storage.get(id);
    }

    public List<TestDriveOrder> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(TestDriveOrder order) {
        if (storage.containsKey(order.getId())) {
            storage.put(order.getId(), order);
        }
    }

    public void delete(UUID id) { storage.remove(id); }
}
