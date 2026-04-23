package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.CarPurchaseOrderEntity;
import ru.rastoropova.persistence_layer.mappers.CarPurchaseOrderEntityMapper;
import ru.rastoropova.persistence_layer.repositories.CarPurchaseOrderJpaRepository;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.orders.CarPurchaseOrder;
import ru.rastoropova.service_layer.repositories.CarPurchaseOrderRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CarPurchaseOrderRepositoryAdapter implements CarPurchaseOrderRepository {
    private CarPurchaseOrderJpaRepository jpaRepository;
    private CarPurchaseOrderEntityMapper mapper;

    public CarPurchaseOrder save(CarPurchaseOrder order) {
        CarPurchaseOrderEntity saved = jpaRepository.save(mapper.toEntity(order));
        return mapper.toDomain(saved);
    }

    public CarPurchaseOrder find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<CarPurchaseOrder> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public List<CarPurchaseOrder> findByUserId(UUID userId) {
        return jpaRepository.findByCustomerId(userId).stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public void update(CarPurchaseOrder order) {
        CarPurchaseOrderEntity entity = jpaRepository.findById(order.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: CarPurchaseOrder not found"));
        CarPurchaseOrderEntity updated = mapper.toEntity(order);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        jpaRepository.save(updated);
    }

    public void delete(UUID id) {
        CarPurchaseOrderEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: CarPurchaseOrder not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
