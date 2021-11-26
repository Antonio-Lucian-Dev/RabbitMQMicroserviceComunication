package com.asusoftware.userapi;

import com.asusoftware.userapi.model.dto.NotificationDto;
import com.asusoftware.userapi.model.dto.UserDto;
import com.asusoftware.userapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;

    @Value("${javainuse.rabbitmq.exchange}")
    private String exchange;

    @Value("${javainuse.rabbitmq.routingkey}")
    private String routingKey;

    @Value("${javainuse.rabbitmq.queue}")
    private String queueName;


    @PostMapping(path = "/create")
    private void create(@RequestBody UserDto userDto) {
        userService.create(userDto);
    }

    @GetMapping(path = "/all")
    private List<UserDto> findAll() {
        return userService.findAll();
    }

    @PostMapping(path = "/createNotification/{id}")
    public String produce(@PathVariable(name = "id") UUID id, @RequestBody NotificationDto notificationDto) {
        logger.info("Storing notification...");
        //rabbitTemplate.setExchange(fanoutExchange);
       // rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(userService.createNotification(id, notificationDto)));
        rabbitTemplate.convertAndSend(exchange, routingKey, userService.createNotification(id, notificationDto));
        logger.info("Notification stored in queue sucessfully");
        return "Succes!";
    }
}
