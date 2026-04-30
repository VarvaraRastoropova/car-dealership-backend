package ru.rastoropova.service_layer.repositories;

import ru.rastoropova.service_layer.car_characteristics.CarModel;
import java.util.List;
import java.util.UUID;

public interface CarModelRepository {
    CarModel save(CarModel model);
    CarModel find(UUID id);
    List<CarModel> findAll();
    CarModel update(CarModel model);
    void delete(UUID id);
}
