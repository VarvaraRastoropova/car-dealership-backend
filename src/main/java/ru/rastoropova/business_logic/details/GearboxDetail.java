package ru.rastoropova.business_logic.details;

import lombok.Getter;
import ru.rastoropova.business_logic.car_characteristics.GearboxType;

@Getter
public class GearboxDetail implements Detail {
    private int id;
    private int price;
    private String name;
    private GearboxType type;

    public GearboxDetail(int id, int price, String name, GearboxType type) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.type = type;
    }
}
