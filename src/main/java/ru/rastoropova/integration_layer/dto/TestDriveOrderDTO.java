package ru.rastoropova.integration_layer.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class TestDriveOrderDTO {
    private UUID id;
    private UUID customerId;
    private UUID carModelId;
    private String date;
    private String time;
    private String status;
}
