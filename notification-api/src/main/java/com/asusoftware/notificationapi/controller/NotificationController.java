package com.asusoftware.notificationapi.controller;

import com.asusoftware.notificationapi.model.dto.NotificationDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@AllArgsConstructor
public class NotificationController {


    @RabbitListener(queues = "user_test_queue")
    public void consumeMessageQueue(NotificationDto notificationDto) {
        System.out.println("Message received from queue: " + notificationDto);
    }
}
