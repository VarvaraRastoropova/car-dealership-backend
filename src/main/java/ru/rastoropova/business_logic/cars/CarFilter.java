package ru.rastoropova.business_logic.cars;

import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.business_logic.car_characteristics.CarBrand;
import ru.rastoropova.business_logic.details.*;

@Getter
@Setter
public class CarFilter {
    private int price;
    private CarBrand  brand;
    private BodyDetail body;
    private EngineDetail engine;
    private GearboxDetail gearbox;
    private InteriorDetail interior;
    private SteeringWheelDetail steeringWheel;
    private WheelsDetail wheels;
}
