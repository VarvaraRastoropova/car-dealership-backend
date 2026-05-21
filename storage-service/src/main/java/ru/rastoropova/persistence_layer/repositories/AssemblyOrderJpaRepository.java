package ru.rastoropova.persistence_layer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rastoropova.persistence_layer.entities.*;

import java.util.Optional;
import java.util.UUID;

public interface AssemblyOrderJpaRepository extends JpaRepository<AssemblyOrderEntity, UUID> {
    Optional<AssemblyOrderEntity> findBySourceOrderId(UUID sourceOrderId);
}
