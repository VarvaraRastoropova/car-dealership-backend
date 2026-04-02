package ru.rastoropova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.rastoropova.integration_layer.dto.BodyDetailDTO;
import ru.rastoropova.service_layer.car_characteristics.BodyType;
import ru.rastoropova.service_layer.users.Role;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class BodyDetailControllerTests {
    @Container
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreate() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("user_role", Role.SYSTEM_ADMINISTRATOR.name());
        BodyDetailDTO dto = new BodyDetailDTO();
        dto.setName("Name");
        dto.setPrice(1000);
        dto.setColor("Color");
        dto.setType(BodyType.SEDAN.name());
        HttpEntity<BodyDetailDTO> entity = new HttpEntity<>(dto, headers);

        ResponseEntity<BodyDetailDTO> response = restTemplate.exchange(
                "/api/body_details",
                HttpMethod.POST,
                entity,
                BodyDetailDTO.class
        );

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
