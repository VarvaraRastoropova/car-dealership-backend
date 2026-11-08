package ru.rastoropova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.rastoropova.integration_layer.controllers.CarController;
import ru.rastoropova.integration_layer.exceptions.StorageServiceUnavailableException;
import ru.rastoropova.integration_layer.grpc.CarGrpcClient;
import ru.rastoropova.security.SecurityConfig;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@Import(SecurityConfig.class)
public class CarControllerErrorTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarGrpcClient carGrpcClient;

    @MockBean
    private JwtDecoder jwtDecoder;

    @Test
    @WithMockUser
    void shouldReturn503WhenStorageIsUnavailable() throws Exception {
        UUID carId = UUID.randomUUID();
        when(carGrpcClient.getCarById(carId))
                .thenThrow(new StorageServiceUnavailableException("Storage service unavailable."));

        mockMvc.perform(get("/api/v1/cars/{id}", carId))
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    void shouldReturn401WhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/api/v1/cars")).andExpect(status().isUnauthorized());
    }
}
