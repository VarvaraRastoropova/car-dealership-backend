package ru.rastoropova.integration_layer.requests;

import lombok.Data;
import java.util.UUID;

@Data
public class CarPurchaseOrderRequest {
    private UUID carId;
    private UUID carModelId;
    private int price;
}
