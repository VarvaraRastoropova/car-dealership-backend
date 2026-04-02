package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.BodyDetailEntity;
import ru.rastoropova.persistence_layer.mappers.BodyDetailEntityMapper;
import ru.rastoropova.persistence_layer.repositories.BodyDetailJpaRepository;
import ru.rastoropova.service_layer.details.BodyDetail;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.BodyDetailRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class BodyDetailRepositoryAdapter implements BodyDetailRepository {
    private BodyDetailJpaRepository jpaRepository;
    private BodyDetailEntityMapper mapper;

    public void save(BodyDetail detail) {
        jpaRepository.save(mapper.toEntity(detail));
    }

    public BodyDetail find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<BodyDetail> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public void update(BodyDetail detail) {
        BodyDetailEntity entity = jpaRepository.findById(detail.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: BodyDetail not found"));
        BodyDetailEntity updated = mapper.toEntity(detail);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        jpaRepository.save(updated);
    }

    public void delete(UUID id) {
        BodyDetailEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: BodyDetail not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
