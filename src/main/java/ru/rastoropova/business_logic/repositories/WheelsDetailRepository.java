package ru.rastoropova.business_logic.repositories;

import ru.rastoropova.business_logic.details.WheelsDetail;

import java.util.List;

public interface WheelsDetailRepository {
    void save(WheelsDetail detail);
    WheelsDetail find(int id);
    List<WheelsDetail> findAll();
    void update(WheelsDetail detail);
    void delete(WheelsDetail detail);
}
