package ru.rastoropova.persistence_layer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.orders.CarPurchaseOrderStatus;

@Entity
@Table(name = "car_purchase_order")
@Getter
@Setter
public class CarPurchaseOrderEntity extends BaseEntity {
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

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CarPurchaseOrderStatus status;
}
