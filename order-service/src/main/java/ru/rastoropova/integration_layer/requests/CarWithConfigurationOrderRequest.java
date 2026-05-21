package ru.rastoropova.integration_layer.requests;

import lombok.Data;
import java.util.UUID;

@Data
public class CarWithConfigurationOrderRequest {
    private UUID carModelId;
    private UUID bodyId;
    private UUID engineId;
    private UUID gearboxId;
    private UUID interiorId;
    private UUID steeringWheelId;
    private UUID wheelsId;
    private int price;
}
