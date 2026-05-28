package ru.rastoropova;

import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.rastoropova.grpc.CarServiceGrpc;
import ru.rastoropova.grpc.Cars;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = { "grpc.server.port=0" })
@Testcontainers
public class CarGrpcServerIntegrationTest {
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

    @GrpcClient("storage-service")
    private CarServiceGrpc.CarServiceBlockingStub stub;

    @Test
    void shouldThrowOnInvalidId() {
        assertThrows(StatusRuntimeException.class, () -> {
            stub.withDeadlineAfter(5, TimeUnit.SECONDS)
                    .getCarById(Cars.GetCarByIdRequest.newBuilder().setId(UUID.randomUUID().toString()).build());
        });
    }
}
