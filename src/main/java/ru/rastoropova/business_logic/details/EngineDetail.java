package ru.rastoropova.business_logic.details;

import lombok.Getter;
import ru.rastoropova.business_logic.car_characteristics.EnginePower;
import ru.rastoropova.business_logic.car_characteristics.EngineVolume;

@Getter
public class EngineDetail implements Detail {
    private int id;
    private int price;
    private String name;
    private EnginePower enginePower;
    private EngineVolume engineVolume;

    public EngineDetail(int id, int price, String name, EnginePower enginePower, EngineVolume engineVolume) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.enginePower = enginePower;
        this.engineVolume = engineVolume;
    }
}
