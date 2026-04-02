package ru.rastoropova.service_layer.details;

import lombok.Getter;
import java.util.UUID;

@Getter
public class SteeringWheelDetail implements Detail {
    private UUID id;
    private int price;
    private String name;

    public SteeringWheelDetail(UUID id, int price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }
}
