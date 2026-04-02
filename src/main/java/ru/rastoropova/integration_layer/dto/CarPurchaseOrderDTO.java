package ru.rastoropova.integration_layer.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class CarPurchaseOrderDTO {
    private UUID id;
    private UUID customerId;
    private UUID employeeId;
    private UUID carModelId;
    private int carModelPrice;
    private String status;
}
