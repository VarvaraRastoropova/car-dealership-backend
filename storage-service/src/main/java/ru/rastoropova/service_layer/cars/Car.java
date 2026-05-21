package ru.rastoropova.service_layer.cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.car_characteristics.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Car {
    private UUID id;
    private CarModel model;
    private boolean isAvailableForTestDrive = true;

    public int getPrice() {
        return model.getBaseConfiguration().calculatePrice(model);
    }
}
