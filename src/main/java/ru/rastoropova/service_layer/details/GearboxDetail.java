package ru.rastoropova.service_layer.details;

import lombok.Getter;
import ru.rastoropova.service_layer.car_characteristics.GearboxType;
import java.util.UUID;

@Getter
public class GearboxDetail implements Detail {
    private UUID id;
    private int price;
    private String name;
    private GearboxType type;

    public GearboxDetail(UUID id, int price, String name, GearboxType type) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.type = type;
    }
}
