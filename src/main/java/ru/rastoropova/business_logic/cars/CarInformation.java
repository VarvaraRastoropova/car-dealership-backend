package ru.rastoropova.business_logic.cars;

import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.business_logic.car_characteristics.CarModel;

@Getter
@Setter
public class CarInformation {
    private CarModel model;
    private Configuration configuration;
    private boolean isAvailableForTestDrive;

    public boolean getAvailabilityForTestDrive() {
        return isAvailableForTestDrive;
    }

    public void setAvailabilityForTestDrive(boolean availableForTestDrive) {
        this.isAvailableForTestDrive = availableForTestDrive;
    }
}
