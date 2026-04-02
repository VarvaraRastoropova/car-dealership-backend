package ru.rastoropova.persistence_layer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.rastoropova.persistence_layer.entities.*;
import java.util.UUID;

public interface CarJpaRepository extends JpaRepository<CarEntity, UUID>, JpaSpecificationExecutor<CarEntity> {}
