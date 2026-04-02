package ru.rastoropova.service_layer.orders;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
public class CarPurchaseOrder {
    private UUID id;
    private UUID customerId;
    private UUID employeeId;
    private UUID carModelId;
    private int carModelPrice;
    @Setter
    private CarPurchaseOrderStatus status;

    public CarPurchaseOrder(UUID id, UUID customerId, UUID employeeId, UUID carModelId, int carModelPrice) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.carModelId = carModelId;
        this.carModelPrice = carModelPrice;
        this.status = CarPurchaseOrderStatus.DRAFTED;
    }
}
