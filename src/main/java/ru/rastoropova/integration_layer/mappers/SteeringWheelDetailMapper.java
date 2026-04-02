package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.SteeringWheelDetailDTO;
import ru.rastoropova.service_layer.details.SteeringWheelDetail;

@Mapper(componentModel = "spring")
public interface SteeringWheelDetailMapper {
    SteeringWheelDetailDTO toDTO(SteeringWheelDetail domain);
    SteeringWheelDetail toDomain(SteeringWheelDetailDTO dto);
}
