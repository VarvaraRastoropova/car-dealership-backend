package ru.rastoropova.business_logic.repositories;

import ru.rastoropova.business_logic.details.Detail;

import java.util.List;

public interface DetailRepository {
    void save(Detail detail);
    Detail find(int id);
    List<Detail> findAll();
    void update(Detail detail);
    void delete(Detail detail);
}
