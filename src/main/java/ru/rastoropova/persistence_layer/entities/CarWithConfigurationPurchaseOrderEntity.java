package ru.rastoropova.persistence_layer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.orders.CarWithConfigurationPurchaseOrderStatus;

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

    @ManyToOne
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModelEntity carModel;

    @Column(name = "car_model_price", nullable = false)
    private int carModelPrice;

    @Embedded
    private ConfigurationEmbeddable configuration;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CarWithConfigurationPurchaseOrderStatus status;
}
