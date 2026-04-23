package ru.rastoropova;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.rastoropova.integration_layer.dto.CarDTO;
import ru.rastoropova.service_layer.cars.CarFilter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class FilterTest {
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
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    void ShouldFilter() throws Exception {
        String token = getAccessToken();
        CarFilter filter = new CarFilter();
        filter.setPrice(1000);

        MvcResult result = mockMvc
                .perform(get("/api/cars/filter")
                        .param("price", String.valueOf(filter.getPrice()))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<CarDTO> carList = objectMapper.readValue(jsonResponse, new TypeReference<List<CarDTO>>() {});

        assertFalse(carList.isEmpty());
    }
}
