package ru.rastoropova.business_logic.repositories;

import ru.rastoropova.business_logic.details.BodyDetail;

import java.util.List;

public interface BodyDetailRepository {
    void save(BodyDetail detail);
    BodyDetail find(int id);
    List<BodyDetail> findAll();
    void update(BodyDetail detail);
    void delete(int id);
}
