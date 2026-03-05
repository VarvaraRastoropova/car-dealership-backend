package ru.rastoropova.business_logic.repositories;

import ru.rastoropova.business_logic.details.SteeringWheelDetail;

import java.util.List;

public interface SteeringWheelDetailRepository {
    void save(SteeringWheelDetail detail);
    SteeringWheelDetail find(int id);
    List<SteeringWheelDetail> findAll();
    void update(SteeringWheelDetail detail);
    void delete(int id);
}
