package ru.rastoropova.messaging;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface OutboxJpaRepository extends JpaRepository<OutboxEntity, UUID> {
    List<OutboxEntity> findByProcessedFalse();
}
