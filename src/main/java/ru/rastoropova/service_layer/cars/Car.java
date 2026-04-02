package ru.rastoropova.service_layer.cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.rastoropova.service_layer.car_characteristics.*;
import ru.rastoropova.service_layer.exceptions.DomainValidationException;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Car {
    private UUID id;
    private CarModel model;
    private Configuration configuration;
    private boolean isAvailableForTestDrive = true;

    public int getPrice() {
        return configuration.calculatePrice(model);
    }

    public void setAvailabilityForTestDrive(boolean flag) {
        this.isAvailableForTestDrive = flag;
    }

    public void changeInformation(UUID id, CarInformation information) {
        if (id != this.id) {
            throw new DomainValidationException("Error: Information can only be changed in vehicles with the same ID.");
        }
        if (information.getModel() != null) this.model = information.getModel();
        if (information.getConfiguration() != null) this.configuration = information.getConfiguration();
        this.isAvailableForTestDrive = information.isAvailableForTestDrive();
    }
}
