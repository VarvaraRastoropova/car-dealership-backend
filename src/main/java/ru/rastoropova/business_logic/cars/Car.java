package ru.rastoropova.business_logic.cars;

import lombok.Getter;
import ru.rastoropova.business_logic.car_characteristics.*;
import ru.rastoropova.business_logic.exceptions.DomainValidationException;

@Getter
public class Car {
    private int id;
    private CarModel model;
    private Configuration configuration;
    private boolean isAvailableForTestDrive;

    public Car(int id, CarModel model, Configuration configuration) {
        this.id = id;
        this.model = model;
        this.configuration = configuration;
        this.isAvailableForTestDrive = true;
    }

    public int getPrice() {
        return configuration.calculatePrice(model);
    }

    public boolean isAvailableForTestDrive() {
        return isAvailableForTestDrive;
    }

    public void setAvailabilityForTestDrive(boolean flag) {
        this.isAvailableForTestDrive = flag;
    }

    public void changeInformation(int id, CarInformation information) {
        if (id != this.id) {
            throw new DomainValidationException("Error: Information can only be changed in vehicles with the same ID.");
        }
        if (information.getModel() != null) this.model = information.getModel();
        if (information.getConfiguration() != null) this.configuration = information.getConfiguration();
        this.isAvailableForTestDrive = information.getAvailabilityForTestDrive();
    }
}
