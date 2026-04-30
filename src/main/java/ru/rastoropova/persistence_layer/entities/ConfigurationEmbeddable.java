package ru.rastoropova.persistence_layer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ConfigurationEmbeddable {
    @ManyToOne
    @JoinColumn(name = "body_id", nullable = false)
    private BodyDetailEntity body;

    @ManyToOne
    @JoinColumn(name = "engine_id", nullable = false)
    private EngineDetailEntity engine;

    @ManyToOne
    @JoinColumn(name = "gearbox_id", nullable = false)
    private GearboxDetailEntity gearbox;

    @ManyToOne
    @JoinColumn(name = "interior_id", nullable = false)
    private InteriorDetailEntity interior;

    @ManyToOne
    @JoinColumn(name = "steering_wheel_id", nullable = false)
    private SteeringWheelDetailEntity steeringWheel;

    @ManyToOne
    @JoinColumn(name = "wheels_id", nullable = false)
    private WheelsDetailEntity wheels;
}
