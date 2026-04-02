package ru.rastoropova.integration_layer.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class WheelsDetailDTO {
    private UUID id;
    private int price;
    private String name;
}
