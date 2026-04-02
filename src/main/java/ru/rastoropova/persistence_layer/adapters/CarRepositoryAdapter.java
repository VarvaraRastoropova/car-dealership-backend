package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.CarEntity;
import ru.rastoropova.persistence_layer.mappers.CarEntityMapper;
import ru.rastoropova.persistence_layer.repositories.CarJpaRepository;
import ru.rastoropova.persistence_layer.specifications.CarSpecifications;
import ru.rastoropova.service_layer.cars.Car;
import ru.rastoropova.service_layer.cars.CarFilter;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.CarRepository;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CarRepositoryAdapter implements CarRepository {
    private CarJpaRepository jpaRepository;
    private CarEntityMapper mapper;
    private CarSpecifications carSpecifications;

    public void save(Car car) {
        jpaRepository.save(mapper.toEntity(car));
    }

    public Car find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<Car> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public List<Car> findByFilter(CarFilter filter) {
        Specification<CarEntity> specification = carSpecifications.withFilter(filter);
        List<Car> cars = jpaRepository.findAll(specification).stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
        return cars;
    }

    public void update(Car car) {
        CarEntity entity = jpaRepository.findById(car.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: Car not found"));
        CarEntity updated = mapper.toEntity(car);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        jpaRepository.save(updated);
    }

    public void delete(UUID id) {
        CarEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: Car not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
