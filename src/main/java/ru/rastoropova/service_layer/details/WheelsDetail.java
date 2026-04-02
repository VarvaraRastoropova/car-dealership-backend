package ru.rastoropova.service_layer.details;

import lombok.Getter;
import java.util.UUID;

@Getter
public class WheelsDetail implements Detail {
    private UUID id;
    private int price;
    private String name;

    public WheelsDetail(UUID id, int price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }
}
