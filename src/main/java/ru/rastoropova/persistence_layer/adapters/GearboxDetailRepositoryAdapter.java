package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.GearboxDetailEntity;
import ru.rastoropova.persistence_layer.mappers.GearboxDetailEntityMapper;
import ru.rastoropova.persistence_layer.repositories.GearboxDetailJpaRepository;
import ru.rastoropova.service_layer.details.GearboxDetail;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.GearboxDetailRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class GearboxDetailRepositoryAdapter implements GearboxDetailRepository {
    private GearboxDetailJpaRepository jpaRepository;
    private GearboxDetailEntityMapper mapper;

    public void save(GearboxDetail detail) {
        jpaRepository.save(mapper.toEntity(detail));
    }

    public GearboxDetail find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<GearboxDetail> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public void update(GearboxDetail detail) {
        GearboxDetailEntity entity = jpaRepository.findById(detail.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: GearboxDetail not found"));
        GearboxDetailEntity updated = mapper.toEntity(detail);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        jpaRepository.save(updated);
    }

    public void delete(UUID id) {
        GearboxDetailEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: GearboxDetail not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
