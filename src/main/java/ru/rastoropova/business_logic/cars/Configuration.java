package ru.rastoropova.business_logic.cars;

import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.business_logic.car_characteristics.CarModel;
import ru.rastoropova.business_logic.details.*;
import ru.rastoropova.business_logic.exceptions.*;

@Getter
@Setter
public class Configuration {
    private BodyDetail body;
    private EngineDetail engine;
    private GearboxDetail gearbox;
    private InteriorDetail interior;
    private SteeringWheelDetail steeringWheel;
    private WheelsDetail wheels;

    public Configuration(BodyDetail body, EngineDetail engine, GearboxDetail gearbox, InteriorDetail interior,
                         SteeringWheelDetail steeringWheel, WheelsDetail wheels) {
        this.body = body;
        this.engine = engine;
        this.gearbox = gearbox;
        this.interior = interior;
        this.steeringWheel = steeringWheel;
        this.wheels = wheels;
    }

    public void validate(CarModel model) {
        if (body == null || engine == null || gearbox == null || interior == null || steeringWheel == null || wheels == null) {
            throw new DomainValidationException("Error: Not all nodes are specified.");
        }
        if (!model.getAvailableBodies().contains(body)) {
            throw new IncompatibleComponentException("Error: Body " + body.getName() + " is incompatible with model " + model.getName());
        }
        if (!model.getAvailableEngines().contains(engine)) {
            throw new IncompatibleComponentException("Error: Engine " + engine.getName() + " is incompatible with model " + model.getName());
        }
        if (!model.getAvailableGearboxes().contains(gearbox)) {
            throw new IncompatibleComponentException("Error: Gearbox " + gearbox.getName() + " is incompatible with model " + model.getName());
        }
        if (!model.getAvailableInteriors().contains(interior)) {
            throw new IncompatibleComponentException("Error: Interior " + interior.getName() + " is incompatible with model " + model.getName());
        }
        if (!model.getAvailableSteeringWheels().contains(steeringWheel)) {
            throw new IncompatibleComponentException("Error: SteeringWheel " + steeringWheel.getName() + " is incompatible with model " + model.getName());
        }
        if (!model.getAvailableWheels().contains(wheels)) {
            throw new IncompatibleComponentException("Error: Wheels " + wheels.getName() + " is incompatible with model " + model.getName());
        }
    }

    public int calculatePrice(CarModel model) {
        validate(model);
        return model.getBasePrice()
                + this.body.getPrice()
                + this.engine.getPrice()
                + this.gearbox.getPrice()
                + this.interior.getPrice()
                + this.steeringWheel.getPrice()
                + this.wheels.getPrice();
    }
}

