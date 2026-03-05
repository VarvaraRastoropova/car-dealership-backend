package ru.rastoropova.business_logic.repositories;

import ru.rastoropova.business_logic.orders.CarWithConfigurationPurchaseOrder;

import java.util.List;

public interface CarWithConfigurationPurchaseOrderRepository {
    void save(CarWithConfigurationPurchaseOrder order);
    CarWithConfigurationPurchaseOrder find(int id);
    List<CarWithConfigurationPurchaseOrder> findAll();
    void update(CarWithConfigurationPurchaseOrder order);
    void delete(int id);
}
