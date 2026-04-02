package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.service_layer.orders.CarWithConfigurationPurchaseOrder;
import ru.rastoropova.service_layer.repositories.CarWithConfigurationPurchaseOrderRepository;

import java.util.*;

public class InMemoryCarWithConfigurationPurchaseOrderRepository implements CarWithConfigurationPurchaseOrderRepository {
    private Map<UUID, CarWithConfigurationPurchaseOrder> storage = new HashMap<>();

    public void save(CarWithConfigurationPurchaseOrder order) {
        storage.put(order.getId(), order);
    }

    public CarWithConfigurationPurchaseOrder find(UUID id) {
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

    public void delete(UUID id) { storage.remove(id); }
}
