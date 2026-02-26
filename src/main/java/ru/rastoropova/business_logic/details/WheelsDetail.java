package ru.rastoropova.business_logic.details;

import lombok.Getter;

@Getter
public class WheelsDetail implements Detail {
    private int id;
    private int price;
    private String name;

    public WheelsDetail(int id, int price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }
}
