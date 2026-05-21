package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.orders.CarPurchaseOrder;
import java.util.List;
import java.util.UUID;

public interface CarPurchaseOrderRepository {
    CarPurchaseOrder save(CarPurchaseOrder order);
    CarPurchaseOrder find(UUID id);
    List<CarPurchaseOrder> findAll();
    List<CarPurchaseOrder> findByUserId(UUID userId);
    CarPurchaseOrder update(CarPurchaseOrder order);
    void delete(UUID id);
}
