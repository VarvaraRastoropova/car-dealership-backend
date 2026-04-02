package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.InteriorDetailEntity;
import ru.rastoropova.service_layer.details.InteriorDetail;

@Mapper(componentModel = "spring")
public interface InteriorDetailEntityMapper {
    InteriorDetailEntity toEntity(InteriorDetail domain);
    InteriorDetail toDomain(InteriorDetailEntity entity);
}
