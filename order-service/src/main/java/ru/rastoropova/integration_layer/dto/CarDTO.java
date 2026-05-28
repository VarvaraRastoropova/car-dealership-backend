package ru.rastoropova.integration_layer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarDTO {
    private String id;
    private boolean isAvailableForTestDrive;
    private int price;
}
