package ru.rastoropova.integration_layer.dto;

import lombok.Data;
import ru.rastoropova.service_layer.cars.Configuration;
import ru.rastoropova.service_layer.details.*;

import java.util.List;
import java.util.UUID;

@Data
public class CarModelDTO {
    private UUID id;
    private int basePrice;
    private String name;
    private String brand;
    private UUID bodyId;
    private UUID engineId;
    private UUID gearboxId;
    private UUID interiorId;
    private UUID steeringWheelId;
    private UUID wheelsId;
    private List<BodyDetailDTO> availableBodies;
    private List<EngineDetailDTO> availableEngines;
    private List<GearboxDetailDTO> availableGearboxes;
    private List<InteriorDetailDTO> availableInteriors;
    private List<SteeringWheelDetailDTO> availableSteeringWheels;
    private List<WheelsDetailDTO> availableWheels;
}
