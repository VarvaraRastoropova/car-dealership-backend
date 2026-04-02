package ru.rastoropova.service_layer.details;

import lombok.Getter;
import ru.rastoropova.service_layer.car_characteristics.BodyType;
import java.util.UUID;

@Getter
public class BodyDetail implements Detail {
    private UUID id;
    private int price;
    private String name;
    private BodyType type;
    private String color;

    public BodyDetail(UUID id, int price, String name, BodyType type, String color) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.type = type;
        this.color = color;
    }
}
