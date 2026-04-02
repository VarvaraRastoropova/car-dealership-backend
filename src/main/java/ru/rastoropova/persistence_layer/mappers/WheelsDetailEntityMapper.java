package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.WheelsDetailEntity;
import ru.rastoropova.service_layer.details.WheelsDetail;

@Mapper(componentModel = "spring")
public interface WheelsDetailEntityMapper {
    WheelsDetailEntity toEntity(WheelsDetail domain);
    WheelsDetail toDomain(WheelsDetailEntity entity);
}
