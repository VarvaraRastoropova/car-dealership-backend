package ru.rastoropova.business_logic.details;

import lombok.Getter;
import ru.rastoropova.business_logic.car_characteristics.BodyType;
import ru.rastoropova.business_logic.car_characteristics.Color;

@Getter
public class BodyDetail implements Detail {
    private int id;
    private int price;
    private String name;
    private BodyType type;
    private Color color;

    public BodyDetail(int id, int price, String name, BodyType type, Color color) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.type = type;
        this.color = color;
    }
}
