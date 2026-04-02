package ru.rastoropova.service_layer.orders;

import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.cars.Configuration;
import java.util.UUID;

@Getter
public class CarWithConfigurationPurchaseOrder {
    private UUID id;
    private UUID customerId;
    private UUID employeeId;
    private UUID carModelId;
    private int carModelPrice;
    private Configuration configuration;
    @Setter
    private CarWithConfigurationPurchaseOrderStatus status;

    public CarWithConfigurationPurchaseOrder(UUID id, UUID customerId, UUID employeeId, UUID carModelId, int carModelPrice, Configuration configuration) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.carModelId = carModelId;
        this.carModelPrice = carModelPrice;
        this.configuration = configuration;
        this.status = CarWithConfigurationPurchaseOrderStatus.DRAFTED;
    }
}
