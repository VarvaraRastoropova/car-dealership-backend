package ru.rastoropova.persistence_layer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rastoropova.persistence_layer.entities.*;
import java.util.List;
import java.util.UUID;

public interface TestDriveOrderJpaRepository extends JpaRepository<TestDriveOrderEntity, UUID> {
    List<TestDriveOrderEntity> findByCustomerId(UUID userId);
}
