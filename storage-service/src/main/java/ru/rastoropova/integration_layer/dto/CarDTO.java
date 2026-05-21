package ru.rastoropova.integration_layer.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class CarDTO {
    private UUID id;
    private UUID modelId;
    private boolean isAvailableForTestDrive;
}
