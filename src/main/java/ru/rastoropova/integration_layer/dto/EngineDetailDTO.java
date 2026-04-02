package ru.rastoropova.integration_layer.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class EngineDetailDTO {
    private UUID id;
    private int price;
    private String name;
    private int enginePower;
    private int engineVolume;
}
