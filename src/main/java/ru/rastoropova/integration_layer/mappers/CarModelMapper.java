package ru.rastoropova.integration_layer.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rastoropova.integration_layer.dto.CarModelDTO;
import ru.rastoropova.service_layer.car_characteristics.CarModel;
import ru.rastoropova.service_layer.details.*;
import ru.rastoropova.service_layer.details.WheelsDetail;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CarModelMapper {
    @Mapping(target = "bodyId", source = "baseConfiguration.body.id")
    @Mapping(target = "engineId", source = "baseConfiguration.engine.id")
    @Mapping(target = "gearboxId", source = "baseConfiguration.gearbox.id")
    @Mapping(target = "interiorId", source = "baseConfiguration.interior.id")
    @Mapping(target = "steeringWheelId", source = "baseConfiguration.steeringWheel.id")
    @Mapping(target = "wheelsId", source = "baseConfiguration.wheels.id")

    @Mapping(target = "availableBodyIds", source = "availableBodies")
    @Mapping(target = "availableEngineIds", source = "availableEngines")
    @Mapping(target = "availableGearboxIds", source = "availableGearboxes")
    @Mapping(target = "availableInteriorIds", source = "availableInteriors")
    @Mapping(target = "availableSteeringWheelIds", source = "availableSteeringWheels")
    @Mapping(target = "availableWheelsIds", source = "availableWheels")
    CarModelDTO toDTO(CarModel domain);

    @Mapping(target = "baseConfiguration", ignore = true)
    @Mapping(target = "availableBodies", ignore = true)
    @Mapping(target = "availableEngines", ignore = true)
    @Mapping(target = "availableGearboxes", ignore = true)
    @Mapping(target = "availableInteriors", ignore = true)
    @Mapping(target = "availableSteeringWheels", ignore = true)
    @Mapping(target = "availableWheels", ignore = true)
    CarModel toDomain(CarModelDTO dto);

    default UUID map(BodyDetail detail) {
        if (detail == null) {
            return null;
        }
        return detail.getId();
    }

    default UUID map(EngineDetail detail) {
        if (detail == null) {
            return null;
        }
        return detail.getId();
    }

    default UUID map(GearboxDetail detail) {
        if (detail == null) {
            return null;
        }
        return detail.getId();
    }

    default UUID map(InteriorDetail detail) {
        if (detail == null) {
            return null;
        }
        return detail.getId();
    }

    default UUID map(SteeringWheelDetail detail) {
        if (detail == null) {
            return null;
        }
        return detail.getId();
    }

    default UUID map(WheelsDetail detail) {
        if (detail == null) {
            return null;
        }
        return detail.getId();
    }
}
