package ru.rastoropova.business_logic.details;

import lombok.Getter;

@Getter
public class SteeringWheelDetail implements Detail {
    private int id;
    private int price;
    private String name;

    public SteeringWheelDetail(int id, int price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }
}
