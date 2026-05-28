package ru.rastoropova.messaging;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.rastoropova.integration_layer.dto.AssemblyOrderDTO;
import ru.rastoropova.messaging.events.OrderApprovedEvent;
import ru.rastoropova.messaging.events.OrderRejectedEvent;
import ru.rastoropova.messaging.events.OrderSentForApprovalEvent;
import ru.rastoropova.service_layer.assembly.AssemblyOrder;
import ru.rastoropova.service_layer.assembly.AssemblyOrderStatus;
import ru.rastoropova.service_layer.services.WarehouseService;
import static ru.rastoropova.messaging.RabbitConfig.*;

@Component
@AllArgsConstructor
public class WarehouseEventListener {
    private WarehouseService warehouseService;
    private RabbitTemplate rabbitTemplate;

//    @RabbitListener(queues = "order.sent.for.approval")
    @RabbitListener(queuesToDeclare = @Queue(name = "order.sent.for.approval"))
    public void handleOrderSentForApproval(OrderSentForApprovalEvent event) {
        if (warehouseService.existsBySourceOrderId(event.getOrderId())) {
            return;
        }
        AssemblyOrderDTO dto = new AssemblyOrderDTO();
        dto.setSourceOrderId(event.getOrderId());
        dto.setType(event.getType());
        dto.setCarModelId(event.getCarModelId());
        dto.setCarId(event.getCarId());
        dto.setEmployeeId(event.getEmployeeId());
        dto.setRequiredComponentsIds(event.getRequiredComponentsIds());
        dto.setStatus("CREATED");
        AssemblyOrder assemblyOrder = warehouseService.createAssemblyOrder(dto);
        AssemblyOrder result = warehouseService.changeAssemblyOrderStatus(assemblyOrder.getId(), AssemblyOrderStatus.ASSEMBLED);
        if (result.getStatus() == AssemblyOrderStatus.ASSEMBLED) {
            OrderApprovedEvent approved = new OrderApprovedEvent(
                    event.getEventId(),
                    event.getOrderId(),
                    event.getType()
            );
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ORDER_APPROVED_KEY, approved);
        } else {
            OrderRejectedEvent rejected = new OrderRejectedEvent(
                    event.getEventId(),
                    event.getOrderId(),
                    "Insufficient stock",
                    event.getType()
            );
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ORDER_REJECTED_KEY, rejected);
        }
    }
}
