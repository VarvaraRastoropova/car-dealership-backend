package ru.rastoropova.service_layer.details;

import lombok.Getter;
import java.util.UUID;

@Getter
public class EngineDetail implements Detail {
    private UUID id;
    private int price;
    private String name;
    private int enginePower;
    private int engineVolume;

    public EngineDetail(UUID id, int price, String name, int enginePower, int engineVolume) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.enginePower = enginePower;
        this.engineVolume = engineVolume;
    }
}
