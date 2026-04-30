package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rastoropova.persistence_layer.entities.CarEntity;
import ru.rastoropova.persistence_layer.entities.CarModelEntity;
import ru.rastoropova.service_layer.cars.Car;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CarEntityMapper {
    @Mapping(target = "model", source = "model.id")
    CarEntity toEntity(Car domain);

    @Mapping(target = "model", source = "model")
    Car toDomain(CarEntity entity);

    default CarModelEntity map(UUID id) {
        if (id == null) {
            return null;
        }
        CarModelEntity model = new CarModelEntity();
        model.setId(id);
        return model;
    }
}
