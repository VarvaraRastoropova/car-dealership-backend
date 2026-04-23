package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.details.BodyDetail;
import java.util.List;
import java.util.UUID;

public interface BodyDetailRepository {
    BodyDetail save(BodyDetail detail);
    BodyDetail find(UUID id);
    List<BodyDetail> findAll();
    void update(BodyDetail detail);
    void delete(UUID id);
}
