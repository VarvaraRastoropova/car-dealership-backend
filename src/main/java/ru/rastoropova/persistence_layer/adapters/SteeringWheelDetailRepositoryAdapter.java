package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.SteeringWheelDetailEntity;
import ru.rastoropova.persistence_layer.mappers.SteeringWheelDetailEntityMapper;
import ru.rastoropova.persistence_layer.repositories.SteeringWheelDetailJpaRepository;
import ru.rastoropova.service_layer.details.SteeringWheelDetail;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.SteeringWheelDetailRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class SteeringWheelDetailRepositoryAdapter implements SteeringWheelDetailRepository {
    private SteeringWheelDetailJpaRepository jpaRepository;
    private SteeringWheelDetailEntityMapper mapper;

    public void save(SteeringWheelDetail detail) {
        jpaRepository.save(mapper.toEntity(detail));
    }

    public SteeringWheelDetail find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<SteeringWheelDetail> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public void update(SteeringWheelDetail detail) {
        SteeringWheelDetailEntity entity = jpaRepository.findById(detail.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: SteeringWheelDetail not found"));
        SteeringWheelDetailEntity updated = mapper.toEntity(detail);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        jpaRepository.save(updated);
    }

    public void delete(UUID id) {
        SteeringWheelDetailEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: SteeringWheelDetail not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
