package ru.rastoropova.business_logic.repositories;

import ru.rastoropova.business_logic.orders.CarPurchaseOrder;

import java.util.List;

public interface CarPurchaseOrderRepository {
    void save(CarPurchaseOrder order);
    CarPurchaseOrder find(int id);
    List<CarPurchaseOrder> findAll();
    void update(CarPurchaseOrder order);
    void delete(int id);
}
