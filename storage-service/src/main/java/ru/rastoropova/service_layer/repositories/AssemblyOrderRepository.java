package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.assembly.AssemblyOrder;
import java.util.List;
import java.util.UUID;

public interface AssemblyOrderRepository {
    AssemblyOrder save(AssemblyOrder order);
    AssemblyOrder find(UUID id);
    AssemblyOrder findBySourceOrderId(UUID sourceOrderId);
    List<AssemblyOrder> findAll();
    AssemblyOrder update(AssemblyOrder order);
    void delete(UUID id);
}
