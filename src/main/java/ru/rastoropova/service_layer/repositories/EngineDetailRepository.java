package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.details.EngineDetail;
import java.util.List;
import java.util.UUID;

public interface EngineDetailRepository {
    EngineDetail save(EngineDetail detail);
    EngineDetail find(UUID id);
    List<EngineDetail> findAll();
    void update(EngineDetail detail);
    void delete(UUID id);
}
