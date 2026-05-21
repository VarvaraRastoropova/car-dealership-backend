package ru.rastoropova.service_layer.cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.car_characteristics.CarModel;
import ru.rastoropova.service_layer.details.*;
import ru.rastoropova.service_layer.exceptions.*;

@Getter
@Setter
@AllArgsConstructor
public class Configuration {
    private BodyDetail body;
    private EngineDetail engine;
    private GearboxDetail gearbox;
    private InteriorDetail interior;
    private SteeringWheelDetail steeringWheel;
    private WheelsDetail wheels;

    public void validate(CarModel model) {
        if (body == null || engine == null || gearbox == null || interior == null || steeringWheel == null || wheels == null) {
            throw new DomainValidationException("Error: Not all nodes are specified.");
        }
        if (model.getAvailableBodies().stream().noneMatch(b -> b.getId().equals(body.getId()))) {
            throw new IncompatibleComponentException("Error: Body " + body.getName() + " is incompatible with model " + model.getName());
        }
        if (model.getAvailableEngines().stream().noneMatch(e -> e.getId().equals(engine.getId()))) {
            throw new IncompatibleComponentException("Error: Engine " + engine.getName() + " is incompatible with model " + model.getName());
        }
        if (model.getAvailableGearboxes().stream().noneMatch(g -> g.getId().equals(gearbox.getId()))) {
            throw new IncompatibleComponentException("Error: Gearbox " + gearbox.getName() + " is incompatible with model " + model.getName());
        }
        if (model.getAvailableInteriors().stream().noneMatch(i -> i.getId().equals(interior.getId()))) {
            throw new IncompatibleComponentException("Error: Interior " + interior.getName() + " is incompatible with model " + model.getName());
        }
        if (model.getAvailableSteeringWheels().stream().noneMatch(s -> s.getId().equals(steeringWheel.getId()))) {
            throw new IncompatibleComponentException("Error: SteeringWheel " + steeringWheel.getName() + " is incompatible with model " + model.getName());
        }
        if (model.getAvailableWheels().stream().noneMatch(w -> w.getId().equals(wheels.getId()))) {
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
