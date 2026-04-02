package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import ru.rastoropova.integration_layer.dto.CarDTO;
import ru.rastoropova.service_layer.cars.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDTO toDTO(Car domain);
    Car toDomain(CarDTO dto);
}
