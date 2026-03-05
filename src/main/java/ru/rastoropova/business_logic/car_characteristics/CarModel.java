package ru.rastoropova.business_logic.car_characteristics;

import ru.rastoropova.business_logic.cars.Configuration;
import ru.rastoropova.business_logic.details.*;

import java.util.ArrayList;
import java.util.List;

public class CarModel {
    private int id;
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

    public CarModel(int id, int basePrice, String name, String brand, Configuration baseConfiguration, List<BodyDetail> availableBodies,
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

    public int getId() {
        return id;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public Configuration getConfiguration() {
        return baseConfiguration;
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
