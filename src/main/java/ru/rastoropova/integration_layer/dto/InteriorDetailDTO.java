package ru.rastoropova.integration_layer.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class InteriorDetailDTO {
    private UUID id;
    private int price;
    private String name;
    private String color;
}
