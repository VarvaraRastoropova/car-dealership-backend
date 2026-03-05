package ru.rastoropova.business_logic.repositories;

import ru.rastoropova.business_logic.details.GearboxDetail;

import java.util.List;

public interface GearboxDetailRepository {
    void save(GearboxDetail detail);
    GearboxDetail find(int id);
    List<GearboxDetail> findAll();
    void update(GearboxDetail detail);
    void delete(int id);
}
