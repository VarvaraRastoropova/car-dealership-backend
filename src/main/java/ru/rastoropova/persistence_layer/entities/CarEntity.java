package ru.rastoropova.persistence_layer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cars")
@Getter
@Setter
public class CarEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModelEntity model;

    @Embedded
    private ConfigurationEmbeddable configuration;

    @Column(name = "is_available_for_test_drive", nullable = false)
    private boolean isAvailableForTestDrive;
}
