package ru.rastoropova.integration_layer.dto;

import lombok.Data;
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
    private List<UUID> availableBodyIds;
    private List<UUID> availableEngineIds;
    private List<UUID> availableGearboxIds;
    private List<UUID> availableInteriorIds;
    private List<UUID> availableSteeringWheelIds;
    private List<UUID> availableWheelsIds;
}
