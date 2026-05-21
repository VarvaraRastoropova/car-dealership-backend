package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.WheelsDetailDTO;
import ru.rastoropova.service_layer.details.WheelsDetail;

@Mapper(componentModel = "spring")
public interface WheelsDetailMapper {
    WheelsDetailDTO toDTO(WheelsDetail domain);
    WheelsDetail toDomain(WheelsDetailDTO dto);
}
