package ru.rastoropova.persistence_layer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.orders.TestDriveOrderStatus;

@Entity
@Table(name = "test_drive_order")
@Getter
@Setter
public class TestDriveOrderEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private UserEntity customer;

    @ManyToOne
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModelEntity carModel;

    @Column(name = "data", nullable = false)
    private String date;

    @Column(name = "time", nullable = false)
    private String time;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TestDriveOrderStatus status;
}
