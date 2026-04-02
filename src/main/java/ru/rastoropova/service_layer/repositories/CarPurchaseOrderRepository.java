package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.orders.CarPurchaseOrder;
import java.util.List;
import java.util.UUID;

public interface CarPurchaseOrderRepository {
    void save(CarPurchaseOrder order);
    CarPurchaseOrder find(UUID id);
    List<CarPurchaseOrder> findAll();
    void update(CarPurchaseOrder order);
    void delete(UUID id);
}
