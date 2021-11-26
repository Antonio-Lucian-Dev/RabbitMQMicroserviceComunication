package com.asusoftware.notificationapi.model.dto;

import com.asusoftware.notificationapi.model.NotificationType;
import com.asusoftware.notificationapi.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class NotificationDto {
    private User user;
    private String message;
    private NotificationType notificationType;
}
