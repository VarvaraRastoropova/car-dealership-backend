package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.orders.TestDriveOrder;
import java.util.List;
import java.util.UUID;

public interface TestDriveOrderRepository {
    TestDriveOrder save(TestDriveOrder order);
    TestDriveOrder find(UUID id);
    List<TestDriveOrder> findAll();
    List<TestDriveOrder> findByUserId(UUID userId);
    TestDriveOrder update(TestDriveOrder order);
    void delete(UUID id);
}
