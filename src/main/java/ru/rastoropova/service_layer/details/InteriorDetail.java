package ru.rastoropova.service_layer.details;

import lombok.Getter;
import java.util.UUID;

@Getter
public class InteriorDetail implements Detail {
    private UUID id;
    private int price;
    private String name;
    private String color;

    public InteriorDetail(UUID id, int price, String name, String color) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.color = color;
    }
}
