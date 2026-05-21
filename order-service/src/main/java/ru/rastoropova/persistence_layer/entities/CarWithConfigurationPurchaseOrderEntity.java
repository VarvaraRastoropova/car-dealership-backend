package ru.rastoropova.persistence_layer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.orders.CarWithConfigurationPurchaseOrderStatus;
import java.util.UUID;

@Entity
@Table(name = "car_with_configuration_purchase_order")
@Getter
@Setter
public class CarWithConfigurationPurchaseOrderEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private UserEntity customer;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private UserEntity employee;

    @Column(name = "car_model_id", nullable = false)
    private UUID carModelId;

    @Column(name = "car_model_price", nullable = false)
    private int carModelPrice;

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

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CarWithConfigurationPurchaseOrderStatus status;
}
