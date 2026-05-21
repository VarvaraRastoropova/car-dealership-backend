package ru.rastoropova.messaging.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrderApprovedEvent {
    private UUID eventId;
    private UUID orderId;
    private String type;
}
