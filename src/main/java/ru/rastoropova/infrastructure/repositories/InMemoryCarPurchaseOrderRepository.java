package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.service_layer.orders.CarPurchaseOrder;
import ru.rastoropova.service_layer.repositories.CarPurchaseOrderRepository;

import java.util.*;

public class InMemoryCarPurchaseOrderRepository implements CarPurchaseOrderRepository {
    private Map<UUID, CarPurchaseOrder> storage = new HashMap<>();

    public CarPurchaseOrder save(CarPurchaseOrder order) {
        storage.put(order.getId(), order);
        return order;
    }

    public CarPurchaseOrder find(UUID id) { return storage.get(id); }

    public List<CarPurchaseOrder> findAll() { return new ArrayList<>(storage.values()); }

    public List<CarPurchaseOrder> findByUserId(UUID userId) {
        return storage.values().stream().filter(order -> order.getCustomerId().equals(userId)).toList();
    }

    public CarPurchaseOrder update(CarPurchaseOrder order) {
        if (storage.containsKey(order.getId())) {
            storage.put(order.getId(), order);
        }
        return order;
    }

    public void delete(UUID id) { storage.remove(id); }
}
