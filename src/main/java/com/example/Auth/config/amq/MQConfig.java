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

    //Auth to Search Queue
    public static final String AUTH_SEARCH_CLIENT_QUEUE = "auth_search_client_queue";
    public static final String AUTH_SEARCH_CLIENT_DELETE_QUEUE = "auth_search_client_delete_queue";
    public static final String AUTH_SEARCH_WORKER_QUEUE = "auth_search_worker_queue";
    public static final String AUTH_SEARCH_WORKER_DELETE_QUEUE = "auth_search_worker_delete_queue";

    //Auth to Matching Queue
    public static final String AUTH_MATCHING_CLIENT_QUEUE = "auth_matching_client_queue";
    public static final String AUTH_MATCHING_CLIENT_DELETE_QUEUE = "auth_matching_client_delete_queue";
    public static final String AUTH_MATCHING_WORKER_QUEUE = "auth_matching_worker_queue";
    public static final String AUTH_MATCHING_WORKER_DELETE_QUEUE = "auth_matching_worker_delete_queue";

    //Auth to Review Queue
    public static final String AUTH_REVIEW_CLIENT_QUEUE = "auth_review_client_queue";
    public static final String AUTH_REVIEW_CLIENT_DELETE_QUEUE = "auth_review_client_delete_queue";
    public static final String AUTH_REVIEW_WORKER_QUEUE = "auth_review_worker_queue";
    public static final String AUTH_REVIEW_WORKER_DELETE_QUEUE = "auth_review_worker_delete_queue";

    //Exchanges
    public static final String CLIENT_EXCHANGE = "client_exchange";
    public static final String WORKER_EXCHANGE = "worker_exchange";

    //Routing keys
    public static final String CLIENT_CREATE_ROUTING_KEY = "client_create_routing_key";
    public static final String CLIENT_DELETE_ROUTING_KEY = "client_delete_routing_key";
    public static final String WORKER_CREATE_ROUTING_KEY = "worker_create_routing_key";
    public static final String WORKER_DELETE_ROUTING_KEY = "worker_delete_routing_key";


    //Auth to Search
    @Bean
    @Qualifier("auth_search_client_queue")
    public Queue authSearchClientQueue() {
        return  new Queue(AUTH_SEARCH_CLIENT_QUEUE);
    }

    @Bean
    @Qualifier("auth_search_client_delete_queue")
    public Queue authSearchDeleteClientQueue() {
        return  new Queue(AUTH_SEARCH_CLIENT_DELETE_QUEUE);
    }

    @Bean
    @Qualifier("auth_search_worker_queue")
    public Queue authSearchWorkerQueue() {
        return  new Queue(AUTH_SEARCH_WORKER_QUEUE);
    }

    @Bean
    @Qualifier("auth_search_worker_delete_queue")
    public Queue authSearchDeleteWorkerQueue() {
        return  new Queue(AUTH_SEARCH_WORKER_DELETE_QUEUE);
    }

    //Auth to Matching

    @Bean
    @Qualifier("auth_matching_client_queue")
    public Queue authMatchingClientQueue() {
        return  new Queue(AUTH_MATCHING_CLIENT_QUEUE);
    }

    @Bean
    @Qualifier("auth_matching_client_delete_queue")
    public Queue authMatchingDeleteClientQueue() {
        return  new Queue(AUTH_MATCHING_CLIENT_DELETE_QUEUE);
    }

    @Bean
    @Qualifier("auth_matching_worker_queue")
    public Queue authMatchingWorkerQueue() {
        return  new Queue(AUTH_MATCHING_WORKER_QUEUE);
    }

    @Bean
    @Qualifier("auth_matching_worker_delete_queue")
    public Queue authMatchingDeleteWorkerQueue() {
        return  new Queue(AUTH_MATCHING_WORKER_DELETE_QUEUE);
    }

    //Auth to Review

    @Bean
    @Qualifier("auth_review_client_queue")
    public Queue authReviewClientQueue() {
        return  new Queue(AUTH_REVIEW_CLIENT_QUEUE);
    }

    @Bean
    @Qualifier("auth_review_client_delete_queue")
    public Queue authReviewDeleteClientQueue() {
        return  new Queue(AUTH_REVIEW_CLIENT_DELETE_QUEUE);
    }

    @Bean
    @Qualifier("auth_review_worker_queue")
    public Queue authReviewWorkerQueue() {
        return  new Queue(AUTH_REVIEW_WORKER_QUEUE);
    }

    @Bean
    @Qualifier("auth_review_worker_delete_queue")
    public Queue authReviewDeleteWorkerQueue() {
        return  new Queue(AUTH_REVIEW_WORKER_DELETE_QUEUE);
    }

    //Exchanges

    @Bean
    @Qualifier("client_exchange")
    public FanoutExchange clientExchange() {
        return new FanoutExchange(CLIENT_EXCHANGE);
    }

    @Bean
    @Qualifier("worker_exchange")
    public FanoutExchange workerExchange() {
        return new FanoutExchange(WORKER_EXCHANGE);
    }

    //Binding Auth to Search
    @Bean
    public Binding bindingClient(@Qualifier("auth_search_client_queue")Queue queue,@Qualifier("client_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CLIENT_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding bindingDeleteClient(@Qualifier("auth_search_client_delete_queue")Queue queue,@Qualifier("client_exchange") TopicExchange exchange) {
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
    public Binding bindingDeleteWorker(@Qualifier("auth_search_worker_delete_queue")Queue queue,@Qualifier("worker_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(WORKER_DELETE_ROUTING_KEY);
    }

    //Binding Auth to Matching

    @Bean
    public Binding bindingClientMatching(@Qualifier("auth_matching_client_queue")Queue queue,@Qualifier("client_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CLIENT_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding bindingDeleteClientMatching(@Qualifier("auth_matching_client_delete_queue")Queue queue,@Qualifier("client_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CLIENT_DELETE_ROUTING_KEY);
    }

    @Bean
    public Binding bindingWorkerMatching(@Qualifier("auth_matching_worker_queue")Queue queue,@Qualifier("worker_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(WORKER_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding bindingDeleteWorkerMatching(@Qualifier("auth_matching_worker_delete_queue")Queue queue,@Qualifier("worker_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(WORKER_DELETE_ROUTING_KEY);
    }

    //Binding Auth to Review

    @Bean
    public Binding bindingClientReview(@Qualifier("auth_review_client_queue")Queue queue,@Qualifier("client_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CLIENT_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding bindingDeleteClientReview(@Qualifier("auth_review_client_delete_queue")Queue queue,@Qualifier("client_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(CLIENT_DELETE_ROUTING_KEY);
    }

    @Bean
    public Binding bindingWorkerReview(@Qualifier("auth_review_worker_queue")Queue queue,@Qualifier("worker_exchange") TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(WORKER_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding bindingDeleteWorkerReview(@Qualifier("auth_review_worker_delete_queue")Queue queue,@Qualifier("worker_exchange") TopicExchange exchange) {
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
