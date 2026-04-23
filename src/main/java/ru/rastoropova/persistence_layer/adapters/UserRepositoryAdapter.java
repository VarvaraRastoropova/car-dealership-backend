package ru.rastoropova.persistence_layer.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.rastoropova.persistence_layer.entities.UserEntity;
import ru.rastoropova.persistence_layer.mappers.UserEntityMapper;
import ru.rastoropova.persistence_layer.repositories.UserJpaRepository;
import ru.rastoropova.service_layer.exceptions.EntityNotFoundException;
import ru.rastoropova.service_layer.repositories.UserRepository;
import ru.rastoropova.service_layer.users.Role;
import ru.rastoropova.service_layer.users.User;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private UserJpaRepository jpaRepository;
    private UserEntityMapper mapper;

    public User save(User model) {
        UserEntity saved = jpaRepository.save(mapper.toEntity(model));
        return mapper.toDomain(saved);
    }

    public User find(UUID id) {
        return jpaRepository.findById(id)
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .orElse(null);
    }

    public List<User> findAll() {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public List<User> findAllByRole(Role role) {
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isRemoved())
                .filter(entity -> entity.getRole() == role)
                .map(entity -> mapper.toDomain(entity))
                .toList();
    }

    public void update(User model) {
        UserEntity entity = jpaRepository.findById(model.getId())
                .orElseThrow(() -> new EntityNotFoundException("Error: User not found"));
        UserEntity updated = mapper.toEntity(model);
        updated.setUpdatedAt(entity.getUpdatedAt());
        updated.setCreatedAt(entity.getCreatedAt());
        updated.setRemoved(entity.isRemoved());
        updated.setId(entity.getId());
        jpaRepository.save(updated);
    }

    public void delete(UUID id) {
        UserEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error: User not found"));
        entity.setRemoved(true);
        jpaRepository.save(entity);
    }
}
