package ru.rastoropova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerErrorTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void shouldReturn503WhenCarNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/cars/" + UUID.randomUUID())).andExpect(status().isServiceUnavailable());
    }

    @Test
    void shouldReturn401WhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/api/v1/cars")).andExpect(status().isUnauthorized());
    }
}
