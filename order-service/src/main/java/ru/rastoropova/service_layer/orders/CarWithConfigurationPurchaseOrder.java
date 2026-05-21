package ru.rastoropova.service_layer.orders;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
public class CarWithConfigurationPurchaseOrder {
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

    @Setter
    private CarWithConfigurationPurchaseOrderStatus status;

    public CarWithConfigurationPurchaseOrder(UUID id, UUID customerId, UUID employeeId, UUID carModelId, int carModelPrice, UUID bodyId, UUID engineId, UUID gearboxId, UUID interiorId, UUID steeringWheelId, UUID wheelsId) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.carModelId = carModelId;
        this.carModelPrice = carModelPrice;
        this.bodyId = bodyId;
        this.engineId = engineId;
        this.gearboxId = gearboxId;
        this.interiorId = interiorId;
        this.steeringWheelId = steeringWheelId;
        this.wheelsId = wheelsId;
        this.status = CarWithConfigurationPurchaseOrderStatus.DRAFTED;
    }
}
