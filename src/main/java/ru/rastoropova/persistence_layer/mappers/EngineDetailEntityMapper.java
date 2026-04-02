package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.EngineDetailEntity;
import ru.rastoropova.service_layer.details.EngineDetail;

@Mapper(componentModel = "spring")
public interface EngineDetailEntityMapper {
    EngineDetailEntity toEntity(EngineDetail domain);
    EngineDetail toDomain(EngineDetailEntity entity);
}
