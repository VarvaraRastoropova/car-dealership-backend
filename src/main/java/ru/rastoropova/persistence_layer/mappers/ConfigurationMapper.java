package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.ConfigurationEmbeddable;
import ru.rastoropova.service_layer.cars.Configuration;

@Mapper(componentModel = "spring")
public interface ConfigurationMapper {
    ConfigurationEmbeddable toEntity(Configuration domain);
    Configuration toDomain(ConfigurationEmbeddable domain);
}
