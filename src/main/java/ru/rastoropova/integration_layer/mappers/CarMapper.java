package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rastoropova.integration_layer.dto.CarDTO;
import ru.rastoropova.service_layer.cars.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(target = "modelId", source = "model.id")
    CarDTO toDTO(Car domain);

    @Mapping(target = "model", ignore = true)
    Car toDomain(CarDTO dto);
}
