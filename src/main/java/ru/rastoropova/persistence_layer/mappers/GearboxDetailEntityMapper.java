package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.GearboxDetailEntity;
import ru.rastoropova.service_layer.details.GearboxDetail;

@Mapper(componentModel = "spring")
public interface GearboxDetailEntityMapper {
    GearboxDetailEntity toEntity(GearboxDetail domain);
    GearboxDetail toDomain(GearboxDetailEntity entity);
}
