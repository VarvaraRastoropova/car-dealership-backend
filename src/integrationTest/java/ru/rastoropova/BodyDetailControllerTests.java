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
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

    private String getAccessToken() {
        try {
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8081/realms/car-dealership/protocol/openid-connect/token"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(
                            "grant_type=password" +
                                    "&client_id=car-service-client" +
                                    "&username=adolphus_maggio" +
                                    "&password=12345" +
                                    "&client_secret=FK8CitAQtFcHzx2gcobPOMXZVjEaAbbA"))
                    .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().split("\"access_token\":\"")[1].split("\"")[0];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCreate() {
        String accessToken = getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

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
