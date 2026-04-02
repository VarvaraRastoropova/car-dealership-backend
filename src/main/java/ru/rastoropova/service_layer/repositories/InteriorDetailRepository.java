package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.details.InteriorDetail;
import java.util.List;
import java.util.UUID;

public interface InteriorDetailRepository {
    void save(InteriorDetail detail);
    InteriorDetail find(UUID id);
    List<InteriorDetail> findAll();
    void update(InteriorDetail detail);
    void delete(UUID id);
}
