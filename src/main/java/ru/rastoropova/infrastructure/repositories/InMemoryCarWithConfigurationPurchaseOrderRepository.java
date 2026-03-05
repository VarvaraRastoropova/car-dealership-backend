package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.business_logic.orders.CarWithConfigurationPurchaseOrder;
import ru.rastoropova.business_logic.repositories.CarWithConfigurationPurchaseOrderRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCarWithConfigurationPurchaseOrderRepository implements CarWithConfigurationPurchaseOrderRepository {
    private Map<Integer, CarWithConfigurationPurchaseOrder> storage = new HashMap<>();

    public void save(CarWithConfigurationPurchaseOrder order) {
        storage.put(order.getId(), order);
    }

    public CarWithConfigurationPurchaseOrder find(int id) {
        return storage.get(id);
    }

    public List<CarWithConfigurationPurchaseOrder> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(CarWithConfigurationPurchaseOrder order) {
        if (storage.containsKey(order.getId())) {
            storage.put(order.getId(), order);
        }
    }

    public void delete(int id) { storage.remove(id); }
}
