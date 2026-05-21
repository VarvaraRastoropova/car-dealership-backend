package ru.rastoropova.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_NAME = "order.exchange";
    public static final String QUEUE_NAME = "order.sent.for.approval";
    public static final String QUEUE_APPROVED_NAME = "order.approved.queue";
    public static final String QUEUE_REJECTED_NAME = "order.rejected.queue";
    public static final String ROUTING_KEY = "order.created.key";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
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
    public Binding binding(DirectExchange exchange, Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }
}
