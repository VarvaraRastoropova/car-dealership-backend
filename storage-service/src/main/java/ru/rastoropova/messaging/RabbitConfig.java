package ru.rastoropova.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_NAME = "storage.exchange";
    public static final String QUEUE_APPROVED_NAME = "order.approved.queue";
    public static final String QUEUE_REJECTED_NAME = "order.rejected.queue";
    public static final String ORDER_APPROVED_KEY = "order.approved.key";
    public static final String ORDER_REJECTED_KEY = "order.rejected.key";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue orderApprovedQueue() {
        return new Queue(QUEUE_APPROVED_NAME);
    }

    @Bean
    public Queue orderRejectedQueue() {
        return new Queue(QUEUE_REJECTED_NAME);
    }

    @Bean
    public Binding approvedBinding(DirectExchange exchange, Queue orderApprovedQueue) {
        return BindingBuilder
                .bind(orderApprovedQueue)
                .to(exchange)
                .with(ORDER_APPROVED_KEY);
    }

    @Bean
    public Binding rejectedBinding(DirectExchange exchange, Queue orderRejectedQueue) {
        return BindingBuilder
                .bind(orderRejectedQueue)
                .to(exchange)
                .with(ORDER_REJECTED_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }
}
