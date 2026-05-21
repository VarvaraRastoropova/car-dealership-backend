package ru.rastoropova.service_layer.car_characteristics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.cars.Configuration;
import ru.rastoropova.service_layer.details.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CarModel {
    private UUID id;
    private int basePrice;
    private String name;
    private String brand;
    private Configuration baseConfiguration;
    private Set<BodyDetail> availableBodies;
    private Set<EngineDetail> availableEngines;
    private Set<GearboxDetail> availableGearboxes;
    private Set<InteriorDetail> availableInteriors;
    private Set<SteeringWheelDetail> availableSteeringWheels;
    private Set<WheelsDetail> availableWheels;

    public Set<BodyDetail> getAvailableBodies() {
        if (availableBodies == null) {
            availableBodies = new HashSet<>();
        }
        return availableBodies;
    }

    public Set<EngineDetail> getAvailableEngines() {
        if (availableEngines == null) {
            availableEngines = new HashSet<>();
        }
        return availableEngines;
    }

    public Set<GearboxDetail> getAvailableGearboxes() {
        if (availableGearboxes == null) {
            availableGearboxes = new HashSet<>();
        }
        return availableGearboxes;
    }

    public Set<InteriorDetail> getAvailableInteriors() {
        if (availableInteriors == null) {
            availableInteriors = new HashSet<>();
        }
        return availableInteriors;
    }

    public Set<SteeringWheelDetail> getAvailableSteeringWheels() {
        if (availableSteeringWheels == null) {
            availableSteeringWheels = new HashSet<>();
        }
        return availableSteeringWheels;
    }

    public Set<WheelsDetail> getAvailableWheels() {
        if (availableWheels == null) {
            availableWheels = new HashSet<>();
        }
        return availableWheels;
    }
}
