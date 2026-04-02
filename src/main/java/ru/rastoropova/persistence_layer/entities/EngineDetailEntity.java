package ru.rastoropova.persistence_layer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "engine_details")
@Getter
@Setter
public class EngineDetailEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "engine_power", nullable = false)
    private int enginePower;

    @Column(name = "engine_volume", nullable = false)
    private int engineVolume;
}
