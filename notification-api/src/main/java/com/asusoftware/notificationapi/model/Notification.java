package com.asusoftware.notificationapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Column(name = "message")
    private String message;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private NotificationType notificationType;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
