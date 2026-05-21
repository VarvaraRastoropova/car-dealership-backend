package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.DetailStockEntity;
import ru.rastoropova.persistence_layer.mappers.DetailStockEntityMapper;
import ru.rastoropova.persistence_layer.repositories.DetailStockJpaRepository;
import ru.rastoropova.service_layer.details.DetailStock;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.DetailStockRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class DetailStockRepositoryAdapter implements DetailStockRepository {
    private DetailStockJpaRepository jpaRepository;
    private DetailStockEntityMapper mapper;

    public DetailStock save(DetailStock detail) {
        DetailStockEntity saved = jpaRepository.save(mapper.toEntity(detail));
        return mapper.toDomain(saved);
    }

    public DetailStock find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public DetailStock findByDetailId(UUID detailId) {
        return jpaRepository.findByDetailId(detailId)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<DetailStock> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public DetailStock update(DetailStock detail) {
        DetailStockEntity entity = jpaRepository.findById(detail.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: BodyDetail not found"));
        DetailStockEntity updated = mapper.toEntity(detail);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        return mapper.toDomain(jpaRepository.save(updated));
    }

    public void delete(UUID id) {
        DetailStockEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: BodyDetail not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
