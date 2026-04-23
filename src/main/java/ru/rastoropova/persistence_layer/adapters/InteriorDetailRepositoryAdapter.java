package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.InteriorDetailEntity;
import ru.rastoropova.persistence_layer.mappers.InteriorDetailEntityMapper;
import ru.rastoropova.persistence_layer.repositories.InteriorDetailJpaRepository;
import ru.rastoropova.service_layer.details.InteriorDetail;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.InteriorDetailRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class InteriorDetailRepositoryAdapter implements InteriorDetailRepository {
    private InteriorDetailJpaRepository jpaRepository;
    private InteriorDetailEntityMapper mapper;

    public InteriorDetail save(InteriorDetail detail) {
        InteriorDetailEntity saved = jpaRepository.save(mapper.toEntity(detail));
        return mapper.toDomain(saved);
    }

    public InteriorDetail find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<InteriorDetail> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public void update(InteriorDetail detail) {
        InteriorDetailEntity entity = jpaRepository.findById(detail.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: InteriorDetail not found"));
        InteriorDetailEntity updated = mapper.toEntity(detail);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        jpaRepository.save(updated);
    }

    public void delete(UUID id) {
        InteriorDetailEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: InteriorDetail not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
