package ru.rastoropova.service_layer.car_characteristics;

import lombok.Getter;
import ru.rastoropova.service_layer.cars.Configuration;
import ru.rastoropova.service_layer.details.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class CarModel {
    private UUID id;
    private int basePrice;
    private String name;
    private String brand;
    private Configuration baseConfiguration;
    private List<BodyDetail> availableBodies;
    private List<EngineDetail> availableEngines;
    private List<GearboxDetail> availableGearboxes;
    private List<InteriorDetail> availableInteriors;
    private List<SteeringWheelDetail> availableSteeringWheels;
    private List<WheelsDetail> availableWheels;

    public CarModel(UUID id, int basePrice, String name, String brand, Configuration baseConfiguration, List<BodyDetail> availableBodies,
                    List<EngineDetail> availableEngines, List<GearboxDetail> availableGearboxes, List<InteriorDetail> availableInteriors,
                    List<SteeringWheelDetail> availableSteeringWheels, List<WheelsDetail> availableWheels) {
        this.id = id;
        this.basePrice = basePrice;
        this.name = name;
        this.brand = brand;
        this.baseConfiguration = baseConfiguration;
        this.availableBodies =  availableBodies;
        this.availableEngines = availableEngines;
        this.availableGearboxes = availableGearboxes;
        this.availableInteriors = availableInteriors;
        this.availableSteeringWheels = availableSteeringWheels;
        this.availableWheels = availableWheels;
    }

    public List<BodyDetail> getAvailableBodies() {
        if (availableBodies == null) {
            availableBodies = new ArrayList<>();
        }
        return availableBodies;
    }

    public List<EngineDetail> getAvailableEngines() {
        if (availableEngines == null) {
            availableEngines = new ArrayList<>();
        }
        return availableEngines;
    }

    public List<GearboxDetail> getAvailableGearboxes() {
        if (availableGearboxes == null) {
            availableGearboxes = new ArrayList<>();
        }
        return availableGearboxes;
    }

    public List<InteriorDetail> getAvailableInteriors() {
        if (availableInteriors == null) {
            availableInteriors = new ArrayList<>();
        }
        return availableInteriors;
    }

    public List<SteeringWheelDetail> getAvailableSteeringWheels() {
        if (availableSteeringWheels == null) {
            availableSteeringWheels = new ArrayList<>();
        }
        return availableSteeringWheels;
    }

    public List<WheelsDetail> getAvailableWheels() {
        if (availableWheels == null) {
            availableWheels = new ArrayList<>();
        }
        return availableWheels;
    }
}
