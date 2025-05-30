package com.example.Auth.config.amq;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String AUTH_SEARCH_CLIENT_QUEUE = "auth_search_client_queue";
    public static final String AUTH_SEARCH_CLIENT_DELETE_QUEUE = "auth_search_client_delete_queue";
    public static final String AUTH_SEARCH_WORKER_QUEUE = "auth_search_worker_queue";
    public static final String AUTH_SEARCH_WORKER_DELETE_QUEUE = "auth_search_worker_delete_queue";
    public static final String CLIENT_EXCHANGE = "client_exchange";
    public static final String WORKER_EXCHANGE = "worker_exchange";
    public static final String CLIENT_CREATE_ROUTING_KEY = "client_create_routing_key";
    public static final String CLIENT_DELETE_ROUTING_KEY = "client_delete_routing_key";
    public static final String WORKER_CREATE_ROUTING_KEY = "worker_create_routing_key";
    public static final String WORKER_DELETE_ROUTING_KEY = "worker_delete_routing_key";

    @Bean
    @Qualifier("auth_search_client_queue")
    public Queue authSearchClientQueue() {
        return  new Queue(AUTH_SEARCH_CLIENT_QUEUE);
    }

    @Bean
    @Qualifier("auth_search_delete_client_queue")
    public Queue authSearchDeleteClientQueue() {
        return  new Queue(AUTH_SEARCH_CLIENT_DELETE_QUEUE);
    }

    @Bean
    @Qualifier("client_exchange")
    public TopicExchange clientExchange() {
        return new TopicExchange(CLIENT_EXCHANGE);
    }

    @Bean
    @Qualifier("auth_search_worker_queue")
    public Queue authSearchWorkerQueue() {
        return  new Queue(AUTH_SEARCH_WORKER_QUEUE);
    }

    @Bean
    @Qualifier("auth_search_delete_worker_queue")
    public Queue authSearchDeleteWorkerQueue() {
        return  new Queue(AUTH_SEARCH_WORKER_DELETE_QUEUE);
    }

    @Bean@Qualifier("worker_exchange")
    public TopicExchange workerExchange() {
        return new TopicExchange(WORKER_EXCHANGE);
    }

    @Bean
    public Binding binding(@Qualifier("auth_search_client_queue")Queue queue,@Qualifier("client_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CLIENT_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding bindingDeleteClient(@Qualifier("auth_search_delete_client_queue")Queue queue,@Qualifier("client_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CLIENT_DELETE_ROUTING_KEY);
    }

    @Bean
    public Binding bindingWorker(@Qualifier("auth_search_worker_queue")Queue queue,@Qualifier("worker_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(WORKER_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding bindingDeleteWorker(@Qualifier("auth_search_delete_worker_queue")Queue queue,@Qualifier("worker_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(WORKER_DELETE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return  template;
    }

}
