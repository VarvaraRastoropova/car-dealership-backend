package ru.rastoropova.service_layer.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.rastoropova.service_layer.car_characteristics.BodyType;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class BodyDetail implements Detail {
    private UUID id;
    private int price;
    private String name;
    private BodyType type;
    private String color;
}
