package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.CarModelEntity;
import ru.rastoropova.persistence_layer.mappers.CarModelEntityMapper;
import ru.rastoropova.persistence_layer.repositories.CarModelJpaRepository;
import ru.rastoropova.service_layer.car_characteristics.CarModel;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.CarModelRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CarModelRepositoryAdapter implements CarModelRepository {
    private CarModelJpaRepository jpaRepository;
    private CarModelEntityMapper mapper;

    public CarModel save(CarModel model) {
        CarModelEntity saved = jpaRepository.save(mapper.toEntity(model));
        return mapper.toDomain(saved);
    }

    public CarModel find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<CarModel> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public CarModel update(CarModel model) {
        CarModelEntity entity = jpaRepository.findById(model.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: CarModel not found"));
        CarModelEntity updated = mapper.toEntity(model);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        return mapper.toDomain(jpaRepository.save(updated));
    }

    public void delete(UUID id) {
        CarModelEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: CarModel not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
