package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.details.WheelsDetail;
import java.util.List;
import java.util.UUID;

public interface WheelsDetailRepository {
    WheelsDetail save(WheelsDetail detail);
    WheelsDetail find(UUID id);
    List<WheelsDetail> findAll();
    WheelsDetail update(WheelsDetail detail);
    void delete(UUID id);
}
