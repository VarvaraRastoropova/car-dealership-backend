package ru.rastoropova.infrastructure.repositories;

import ru.rastoropova.business_logic.orders.CarPurchaseOrder;
import ru.rastoropova.business_logic.repositories.CarPurchaseOrderRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCarPurchaseOrderRepository implements CarPurchaseOrderRepository {
    private Map<Integer, CarPurchaseOrder> storage = new HashMap<>();

    public void save(CarPurchaseOrder order) {
        storage.put(order.getId(), order);
    }

    public CarPurchaseOrder find(int id) {
        return storage.get(id);
    }

    public List<CarPurchaseOrder> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(CarPurchaseOrder order) {
        if (storage.containsKey(order.getId())) {
            storage.put(order.getId(), order);
        }
    }

    public void delete(CarPurchaseOrder order) {
        storage.remove(order.getId());
    }
}
