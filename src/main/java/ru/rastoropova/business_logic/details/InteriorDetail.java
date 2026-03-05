package ru.rastoropova.business_logic.details;

import lombok.Getter;

@Getter
public class InteriorDetail implements Detail {
    private int id;
    private int price;
    private String name;
    private String color;

    public InteriorDetail(int id, int price, String name, String color) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.color = color;
    }
}
