package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.TestDriveOrderEntity;
import ru.rastoropova.persistence_layer.mappers.TestDriveOrderEntityMapper;
import ru.rastoropova.persistence_layer.repositories.TestDriveOrderJpaRepository;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.orders.TestDriveOrder;
import ru.rastoropova.service_layer.repositories.TestDriveOrderRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class TestDriveOrderRepositoryAdapter implements TestDriveOrderRepository {
    private TestDriveOrderJpaRepository jpaRepository;
    private TestDriveOrderEntityMapper mapper;

    public void save(TestDriveOrder order) {
        jpaRepository.save(mapper.toEntity(order));
    }

    public TestDriveOrder find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<TestDriveOrder> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public void update(TestDriveOrder order) {
        TestDriveOrderEntity entity = jpaRepository.findById(order.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: TestDriveOrder not found"));
        TestDriveOrderEntity updated = mapper.toEntity(order);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        jpaRepository.save(updated);
    }

    public void delete(UUID id) {
        TestDriveOrderEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: TestDriveOrder not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
