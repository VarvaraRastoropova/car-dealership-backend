package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.details.GearboxDetail;
import java.util.List;
import java.util.UUID;

public interface GearboxDetailRepository {
    void save(GearboxDetail detail);
    GearboxDetail find(UUID id);
    List<GearboxDetail> findAll();
    void update(GearboxDetail detail);
    void delete(UUID id);
}
