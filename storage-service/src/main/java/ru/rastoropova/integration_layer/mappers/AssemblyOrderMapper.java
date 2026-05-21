package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.AssemblyOrderDTO;
import ru.rastoropova.service_layer.assembly.AssemblyOrder;

@Mapper(componentModel = "spring")
public interface AssemblyOrderMapper {
    AssemblyOrderDTO toDTO(AssemblyOrder domain);
    AssemblyOrder toDomain(AssemblyOrderDTO dto);
}
