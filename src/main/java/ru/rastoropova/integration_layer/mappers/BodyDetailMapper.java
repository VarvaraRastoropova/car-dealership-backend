package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.BodyDetailDTO;
import ru.rastoropova.service_layer.details.BodyDetail;

@Mapper(componentModel = "spring")
public interface BodyDetailMapper {
    BodyDetailDTO toDTO(BodyDetail domain);
    BodyDetail toDomain(BodyDetailDTO dto);
}
