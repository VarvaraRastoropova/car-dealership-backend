package ru.rastoropova.messaging;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import static ru.rastoropova.messaging.RabbitConfig.*;

@Component
@AllArgsConstructor
public class OutboxPublisher {
    private OutboxJpaRepository outboxJpaRepository;
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void publish() {
        List<OutboxEntity> unsent = outboxJpaRepository.findByProcessedFalse();

        for (OutboxEntity entity : unsent) {
            try {
                rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, entity.getPayload());
                entity.setProcessed(true);
                outboxJpaRepository.save(entity);
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}
