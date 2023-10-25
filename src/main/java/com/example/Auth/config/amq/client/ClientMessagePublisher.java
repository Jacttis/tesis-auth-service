package com.example.Auth.config.amq.client;
import com.example.Auth.config.amq.MQConfig;
import com.example.Auth.config.amq.client.ClientMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class ClientMessagePublisher {

    @Autowired
    private RabbitTemplate template;


    public String publishMessage(@RequestBody ClientMessage message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.CLIENT_EXCHANGE,
                MQConfig.CLIENT_CREATE_ROUTING_KEY, message);

        return "Message Published";
    }
}
