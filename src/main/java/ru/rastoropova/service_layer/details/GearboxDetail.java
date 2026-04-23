package ru.rastoropova.service_layer.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.rastoropova.service_layer.car_characteristics.GearboxType;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class GearboxDetail implements Detail {
    private UUID id;
    private int price;
    private String name;
    private GearboxType type;
}
