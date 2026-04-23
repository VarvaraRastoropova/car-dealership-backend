package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.details.SteeringWheelDetail;
import java.util.List;
import java.util.UUID;

public interface SteeringWheelDetailRepository {
    SteeringWheelDetail save(SteeringWheelDetail detail);
    SteeringWheelDetail find(UUID id);
    List<SteeringWheelDetail> findAll();
    void update(SteeringWheelDetail detail);
    void delete(UUID id);
}
