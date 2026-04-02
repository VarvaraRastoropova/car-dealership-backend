package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.CarModelDTO;
import ru.rastoropova.service_layer.car_characteristics.CarModel;

@Mapper(componentModel = "spring")
public interface CarModelMapper {
    CarModelDTO toDTO(CarModel domain);
    CarModel toDomain(CarModelDTO dto);
}
