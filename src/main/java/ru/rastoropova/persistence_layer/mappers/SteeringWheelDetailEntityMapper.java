package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.SteeringWheelDetailEntity;
import ru.rastoropova.service_layer.details.SteeringWheelDetail;

@Mapper(componentModel = "spring")
public interface SteeringWheelDetailEntityMapper {
    SteeringWheelDetailEntity toEntity(SteeringWheelDetail domain);
    SteeringWheelDetail toDomain(SteeringWheelDetailEntity entity);
}
