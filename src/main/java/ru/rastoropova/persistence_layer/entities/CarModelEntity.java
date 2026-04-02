package ru.rastoropova.persistence_layer.entities;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_models")
@Getter
@Setter
public class CarModelEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "base_price", nullable = false)
    private int basePrice;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Embedded
    private ConfigurationEmbeddable baseConfiguration;

    @ManyToMany
    @JoinTable(
            name = "car_model_bodies",
            joinColumns = @JoinColumn(name = "car_model_id"),
            inverseJoinColumns = @JoinColumn(name = "body_id"))
    private List<BodyDetailEntity> availableBodies;

    @ManyToMany
    @JoinTable(
            name = "car_model_engines",
            joinColumns = @JoinColumn(name = "car_model_id"),
            inverseJoinColumns = @JoinColumn(name = "engine_id"))
    private List<EngineDetailEntity> availableEngines;

    @ManyToMany
    @JoinTable(
            name = "car_model_gearboxes",
            joinColumns = @JoinColumn(name = "car_model_id"),
            inverseJoinColumns = @JoinColumn(name = "gearbox_id"))
    private List<GearboxDetailEntity> availableGearboxes;

    @ManyToMany
    @JoinTable(
            name = "car_model_interiors",
            joinColumns = @JoinColumn(name = "car_model_id"),
            inverseJoinColumns = @JoinColumn(name = "interior_id"))
    private List<InteriorDetailEntity> availableInteriors;

    @ManyToMany
    @JoinTable(
            name = "car_model_steering_wheels",
            joinColumns = @JoinColumn(name = "car_model_id"),
            inverseJoinColumns = @JoinColumn(name = "steering_wheel_id"))
    private List<SteeringWheelDetailEntity> availableSteeringWheels;

    @ManyToMany
    @JoinTable(
            name = "car_model_wheels",
            joinColumns = @JoinColumn(name = "car_model_id"),
            inverseJoinColumns = @JoinColumn(name = "wheel_id"))
    private List<WheelsDetailEntity> availableWheels;
}
