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
import ru.rastoropova.service_layer.orders.CarPurchaseOrderStatus;
import ru.rastoropova.service_layer.services.OrderService;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class OrderIntegrationTests {
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
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private UUID orderId;

    @Test
    @org.junit.jupiter.api.Order(1)
    void shouldCreateOrderAndChangeStatusToPaid() {
        UUID customerId = UUID.fromString("ce6af829-e318-45a8-82f9-83aaebb830a8");
        UUID carModelId = UUID.fromString("a1b2c3d4-e5f6-4a1b-8c2d-3e4f5a6b7c8d");

        var order = orderService.orderCar(customerId, carModelId, 1000);
        orderId = order.getId();

        orderService.changeCarPurchaseOrderStatus(orderId, CarPurchaseOrderStatus.APPROVED);
        orderService.changeCarPurchaseOrderStatus(orderId, CarPurchaseOrderStatus.WAITING_PAYMENT);
        orderService.changeCarPurchaseOrderStatus(orderId, CarPurchaseOrderStatus.PAID);

        var update = orderService.viewOrderCar(orderId);
        assertEquals(CarPurchaseOrderStatus.PAID, update.getStatus());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void shouldSendMessageToRabbitMQ() throws InterruptedException {
        Object message = rabbitTemplate.receiveAndConvert("order.sent.for.approval", 5000);
        assertNotNull(message);
    }
}
