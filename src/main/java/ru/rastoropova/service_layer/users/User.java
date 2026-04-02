package ru.rastoropova.service_layer.users;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class User {
    UUID id;
    @Setter
    String name;
    Role role;

    public User(UUID id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
