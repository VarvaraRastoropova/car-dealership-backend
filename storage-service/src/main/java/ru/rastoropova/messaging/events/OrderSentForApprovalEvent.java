package ru.rastoropova.messaging.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderSentForApprovalEvent {
    private UUID eventId;
    private UUID orderId;
    private String type;
    private UUID carModelId;
    private UUID carId;
    private UUID employeeId;
    private Set<UUID> requiredComponentsIds;
}
