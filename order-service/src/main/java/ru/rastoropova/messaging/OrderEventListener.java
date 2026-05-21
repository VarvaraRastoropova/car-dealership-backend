package ru.rastoropova.messaging;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.rastoropova.messaging.events.OrderApprovedEvent;
import ru.rastoropova.messaging.events.OrderRejectedEvent;
import ru.rastoropova.service_layer.orders.CarPurchaseOrder;
import ru.rastoropova.service_layer.orders.CarPurchaseOrderStatus;
import ru.rastoropova.service_layer.orders.CarWithConfigurationPurchaseOrder;
import ru.rastoropova.service_layer.orders.CarWithConfigurationPurchaseOrderStatus;
import ru.rastoropova.service_layer.services.OrderService;

@Component
@AllArgsConstructor
public class OrderEventListener {
    private OrderService orderService;

    @RabbitListener(queues = "order.approved.queue")
    public void handleOrderApproved(OrderApprovedEvent event) {
        if (event.getType().equals("CAR_PURCHASE")) {
            CarPurchaseOrder order = orderService.viewOrderCar(event.getOrderId());
            if (order.getStatus() == CarPurchaseOrderStatus.READY || order.getStatus() == CarPurchaseOrderStatus.COMPLETED) {
                return;
            }
            orderService.changeCarPurchaseOrderStatus(event.getOrderId(), CarPurchaseOrderStatus.READY);
        } else if (event.getType().equals("CAR_WITH_CONFIGURATION")) {
            CarWithConfigurationPurchaseOrder order = orderService.viewOrderCarWithConfiguration(event.getOrderId());
            if (order.getStatus() == CarWithConfigurationPurchaseOrderStatus.READY || order.getStatus() == CarWithConfigurationPurchaseOrderStatus.COMPLETED) {
                return;
            }
            orderService.changeCarWithConfigurationPurchaseOrderStatus(event.getOrderId(), CarWithConfigurationPurchaseOrderStatus.READY);
        }
    }

    @RabbitListener(queues = "order.rejected.queue")
    public void handleOrderRejected(OrderRejectedEvent event) {
        if (event.getType().equals("CAR_PURCHASE")) {
            CarPurchaseOrder order = orderService.viewOrderCar(event.getOrderId());
            if (order.getStatus() == CarPurchaseOrderStatus.CANCELLED) {
                return;
            }
            orderService.changeCarPurchaseOrderStatus(event.getOrderId(), CarPurchaseOrderStatus.CANCELLED);
        } else if (event.getType().equals("CAR_WITH_CONFIGURATION")) {
            CarWithConfigurationPurchaseOrder order = orderService.viewOrderCarWithConfiguration(event.getOrderId());
            if (order.getStatus() == CarWithConfigurationPurchaseOrderStatus.CANCELLED) {
                return;
            }
            orderService.changeCarWithConfigurationPurchaseOrderStatus(event.getOrderId(), CarWithConfigurationPurchaseOrderStatus.CANCELLED);
        }
    }
}
