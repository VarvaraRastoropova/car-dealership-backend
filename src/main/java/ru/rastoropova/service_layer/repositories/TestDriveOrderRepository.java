package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.orders.TestDriveOrder;
import java.util.List;
import java.util.UUID;

public interface TestDriveOrderRepository {
    void save(TestDriveOrder order);
    TestDriveOrder find(UUID id);
    List<TestDriveOrder> findAll();
    void update(TestDriveOrder order);
    void delete(UUID id);
}
