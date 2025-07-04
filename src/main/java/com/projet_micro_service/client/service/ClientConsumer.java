package com.projet_micro_service.client.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.projet_micro_service.client.model.ClientModel;

@Service
public class ClientConsumer {

    @RabbitListener(queues = "${client.queue}")
    public void handleOrderNotification(String message) {
        try {
            System.out.println("Client notification received: " + message);
        } catch (Exception e) {
            System.err.println("Error processing client notification: " + e.getMessage());
        }
    }
}