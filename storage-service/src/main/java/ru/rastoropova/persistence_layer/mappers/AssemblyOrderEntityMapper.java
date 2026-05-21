package ru.rastoropova.persistence_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.persistence_layer.entities.AssemblyOrderEntity;
import ru.rastoropova.service_layer.assembly.AssemblyOrder;

@Mapper(componentModel = "spring")
public interface AssemblyOrderEntityMapper {
    AssemblyOrderEntity toEntity(AssemblyOrder domain);
    AssemblyOrder toDomain(AssemblyOrderEntity entity);
}