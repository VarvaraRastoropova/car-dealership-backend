package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.business_logic.orders.TestDriveOrder;
import ru.rastoropova.business_logic.repositories.TestDriveOrderRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTestDriveOrderRepository implements TestDriveOrderRepository {
    private Map<Integer, TestDriveOrder> storage = new HashMap<>();

    public void save(TestDriveOrder order) {
        storage.put(order.getId(), order);
    }

    public TestDriveOrder find(int id) {
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

    public void delete(int id) { storage.remove(id); }
}
