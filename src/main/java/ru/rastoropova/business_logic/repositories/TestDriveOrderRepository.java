package ru.rastoropova.business_logic.repositories;

import ru.rastoropova.business_logic.orders.TestDriveOrder;

import java.util.List;

public interface TestDriveOrderRepository {
    void save(TestDriveOrder order);
    TestDriveOrder find(int id);
    List<TestDriveOrder> findAll();
    void update(TestDriveOrder order);
    void delete(TestDriveOrder order);
}
