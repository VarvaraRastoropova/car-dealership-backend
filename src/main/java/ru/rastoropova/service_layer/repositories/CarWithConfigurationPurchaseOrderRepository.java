package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.orders.CarWithConfigurationPurchaseOrder;
import java.util.List;
import java.util.UUID;

public interface CarWithConfigurationPurchaseOrderRepository {
    void save(CarWithConfigurationPurchaseOrder order);
    CarWithConfigurationPurchaseOrder find(UUID id);
    List<CarWithConfigurationPurchaseOrder> findAll();
    void update(CarWithConfigurationPurchaseOrder order);
    void delete(UUID id);
}
