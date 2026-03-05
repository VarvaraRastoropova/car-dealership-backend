package ru.rastoropova.business_logic.repositories;

import ru.rastoropova.business_logic.details.EngineDetail;

import java.util.List;

public interface EngineDetailRepository {
    void save(EngineDetail detail);
    EngineDetail find(int id);
    List<EngineDetail> findAll();
    void update(EngineDetail detail);
    void delete(EngineDetail detail);
}
