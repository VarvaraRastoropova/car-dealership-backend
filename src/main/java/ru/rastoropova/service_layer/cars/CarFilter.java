package ru.rastoropova.service_layer.cars;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class CarFilter {
    private Integer price;
    private String  brand;
    private UUID bodyId;
    private UUID engineId;
    private UUID gearboxId;
    private UUID interiorId;
    private UUID steeringWheelId;
    private UUID wheelsId;
}
