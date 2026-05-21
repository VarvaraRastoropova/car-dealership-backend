package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.*;
import ru.rastoropova.service_layer.car_characteristics.CarModel;

@Mapper(componentModel = "spring")
public interface CarModelEntityMapper {
    CarModelEntity toEntity(CarModel domain);
    CarModel toDomain(CarModelEntity entity);
}
