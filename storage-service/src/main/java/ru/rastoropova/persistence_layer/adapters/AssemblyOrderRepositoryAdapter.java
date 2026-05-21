package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.AssemblyOrderEntity;
import ru.rastoropova.persistence_layer.mappers.AssemblyOrderEntityMapper;
import ru.rastoropova.persistence_layer.repositories.AssemblyOrderJpaRepository;
import ru.rastoropova.service_layer.assembly.AssemblyOrder;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.AssemblyOrderRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class AssemblyOrderRepositoryAdapter implements AssemblyOrderRepository {
    private AssemblyOrderJpaRepository jpaRepository;
    private AssemblyOrderEntityMapper mapper;

    public AssemblyOrder save(AssemblyOrder order) {
        AssemblyOrderEntity saved = jpaRepository.save(mapper.toEntity(order));
        return mapper.toDomain(saved);
    }

    public AssemblyOrder find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public AssemblyOrder findBySourceOrderId(UUID sourceOrderId) {
        return jpaRepository.findBySourceOrderId(sourceOrderId)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<AssemblyOrder> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public AssemblyOrder update(AssemblyOrder order) {
        AssemblyOrderEntity entity = jpaRepository.findById(order.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: CarModel not found"));
        AssemblyOrderEntity updated = mapper.toEntity(order);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        return mapper.toDomain(jpaRepository.save(updated));
    }

    public void delete(UUID id) {
        AssemblyOrderEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: CarModel not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
