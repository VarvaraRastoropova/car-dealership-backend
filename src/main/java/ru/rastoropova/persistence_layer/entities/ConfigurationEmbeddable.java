package ru.rastoropova.persistence_layer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Embeddable
@Getter
@Setter
public class ConfigurationEmbeddable {
    @Column(name = "body_id")
    private UUID bodyId;

    @Column(name = "engine_id")
    private UUID engineId;

    @Column(name = "gearbox_id")
    private UUID gearboxId;

    @Column(name = "interior_id")
    private UUID interiorId;

    @Column(name = "steering_wheel_id")
    private UUID steeringWheelId;

    @Column(name = "wheels_id")
    private UUID wheelsId;
}
