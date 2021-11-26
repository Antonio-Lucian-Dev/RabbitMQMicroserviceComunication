package com.asusoftware.notificationapi.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String lastName;
}
