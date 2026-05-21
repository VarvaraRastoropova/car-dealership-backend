package ru.rastoropova.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OutboxService {
    private OutboxJpaRepository outboxJpaRepository;
    private ObjectMapper objectMapper;

    public void saveEvent(UUID orderId, String eventType, Object event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            OutboxEntity entity = new OutboxEntity(orderId, eventType, payload);
            outboxJpaRepository.save(entity);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }
}
