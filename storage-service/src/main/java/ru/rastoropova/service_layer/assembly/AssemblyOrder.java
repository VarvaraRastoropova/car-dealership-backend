package ru.rastoropova.service_layer.assembly;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class AssemblyOrder {
    private UUID id;
    private UUID sourceOrderId;
    private OriginalOrderType type;
    private UUID carModelId;
    private UUID carId;
    private Set<UUID> requiredComponentsIds;
    private UUID employeeId;
    private Instant createdAt;
    private Instant updatedAt;
    @Setter
    private AssemblyOrderStatus status;
    private boolean removed;
}
