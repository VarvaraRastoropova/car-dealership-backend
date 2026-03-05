package ru.rastoropova.business_logic.repositories;

import ru.rastoropova.business_logic.details.InteriorDetail;

import java.util.List;

public interface InteriorDetailRepository {
    void save(InteriorDetail detail);
    InteriorDetail find(int id);
    List<InteriorDetail> findAll();
    void update(InteriorDetail detail);
    void delete(InteriorDetail detail);
}
