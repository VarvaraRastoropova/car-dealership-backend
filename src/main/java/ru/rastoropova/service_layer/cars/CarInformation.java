package ru.rastoropova.service_layer.cars;

import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.car_characteristics.CarModel;

@Getter
@Setter
public class CarInformation {
    private CarModel model;
    private Configuration configuration;
    private boolean isAvailableForTestDrive;
}
