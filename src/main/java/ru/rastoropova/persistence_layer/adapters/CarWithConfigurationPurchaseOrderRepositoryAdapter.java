package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.CarWithConfigurationPurchaseOrderEntity;
import ru.rastoropova.persistence_layer.mappers.CarWithConfigurationPurchaseOrderEntityMapper;
import ru.rastoropova.persistence_layer.repositories.CarWithConfigurationPurchaseOrderJpaRepository;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.orders.CarWithConfigurationPurchaseOrder;
import ru.rastoropova.service_layer.repositories.CarWithConfigurationPurchaseOrderRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CarWithConfigurationPurchaseOrderRepositoryAdapter implements CarWithConfigurationPurchaseOrderRepository {
    private CarWithConfigurationPurchaseOrderJpaRepository jpaRepository;
    private CarWithConfigurationPurchaseOrderEntityMapper mapper;

    public void save(CarWithConfigurationPurchaseOrder order) {
        jpaRepository.save(mapper.toEntity(order));
    }

    public CarWithConfigurationPurchaseOrder find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<CarWithConfigurationPurchaseOrder> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public void update(CarWithConfigurationPurchaseOrder order) {
        CarWithConfigurationPurchaseOrderEntity entity = jpaRepository.findById(order.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: CarWithConfigurationPurchaseOrder not found"));
        CarWithConfigurationPurchaseOrderEntity updated = mapper.toEntity(order);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        jpaRepository.save(updated);
    }

    public void delete(UUID id) {
        CarWithConfigurationPurchaseOrderEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: CarWithConfigurationPurchaseOrder not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
