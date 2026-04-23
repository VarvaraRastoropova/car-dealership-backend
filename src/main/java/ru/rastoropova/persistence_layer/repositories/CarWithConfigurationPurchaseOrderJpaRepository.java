package ru.rastoropova.persistence_layer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rastoropova.persistence_layer.entities.*;
import java.util.List;
import java.util.UUID;

public interface CarWithConfigurationPurchaseOrderJpaRepository extends JpaRepository<CarWithConfigurationPurchaseOrderEntity, UUID> {
    List<CarWithConfigurationPurchaseOrderEntity> findByCustomerId(UUID customerId);
}
