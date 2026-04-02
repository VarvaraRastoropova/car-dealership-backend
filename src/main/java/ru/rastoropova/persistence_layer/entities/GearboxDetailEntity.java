package ru.rastoropova.persistence_layer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.car_characteristics.GearboxType;

@Entity
@Table(name = "gearbox_details")
@Getter
@Setter
public class GearboxDetailEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private GearboxType type;
}
