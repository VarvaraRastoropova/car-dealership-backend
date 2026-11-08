package ru.rastoropova;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.rastoropova.messaging.WarehouseEventListener;
import ru.rastoropova.messaging.events.OrderApprovedEvent;
import ru.rastoropova.messaging.events.OrderSentForApprovalEvent;
import ru.rastoropova.service_layer.assembly.AssemblyOrderStatus;
import ru.rastoropova.service_layer.services.WarehouseService;
import java.util.Set;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class StorageIntegrationTests {
    @Container
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Container
    private static RabbitMQContainer rabbitMQ = new RabbitMQContainer("rabbitmq:3-management")
            .withUser("guest", "guest");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
        registry.add("spring.rabbitmq.host", rabbitMQ::getHost);
        registry.add("spring.rabbitmq.port", rabbitMQ::getAmqpPort);
        registry.add("spring.rabbitmq.username", () -> "guest");
        registry.add("spring.rabbitmq.password", () -> "guest");
        registry.add("spring.rabbitmq.listener.simple.auto-startup", () -> "false");
    }

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private WarehouseEventListener warehouseEventListener;

    @Test
    void shouldCreateAssemblyOrderAndPublishApproval() {
        UUID carModelId = UUID.fromString("a1b2c3d4-e5f6-4a1b-8c2d-3e4f5a6b7c8d");
        UUID orderId = UUID.randomUUID();
        UUID carId = UUID.fromString("3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f");
        UUID employeeId = UUID.fromString("d0a75ba0-9cc8-40c7-8ac2-d5b4ab9fbd8e");

        OrderSentForApprovalEvent event = new OrderSentForApprovalEvent(
                UUID.randomUUID(),
                orderId,
                "CAR_PURCHASE",
                carModelId,
                carId,
                employeeId,
                Set.of());

        warehouseEventListener.handleOrderSentForApproval(event);

        var assemblyOrder = warehouseService.viewAssemblyOrderList().stream()
                .filter(order -> orderId.equals(order.getSourceOrderId()))
                .findFirst()
                .orElseThrow();
        assertEquals(AssemblyOrderStatus.ASSEMBLED, assemblyOrder.getStatus());

        var approval = assertInstanceOf(
                OrderApprovedEvent.class,
                rabbitTemplate.receiveAndConvert("order.approved.queue", 5000)
        );
        assertEquals(orderId, approval.getOrderId());
    }
}
