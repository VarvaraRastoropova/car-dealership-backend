package ru.rastoropova.service_layer.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class SteeringWheelDetail implements Detail {
    private UUID id;
    private int price;
    private String name;
}
