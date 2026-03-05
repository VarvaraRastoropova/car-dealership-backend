package ru.rastoropova.business_logic.orders;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CarPurchaseOrder {
    private int id;
    private int customerId;
    private int employeeId;
    private int carModelId;
    private int carModelPrice;
    @Setter
    private CarPurchaseOrderStatus status;

    public CarPurchaseOrder(int id, int customerId, int employeeId, int carModelId, int carModelPrice) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.carModelId = carModelId;
        this.carModelPrice = carModelPrice;
        this.status = CarPurchaseOrderStatus.DRAFTED;
    }
}
