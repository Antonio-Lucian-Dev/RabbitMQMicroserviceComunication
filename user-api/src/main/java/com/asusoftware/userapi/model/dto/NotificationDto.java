package com.asusoftware.userapi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto {
    private UserDto user;
    private String message;
    private NotificationType notificationType;
}
