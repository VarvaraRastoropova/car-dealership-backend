package ru.rastoropova.persistence_layer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.orders.CarPurchaseOrderStatus;
import java.util.UUID;

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

    @Column(name = "car_model_id", nullable = false)
    private UUID carModelId;

    @Column(name = "car_model_price", nullable = false)
    private int carModelPrice;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CarPurchaseOrderStatus status;
}
