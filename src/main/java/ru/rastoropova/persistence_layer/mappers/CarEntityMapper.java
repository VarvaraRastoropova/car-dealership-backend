package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.CarEntity;
import ru.rastoropova.service_layer.cars.Car;

@Mapper(componentModel = "spring")
public interface CarEntityMapper {
    CarEntity toEntity(Car domain);
    Car toDomain(CarEntity entity);
}
