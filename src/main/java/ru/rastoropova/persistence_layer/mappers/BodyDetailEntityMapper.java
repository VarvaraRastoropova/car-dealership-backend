package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.BodyDetailEntity;
import ru.rastoropova.service_layer.details.BodyDetail;

@Mapper(componentModel = "spring")
public interface BodyDetailEntityMapper {
    BodyDetailEntity toEntity(BodyDetail domain);
    BodyDetail toDomain(BodyDetailEntity entity);
}
