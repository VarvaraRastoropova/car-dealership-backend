package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.WheelsDetailEntity;
import ru.rastoropova.persistence_layer.mappers.WheelsDetailEntityMapper;
import ru.rastoropova.persistence_layer.repositories.WheelsDetailJpaRepository;
import ru.rastoropova.service_layer.details.WheelsDetail;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.WheelsDetailRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class WheelsDetailRepositoryAdapter implements WheelsDetailRepository {
    private WheelsDetailJpaRepository jpaRepository;
    private WheelsDetailEntityMapper mapper;

    public WheelsDetail save(WheelsDetail detail) {
        WheelsDetailEntity saved = jpaRepository.save(mapper.toEntity(detail));
        return mapper.toDomain(saved);
    }

    public WheelsDetail find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<WheelsDetail> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public WheelsDetail update(WheelsDetail detail) {
        WheelsDetailEntity entity = jpaRepository.findById(detail.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: WheelsDetail not found"));
        WheelsDetailEntity updated = mapper.toEntity(detail);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        return mapper.toDomain(jpaRepository.save(updated));
    }

    public void delete(UUID id) {
        WheelsDetailEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: WheelsDetail not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
