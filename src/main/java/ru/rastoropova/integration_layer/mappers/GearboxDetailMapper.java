package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.GearboxDetailDTO;
import ru.rastoropova.service_layer.details.GearboxDetail;

@Mapper(componentModel = "spring")
public interface GearboxDetailMapper {
    GearboxDetailDTO toDTO(GearboxDetail domain);
    GearboxDetail toDomain(GearboxDetailDTO dto);
}
