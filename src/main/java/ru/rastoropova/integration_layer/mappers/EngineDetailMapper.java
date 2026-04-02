package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.EngineDetailDTO;
import ru.rastoropova.service_layer.details.EngineDetail;

@Mapper(componentModel = "spring")
public interface EngineDetailMapper {
    EngineDetailDTO toDTO(EngineDetail domain);
    EngineDetail toDomain(EngineDetailDTO dto);
}
