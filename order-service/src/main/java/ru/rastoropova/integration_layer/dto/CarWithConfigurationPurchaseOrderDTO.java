package ru.rastoropova.integration_layer.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class CarWithConfigurationPurchaseOrderDTO {
    private UUID id;
    private UUID customerId;
    private UUID employeeId;
    private UUID carModelId;
    private int carModelPrice;
    private UUID bodyId;
    private UUID engineId;
    private UUID gearboxId;
    private UUID interiorId;
    private UUID steeringWheelId;
    private UUID wheelsId;
    private String status;
}
