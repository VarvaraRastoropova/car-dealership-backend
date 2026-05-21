package ru.rastoropova.persistence_layer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rastoropova.persistence_layer.entities.*;
import java.util.UUID;

public interface GearboxDetailJpaRepository extends JpaRepository<GearboxDetailEntity, UUID> {}
