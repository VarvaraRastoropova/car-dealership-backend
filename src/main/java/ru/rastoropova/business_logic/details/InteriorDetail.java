package ru.rastoropova.business_logic.details;

import lombok.Getter;
import ru.rastoropova.business_logic.car_characteristics.Color;

@Getter
public class InteriorDetail implements Detail {
    private int id;
    private int price;
    private String name;
    private Color color;

    public InteriorDetail(int id, int price, String name, Color color) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.color = color;
    }
}
