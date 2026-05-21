package ru.rastoropova.persistence_layer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.rastoropova.service_layer.assembly.AssemblyOrderStatus;
import ru.rastoropova.service_layer.assembly.OriginalOrderType;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "assembly_orders")
@Getter
@Setter
public class AssemblyOrderEntity extends BaseEntity {
    @Column(name = "source_order_id", nullable = false)
    private UUID sourceOrderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private OriginalOrderType type;

    @Column(name = "car_model_id", nullable = false)
    private UUID carModelId;

    @Column(name = "car_id", nullable = false)
    private UUID carId;

    @Column(name = "required_components_ids", nullable = false)
    private Set<UUID> requiredComponentsIds;

    @Column(name = "employee_id", nullable = false)
    private UUID employeeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AssemblyOrderStatus status;
}
