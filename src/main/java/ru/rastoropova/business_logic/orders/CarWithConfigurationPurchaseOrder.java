package ru.rastoropova.business_logic.orders;

import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.business_logic.cars.Configuration;

@Getter
public class CarWithConfigurationPurchaseOrder {
    private int id;
    private int customerId;
    private int employeeId;
    private int carModelId;
    private int carModelPrice;
    private Configuration configuration;
    @Setter
    private CarWithConfigurationPurchaseOrderStatus status;

    public CarWithConfigurationPurchaseOrder(int id, int customerId, int employeeId, int carModelId, int carModelPrice, Configuration configuration) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.carModelId = carModelId;
        this.carModelPrice = carModelPrice;
        this.configuration = configuration;
        this.status = CarWithConfigurationPurchaseOrderStatus.DRAFTED;
    }
}
