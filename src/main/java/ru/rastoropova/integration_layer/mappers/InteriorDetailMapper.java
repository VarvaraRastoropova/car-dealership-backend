package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.InteriorDetailDTO;
import ru.rastoropova.service_layer.details.InteriorDetail;

@Mapper(componentModel = "spring")
public interface InteriorDetailMapper {
    InteriorDetailDTO toDTO(InteriorDetail domain);
    InteriorDetail toDomain(InteriorDetailDTO dto);
}
