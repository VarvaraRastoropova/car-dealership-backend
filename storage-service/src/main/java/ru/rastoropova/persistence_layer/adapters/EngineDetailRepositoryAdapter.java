package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.EngineDetailEntity;
import ru.rastoropova.persistence_layer.mappers.EngineDetailEntityMapper;
import ru.rastoropova.persistence_layer.repositories.EngineDetailJpaRepository;
import ru.rastoropova.service_layer.details.EngineDetail;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.EngineDetailRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class EngineDetailRepositoryAdapter implements EngineDetailRepository {
    private EngineDetailJpaRepository jpaRepository;
    private EngineDetailEntityMapper mapper;

    public EngineDetail save(EngineDetail detail) {
        EngineDetailEntity saved = jpaRepository.save(mapper.toEntity(detail));
        return mapper.toDomain(saved);
    }

    public EngineDetail find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<EngineDetail> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public EngineDetail update(EngineDetail detail) {
        EngineDetailEntity entity = jpaRepository.findById(detail.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: EngineDetail not found"));
        EngineDetailEntity updated = mapper.toEntity(detail);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        return mapper.toDomain(jpaRepository.save(updated));
    }

    public void delete(UUID id) {
        EngineDetailEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: EngineDetail not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
