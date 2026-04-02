package ru.rastoropova.integration_layer.requests;

import lombok.Data;
import java.util.UUID;

@Data
public class TestDriveOrderRequest {
    private UUID carId;
    private String date;
    private String time;
}
