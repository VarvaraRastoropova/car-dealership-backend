package ru.rastoropova.business_logic.details;

import lombok.Getter;

@Getter
public class EngineDetail implements Detail {
    private int id;
    private int price;
    private String name;
    private int enginePower;
    private int engineVolume;

    public EngineDetail(int id, int price, String name, int enginePower, int engineVolume) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.enginePower = enginePower;
        this.engineVolume = engineVolume;
    }
}
