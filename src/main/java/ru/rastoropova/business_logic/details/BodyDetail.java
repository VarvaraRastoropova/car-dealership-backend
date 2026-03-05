package ru.rastoropova.business_logic.details;

import lombok.Getter;
import ru.rastoropova.business_logic.car_characteristics.BodyType;

@Getter
public class BodyDetail implements Detail {
    private int id;
    private int price;
    private String name;
    private BodyType type;
    private String color;

    public BodyDetail(int id, int price, String name, BodyType type, String color) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.type = type;
        this.color = color;
    }
}
